package pagestate.xz.com.pagestate.page;

/**
 * creator: zhulunjun
 * time:    2018/3/9
 * describe: 页面接口规划
 */

public interface PageInterFace {

    /**
     * 布局
     * @return 子类返回自身的布局
     */
     int getLayoutResId();


    /**
     * 创建布局之前
     */
     void beforeCreateView();

    /**
     * 创建布局之后
     */
    void afterCreateView();

    /**
     * 子类是否已经创建过布局了
     */
    boolean haveRootView();

    /**
     * 初始化布局
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();
}
