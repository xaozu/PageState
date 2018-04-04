package pagestate.xz.com.pagestate;

import android.support.annotation.NonNull;

import pagestate.xz.com.pagestate.multitype.adapter.BaseItemBinder;
import pagestate.xz.com.pagestate.multitype.adapter.BaseViewHolder;

/**
 * creator: zhulunjun
 * time:    2018/4/4
 * describe:
 */

public class TextItemBinder extends BaseItemBinder<TextItem> {
    public TextItemBinder() {
        super(R.layout.item_text);
    }

    @Override
    protected void bindView(@NonNull BaseViewHolder holder, @NonNull TextItem item) {
        holder.setText(R.id.tv_text, item.getText());
    }
}
