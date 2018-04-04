package pagestate.xz.com.pagestate.statepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import butterknife.BindView;
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
    @BindView(R.id.stub_error)
    ViewStub stubError;
    @BindView(R.id.stub_empty)
    ViewStub stubEmpty;
    @BindView(R.id.stub_loading)
    ViewStub stubLoading;

    private LoadingStateDelegate loadingStateDelegate;
    public View mView;
    @Override
    public boolean haveRootView() {
        // 设置带状态的ContentView
        setContentView(R.layout.page_state);
        return true;
    }


    @Override
    public void initView() {
        // 添加子类的视图
        LayoutInflater inflater =  LayoutInflater.from(this);
        mView = inflater.inflate(getLayoutResId(), flContent, true);
        loadingStateDelegate = new LoadingStateDelegate(mView, stubLoading, stubError, stubEmpty);
    }

    @Override
    public void success() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.SUCCEED);
    }

    @Override
    public void empty() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.EMPTY);
    }

    @Override
    public void error() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.ERROR);
    }

    @Override
    public void loading() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.LOADING);
    }

    @Override
    public void setEmpty() {

    }

    @Override
    public void setError() {

    }
}
