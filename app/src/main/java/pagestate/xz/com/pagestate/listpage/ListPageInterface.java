package pagestate.xz.com.pagestate.listpage;

import java.util.List;

import pagestate.xz.com.pagestate.multitype.adapter.BaseItem;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 列表页面接口
 */

public interface ListPageInterface {

    /**
     * 注册数据模型
     */
    void registerDate();

    /**
     * 主动调用刷新
     */
    void refresh();

    /**
     * 刷新要做的事
     */
    void doRefresh();

    /**
     * 主动调用加载更多
     */
    void loadMore();

    /**
     * 加载更多做的事
     */
    void doLoadMore();

    /**
     * 加载数据，这里是数据源头
     */
    void loadData();

    /**
     * 加载成功的回调，加载成功后调用
     *
     * @param data 加载成功的数据
     */
     void loadSuccess(List<?> data);

    /**
     * 加载失败的回调，加载失败后调用
     *
     * @param errorMessage 失败的文本
     */
    void loadFail(String errorMessage);

    /**
     * 替换数据
     *
     * @param data 需要替换的数据
     */
    void replaceData(List<?> data);

}
