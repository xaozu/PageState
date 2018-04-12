package pagestate.xz.com.pagestate.statepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.Unbinder;
import pagestate.xz.com.pagestate.page.BaseActivity;
import pagestate.xz.com.pagestate.R;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 页面状态抽象
 * 这里使用ViewStub 来切换不同的状态，懒加载，节约资源
 * ViewStub 直接继承自View，是一种不可见，0大小的可以在运行的时候再加载的View
 */

public abstract class BaseStateActivity extends BaseActivity implements StatePageInterface{

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.view_error)
    View viewError;
    @BindView(R.id.view_empty)
    View viewEmpty;
    @BindView(R.id.view_loading)
    View viewLoading;
    public View successView;
    private StatePageHandle mStatePageHandle;
    @Override
    public boolean haveRootView() {
        // 设置带状态的ContentView
        setContentView(R.layout.page_state);
        return true;
    }


    @Override
    public void initView() {
        mStatePageHandle = new StatePageHandle(this,this);
        mStatePageHandle.initView(flContent, viewError, viewEmpty, viewLoading);
        successView = mStatePageHandle.successView;
    }

    @Override
    public void success() {
        mStatePageHandle.success();
    }

    @Override
    public void empty() {
        mStatePageHandle.empty();
    }

    @Override
    public void error() {
        mStatePageHandle.error();
    }

    @Override
    public void loading() {
        mStatePageHandle.loading();
    }

    @Override
    public void empty(String emptyStr) {
        mStatePageHandle.empty(emptyStr);
    }

    @Override
    public void empty(int imageRes, String emptyStr) {
        mStatePageHandle.empty(imageRes, emptyStr);
    }

    @Override
    public void error(String errorStr) {
        mStatePageHandle.error(errorStr);
    }

    @Override
    public void error(int imageRes, String errorStr) {
        mStatePageHandle.error(imageRes, errorStr);
    }
}
