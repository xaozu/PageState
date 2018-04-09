package pagestate.xz.com.pagestate.listpage;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pagestate.xz.com.pagestate.R;
import pagestate.xz.com.pagestate.multitype.ItemViewBinder;
import pagestate.xz.com.pagestate.multitype.MultiTypeAdapter;
import pagestate.xz.com.pagestate.refreshlayout.RefreshListenerAdapter;
import pagestate.xz.com.pagestate.refreshlayout.TwinklingRefreshLayout;
import pagestate.xz.com.pagestate.refreshlayout.header.bezierlayout.BezierLayout;
import pagestate.xz.com.pagestate.refreshlayout.header.progresslayout.ProgressLayout;
import pagestate.xz.com.pagestate.statepage.BaseStateActivity;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 带状态的列表页面
 * <p>
 * 为了让fragment和activity使用一套，将对recycleview的操作抽离一个类出来实现，与fragment和activity实现同一个接口，来规划
 */

public abstract class BaseListActivity extends BaseStateActivity implements ListPageInterface{

    /**
     * 页数
     */
    private int page = 1;
    private RecyclerView mRecyclerView;
    private TwinklingRefreshLayout mRefreshLayout;
    private MultiTypeAdapter mAdapter;
    private List mItems = new ArrayList<>();
    /**
     * 加载状态
     */
    private int loadType = REFRESH;
    /**
     * 刷新状态
     */
    private static final int REFRESH = 1;
    /**
     * 加载更多状态
     */
    private static final int LOAD_MORE = 2;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_base_list;
    }

    @Override
    public void initView() {
        super.initView();
        initList();
        initRefresh();
    }

    /**
     * 列表初始化
     */
    private void initList(){
        mRecyclerView = getView(R.id.rv_list);
        mRefreshLayout = getView(R.id.tr_refresh);
        if(mRecyclerView == null){
            return;
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultiTypeAdapter();
        registerDate();
        mAdapter.setItems(mItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 刷新控件初始化
     */
    private void initRefresh(){
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                doRefresh();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                doLoadMore();
            }
        });

        mRefreshLayout.setHeaderView(new MyHeaderView(this));
        mRefreshLayout.setBottomView(new MyFooterView(this));
    }

    /**
     * 获取控件
     * @param viewId 控件id
     * @param <T> 类型
     * @return 控件
     */
    public <T extends View> T getView(@IdRes int viewId) {
        return (T)  mView.findViewById(viewId);
    }

    @Override
    public void refresh() {
        // 主动刷新
        mRefreshLayout.startRefresh();
    }

    @Override
    public void loadMore() {
        // 主动加载更多
        mRefreshLayout.startLoadMore();
    }

    @Override
    public void doRefresh() {
        page = 1;
        mItems.clear();
        loadData();
        loadType = REFRESH;
    }

    @Override
    public void doLoadMore() {
        page++;
        loadData();
        loadType = LOAD_MORE;
    }

    @Override
    public void loadSuccess(List<?> data) {
        if(null == data || data.size() == 0){
            if(page == 1){
                // 没数据
                empty();
            } else {
                // 数据加载完成
            }

        } else {
            success();
            // 展示数据
            mItems.addAll(data);
        }
        endLoad();

    }

    @Override
    public void loadFail(String errorMessage) {
        error();
        endLoad();
    }

    /**
     * 结束加载
     */
    private void endLoad(){
        if(loadType == REFRESH) {
            mRefreshLayout.finishRefreshing();
        }else {
            mRefreshLayout.finishLoadmore();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void replaceData(List<?> data) {
        if(null != data){
            mItems.clear();
            mItems.addAll(data);
            mAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 注册数据
     * @param clazz 数据模型
     * @param binder 绑定视图的适配器
     */
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder){
        mAdapter.register(clazz, binder);
    }
}
