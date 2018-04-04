package pagestate.xz.com.pagestate.statepage;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 状态接口
 */

public interface StatePageInterface {

    void success();

    void empty();

    void error();

    void loading();

    void setEmpty();

    void setError();

}
