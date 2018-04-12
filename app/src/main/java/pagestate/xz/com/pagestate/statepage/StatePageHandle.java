package pagestate.xz.com.pagestate.statepage;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    private View viewError;
    private View viewEmpty;
    private View viewLoading;

    private TextView textEmpty, textError;
    private ImageView imageEmpty, imageError;

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
    public void initView(FrameLayout flContent, View viewError, View viewEmpty, View viewLoading){
        this.flContent = flContent;
        this.viewError = viewError;
        this.viewEmpty = viewEmpty;
        this.viewLoading = viewLoading;
        // 添加子类的视图
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        successView = inflater.inflate(mPage.getLayoutResId(), flContent, true);
        loadingStateDelegate = new LoadingStateDelegate(successView, viewLoading, viewError, viewEmpty);
    }

    @Override
    public void success() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.SUCCEED);
    }

    @Override
    public void empty() {
        empty(-1, null);
    }

    @Override
    public void empty(String emptyStr) {
        empty(-1, emptyStr);
    }

    @Override
    public void empty(int imageRes, String emptyStr) {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.EMPTY);
        if(imageEmpty == null || textEmpty == null){
            getEmptyView();
        }
        // 图片
        if(imageRes != -1){
            imageEmpty.setImageResource(imageRes);
        }
        // 文本
        if (!TextUtils.isEmpty(emptyStr)){
            textEmpty.setText(emptyStr);
        }


    }

    @Override
    public void error() {
        error(-1, null);
    }

    @Override
    public void error(String errorStr) {
        error(-1, errorStr);
    }

    @Override
    public void error(int imageRes, String errorStr) {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.ERROR);
        if(imageError == null || textError == null){
            getErrorView();
        }
        // 图片
        if(imageRes != -1){
            imageError.setImageResource(imageRes);
        }
        // 文本
        if (!TextUtils.isEmpty(errorStr)){
            textError.setText(errorStr);
        }

    }

    @Override
    public void loading() {
        loadingStateDelegate.setLoadingState(LoadingStateDelegate.STATE.LOADING);
    }

    private void getEmptyView(){
        textEmpty = viewEmpty.findViewById(R.id.tv_empty);
        imageEmpty = viewEmpty.findViewById(R.id.iv_empty);
    }

    private void getErrorView(){
        textError = viewError.findViewById(R.id.tv_error);
        imageError = viewError.findViewById(R.id.iv_error);
    }

}
