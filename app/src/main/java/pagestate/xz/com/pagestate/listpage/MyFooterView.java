package pagestate.xz.com.pagestate.listpage;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import pagestate.xz.com.pagestate.loading.RefreshView;
import pagestate.xz.com.pagestate.refreshlayout.IBottomView;

/**
 * creator: zhulunjun
 * time:    2018/4/9
 * describe: 自定义底部加载动画
 */

public class MyFooterView extends LinearLayout implements IBottomView {
    private RefreshView mRefreshView;
    private TextView mTextView;
    public MyFooterView(Context context) {
        super(context);
        init();
    }

    public MyFooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setPadding(0,10,0,10);
        setGravity(Gravity.CENTER);
        mRefreshView = new RefreshView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100, Gravity.CENTER);
        mRefreshView.setLayoutParams(params);
        addView(mRefreshView);

        mTextView = new TextView(getContext());
        mTextView.setTextColor(Color.GRAY);
        mTextView.setText("加载中...");
        mTextView.setTextSize(12);
        LinearLayout.LayoutParams textparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        mTextView.setLayoutParams(textparams);
        addView(mTextView);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
        mRefreshView.stopAnim();
    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {
        mRefreshView.startAnim();
    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {
        mRefreshView.stopAnim();
    }

    @Override
    public void onFinish() {
        mRefreshView.stopAnim();
    }

    @Override
    public void reset() {
        mRefreshView.stopAnim();
    }
}
