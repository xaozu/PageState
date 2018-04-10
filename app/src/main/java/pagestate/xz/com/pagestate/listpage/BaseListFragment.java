package pagestate.xz.com.pagestate.listpage;

import android.support.annotation.NonNull;

import java.util.List;

import pagestate.xz.com.pagestate.R;
import pagestate.xz.com.pagestate.multitype.ItemViewBinder;
import pagestate.xz.com.pagestate.statepage.BaseStateFragment;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 带状态的列表页面
 */

public abstract class BaseListFragment extends BaseStateFragment implements ListPageInterface {

    private ListPageHandle mListPageHandle;

    @Override
    public int getLayoutResId() {
        return R.layout.page_list;
    }

    @Override
    public void initView() {
        super.initView();
        mListPageHandle = new ListPageHandle(getActivity(), successView, this, this);
        mListPageHandle.initView();
    }

    @Override
    public void loading() {
        super.loading();
        loadData();
    }

    @Override
    public void refresh() {
        // 主动刷新
        mListPageHandle.refresh();
    }

    @Override
    public void loadMore() {
        // 主动加载更多
        mListPageHandle.loadMore();
    }

    @Override
    public void doRefresh() {
        mListPageHandle.doRefresh();
    }

    @Override
    public void doLoadMore() {
        mListPageHandle.doLoadMore();
    }

    @Override
    public void loadSuccess(List<?> data) {
        mListPageHandle.loadSuccess(data);
    }

    @Override
    public void loadFail(String errorMessage) {
        mListPageHandle.loadFail(errorMessage);
    }


    @Override
    public void replaceData(List<?> data) {
        mListPageHandle.replaceData(data);
    }

    /**
     * 注册数据
     *
     * @param clazz  数据模型
     * @param binder 绑定视图的适配器
     */
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        mListPageHandle.register(clazz, binder);
    }
}
