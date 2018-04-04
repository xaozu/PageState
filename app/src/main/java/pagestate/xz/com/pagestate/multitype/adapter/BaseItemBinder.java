package pagestate.xz.com.pagestate.multitype.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pagestate.xz.com.pagestate.multitype.ItemViewBinder;


/**
 * Author：zhulunjun
 * Time：2017/12/3
 * description：视图绑定基类
 */

public abstract class BaseItemBinder<T> extends ItemViewBinder<T, BaseViewHolder> {
    private int layoutResId;
    public Context mContext;

    public BaseItemBinder(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        this.mContext = parent.getContext();
        View root = inflater.inflate(layoutResId, parent, false);
        return new BaseViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T item) {
        bindView(holder, item);
    }

    protected abstract void bindView(@NonNull BaseViewHolder holder, @NonNull T item);
}
