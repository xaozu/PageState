package pagestate.xz.com.pagestate.listpage;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pagestate.xz.com.pagestate.R;
import pagestate.xz.com.pagestate.multitype.ItemViewBinder;
import pagestate.xz.com.pagestate.multitype.MultiTypeAdapter;
import pagestate.xz.com.pagestate.refreshlayout.RefreshListenerAdapter;
import pagestate.xz.com.pagestate.refreshlayout.TwinklingRefreshLayout;
import pagestate.xz.com.pagestate.statepage.BaseStateActivity;
import pagestate.xz.com.pagestate.statepage.StatePageInterface;

/**
 * creator: zhulunjun
 * time:    2018/4/9
 * describe: 列表页面数据处理
 * 为了让fragment和activity使用一套逻辑，将对recycleview的操作抽离一个类出来实现，与fragment和activity实现同一个接口
 */

public class ListPageHandle implements ListPageInterface {

    /**
     * 页数
     */
    private int page = 1;
    private RecyclerView mRecyclerView;
    private TwinklingRefreshLayout mRefreshLayout;
    private MultiTypeAdapter mAdapter;
    private List mItems = new ArrayList<>();
    /**
     * 切换页面状态
     */
    private StatePageInterface mState;
    /**
     * 用于回调实现界面的逻辑
     */
    private ListPageInterface mListPage;
    /**
     * 页面的父view
     * 来获取控件
     */
    private View mView;
    private Context mContext;
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

    public ListPageHandle(Context context, View view, StatePageInterface state, ListPageInterface listPage) {
        this.mState = state;
        this.mView = view;
        this.mContext = context;
        this.mListPage = listPage;
    }

    /**
     * 初始化控件
     */
    public void initView() {
        initList();
        initRefresh();
    }

    /**
     * 列表初始化
     */
    private void initList() {
        mRecyclerView = getView(R.id.rv_list);
        if (mRecyclerView == null) {
            return;
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MultiTypeAdapter();
        registerDate();
        mAdapter.setItems(mItems);
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * 刷新控件初始化
     */
    private void initRefresh() {
        mRefreshLayout = getView(R.id.tr_refresh);
        if (mRefreshLayout == null) {
            return;
        }
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

        mRefreshLayout.setHeaderView(new MyHeaderView(mContext));
        mRefreshLayout.setBottomView(new MyFooterView(mContext));
    }


    @Override
    public void registerDate() {
        // 此处需要最终的实现页面来调用
        mListPage.registerDate();
    }

    @Override
    public void refresh() {
        // 主动刷新
        mRefreshLayout.startRefresh();
    }

    @Override
    public void doRefresh() {
        page = 1;
        mItems.clear();
        loadData();
        loadType = REFRESH;
    }

    @Override
    public void loadMore() {
        // 主动加载更多
        mRefreshLayout.startLoadMore();
    }

    @Override
    public void doLoadMore() {
        page++;
        loadData();
        loadType = LOAD_MORE;
    }

    @Override
    public void loadData() {
        // 此处需要最终的实现页面来调用
        mListPage.loadData();
    }

    @Override
    public void loadSuccess(List<?> data) {
        if (null == data || data.size() == 0) {
            if (page == 1) {
                // 没数据
                mState.empty();
            } else {
                // 数据加载完成
            }

        } else {
            mState.success();
            // 展示数据
            mItems.addAll(data);
        }
        endLoad();
    }

    @Override
    public void loadFail(String errorMessage) {
        mState.error();
        endLoad();
    }

    @Override
    public void replaceData(List<?> data) {
        if (null != data) {
            mItems.clear();
            mItems.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取控件
     *
     * @param viewId 控件id
     * @param <T>    类型
     * @return 控件
     */
    public <T extends View> T getView(@IdRes int viewId) {
        return (T) mView.findViewById(viewId);
    }

    /**
     * 结束加载
     */
    private void endLoad() {
        if (loadType == REFRESH) {
            mRefreshLayout.finishRefreshing();
        } else {
            mRefreshLayout.finishLoadmore();
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 注册数据
     *
     * @param clazz  数据模型
     * @param binder 绑定视图的适配器
     */
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        mAdapter.register(clazz, binder);
    }
}
