package pagestate.xz.com.pagestate;

import pagestate.xz.com.pagestate.multitype.adapter.BaseItem;

/**
 * creator: zhulunjun
 * time:    2018/4/4
 * describe:
 */

public class TextItem extends BaseItem {

    private String text;

    public TextItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
