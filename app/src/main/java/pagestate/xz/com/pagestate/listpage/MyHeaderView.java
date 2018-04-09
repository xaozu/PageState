package pagestate.xz.com.pagestate.listpage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import pagestate.xz.com.pagestate.loading.AVLoadingIndicatorView;
import pagestate.xz.com.pagestate.loading.RefreshView;
import pagestate.xz.com.pagestate.refreshlayout.IHeaderView;
import pagestate.xz.com.pagestate.refreshlayout.OnAnimEndListener;

/**
 * creator: zhulunjun
 * time:    2018/4/4
 * describe: 自定义顶部加载动画
 */

public class MyHeaderView extends LinearLayout implements IHeaderView {

    private RefreshView mRefreshView;
    private TextView mTextView;

    public MyHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setPadding(0,10,0,10);
        setGravity(Gravity.CENTER);
        mRefreshView = new RefreshView(getContext());
        mRefreshView.setVisibility(GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100, Gravity.CENTER);
        mRefreshView.setLayoutParams(params);
        addView(mRefreshView);

        mTextView = new TextView(getContext());
        mTextView.setTextColor(Color.GRAY);
        mTextView.setText("下拉刷新中...");
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
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (!mRefreshView.isShown()) {
            mRefreshView.setVisibility(VISIBLE);
        }

        if (fraction >= 1f) {
            ViewCompat.setScaleX(mRefreshView, 1f);
            ViewCompat.setScaleY(mRefreshView, 1f);
        } else {
            ViewCompat.setScaleX(mRefreshView, fraction);
            ViewCompat.setScaleY(mRefreshView, fraction);
        }


    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

        if (fraction >= 1f) {
            ViewCompat.setScaleX(mRefreshView, 1f);
            ViewCompat.setScaleY(mRefreshView, 1f);
        } else {
            ViewCompat.setScaleX(mRefreshView, fraction);
            ViewCompat.setScaleY(mRefreshView, fraction);
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        mRefreshView.setVisibility(VISIBLE);
        ViewCompat.setScaleX(mRefreshView, 1f);
        ViewCompat.setScaleY(mRefreshView, 1f);
        mRefreshView.startAnim();
    }

    @Override
    public void onFinish(final OnAnimEndListener animEndListener) {
        mRefreshView.animate().scaleX(0).scaleY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
                animEndListener.onAnimEnd();
            }
        }).start();
    }

    @Override
    public void reset() {
        mRefreshView.stopAnim();
        mRefreshView.clearAnimation();
        mRefreshView.setVisibility(View.GONE);

        ViewCompat.setScaleX(mRefreshView, 0);
        ViewCompat.setScaleY(mRefreshView, 0);
    }
}
