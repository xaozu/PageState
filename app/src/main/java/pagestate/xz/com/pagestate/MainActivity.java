package pagestate.xz.com.pagestate;


import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import pagestate.xz.com.pagestate.listpage.BaseListActivity;

public class MainActivity extends BaseListActivity {

//    @Override
//    public int getLayoutResId() {
//        return R.layout.activity_main;
//    }

    @Override
    public void initView() {
        super.initView();
        refresh();
    }

    @Override
    public void registerDate() {
        // 注册数据
        register(TextItem.class, new TextItemBinder());

    }

    @Override
    public void loadData() {
        // 添加数据，添加完成，调用loadsuccess
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSuccess(getTextData());
            }
        },2000);

    }

    private List<TextItem> getTextData() {
        List<TextItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new TextItem("item "+i));
        }
        return list;
    }


}
