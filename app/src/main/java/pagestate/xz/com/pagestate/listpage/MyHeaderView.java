package pagestate.xz.com.pagestate.listpage;

import android.content.Context;
import android.view.View;

import pagestate.xz.com.pagestate.loading.AVLoadingIndicatorView;
import pagestate.xz.com.pagestate.refreshlayout.IHeaderView;
import pagestate.xz.com.pagestate.refreshlayout.OnAnimEndListener;

/**
 * creator: zhulunjun
 * time:    2018/4/4
 * describe:
 */

public class MyHeaderView extends AVLoadingIndicatorView implements IHeaderView {

    public MyHeaderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        stopAnimation();
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        stopAnimation();
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        startAnimation();
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        stopAnimation();
    }

    @Override
    public void reset() {
        stopAnimation();
    }
}
