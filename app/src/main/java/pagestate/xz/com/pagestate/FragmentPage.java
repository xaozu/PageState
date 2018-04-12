package pagestate.xz.com.pagestate;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import pagestate.xz.com.pagestate.listpage.BaseListFragment;

/**
 * creator: zhulunjun
 * time:    2018/4/10
 * describe:
 */

public class FragmentPage extends BaseListFragment {

    @Override
    public void initView() {
        super.initView();
        loading();
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
//                loadSuccess(getTextData());
//                empty("没有数据，点击重新加载");
                error("服务器异常(500)");
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
