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
    ViewStub stubError;
    ViewStub stubEmpty;
    ViewStub stubLoading;

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

        textEmpty = stubEmpty.findViewById(R.id.tv_empty);
        imageEmpty = stubEmpty.findViewById(R.id.iv_empty);
        Log.e("错误信息 "," "+(textEmpty==null)+" "+(imageEmpty==null)+" "+(stubEmpty==null));
    }

    private void getErrorView(){
        textError = stubError.findViewById(R.id.tv_error);
        imageError = stubError.findViewById(R.id.iv_error);
    }

}
