package pagestate.xz.com.pagestate.statepage;

import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import butterknife.BindView;
import pagestate.xz.com.pagestate.page.BaseFragment;
import pagestate.xz.com.pagestate.R;

/**
 * creator: zhulunjun
 * time:    2018/3/9
 * describe: 带状态的fragment基类
 */

public abstract class BaseStateFragment extends BaseFragment implements StatePageInterface {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.stub_error)
    ViewStub stubError;
    @BindView(R.id.stub_empty)
    ViewStub stubEmpty;
    @BindView(R.id.stub_loading)
    ViewStub stubLoading;

    private LoadingStateDelegate loadingStateDelegate;

    @Override
    public boolean haveRootView() {
        // 设置带状态的ContentView
        mView = mInflater.inflate(R.layout.page_state, mParentsView, false);
        return true;
    }


    @Override
    public void initView() {
        // 添加子类的视图
        View view = mInflater.inflate(getLayoutResId(), flContent, true);
        loadingStateDelegate = new LoadingStateDelegate(view, stubLoading, stubError, stubEmpty);
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
