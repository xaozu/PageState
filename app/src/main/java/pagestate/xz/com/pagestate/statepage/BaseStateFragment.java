package pagestate.xz.com.pagestate.statepage;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import java.util.logging.Logger;

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
    private StatePageHandle mStatePageHandle;
    public View successView;
    @Override
    public boolean haveRootView() {
        // 设置带状态的ContentView
        mView = mInflater.inflate(R.layout.page_state, mParentsView, false);
        return true;
    }


    @Override
    public void initView() {
        mStatePageHandle = new StatePageHandle(getActivity(), this);
        mStatePageHandle.initView(flContent, stubError, stubEmpty, stubLoading);
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
    public void setEmpty() {

    }

    @Override
    public void setError() {

    }

}
