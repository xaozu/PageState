package pagestate.xz.com.pagestate.statepage;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 状态接口
 */

public interface StatePageInterface {

    void success();

    void empty();

    void empty(String emptyStr);

    void empty(int imageRes, String emptyStr);

    void error();

    void error(String errorStr);

    void error(int imageRes, String errorStr);

    void loading();


}
