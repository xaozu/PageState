package pagestate.xz.com.pagestate.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: Fragment基类
 */

public abstract class BaseFragment extends Fragment implements PageInterFace{
    private Unbinder unbinder;
    public View mView;
    public ViewGroup mParentsView;
    public LayoutInflater mInflater;

    @Override
    public void beforeCreateView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        mParentsView = container;
        beforeCreateView();
        if(getLayoutResId() != -1 && !haveRootView()) {
            mView = inflater.inflate(getLayoutResId(), container, false);
        }
        afterCreateView();
        return mView;
    }

    @Override
    public boolean haveRootView() {
        return false;
    }

    @Override
    public void afterCreateView() {
        unbinder = ButterKnife.bind(this, mView);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
