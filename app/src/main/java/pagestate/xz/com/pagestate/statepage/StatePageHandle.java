package pagestate.xz.com.pagestate.statepage;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pagestate.xz.com.pagestate.R;
import pagestate.xz.com.pagestate.page.PageInterFace;

/**
 * creator: zhulunjun
 * time:    2018/4/10
 * describe: 页面状态的处理
 * 为了让fragment和activity使用一套逻辑，抽离一个类来处理公共逻辑
 */

public class StatePageHandle implements StatePageInterface {

    FrameLayout flContent;
    ViewStub stubError;
    ViewStub stubEmpty;
    ViewStub stubLoading;

    private LoadingStateDelegate loadingStateDelegate;
    public View successView;
    private PageInterFace mPage;
    private Context mContext;

    public StatePageHandle(Context mContext, PageInterFace mPage) {
        this.mPage = mPage;
        this.mContext = mContext;
    }

    /**
     * 初始化控件
     */
    public void initView(FrameLayout flContent, ViewStub stubError, ViewStub stubEmpty, ViewStub stubLoading){
        this.flContent = flContent;
        this.stubError = stubError;
        this.stubEmpty = stubEmpty;
        this.stubLoading = stubLoading;
        // 添加子类的视图
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        successView = inflater.inflate(mPage.getLayoutResId(), flContent, true);
        loadingStateDelegate = new LoadingStateDelegate(successView, stubLoading, stubError, stubEmpty);

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
