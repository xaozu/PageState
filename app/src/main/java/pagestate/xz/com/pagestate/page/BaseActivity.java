package pagestate.xz.com.pagestate.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creator: zhulunjun
 * time:    2018/3/8
 * describe: 基类
 * 控件获取等操作
 */

public abstract class BaseActivity extends AppCompatActivity implements PageInterFace{

    private Unbinder unbinder;


    /**
     * 在setContentView之前
     */
    @Override
    public void beforeCreateView() {

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beforeCreateView();
        if(getLayoutResId() != -1 && !haveRootView()) {
            setContentView(getLayoutResId());
        }
        afterCreateView();
    }
    /**
     * 在setContentView之前
     */
    @Override
    public void afterCreateView() {
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 是否已经设置了ContentView
     * @return 默认没有设置，如果子类需要设置则重写该方法
     */
    @Override
    public boolean haveRootView() {
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
