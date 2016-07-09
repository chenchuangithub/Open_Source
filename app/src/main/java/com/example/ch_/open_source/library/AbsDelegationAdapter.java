package com.example.ch_.open_source.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ch_ on 2016/7/9.
 */
public abstract class AbsDelegationAdapter<T> extends RecyclerView.Adapter {
    protected AdapterDelegateManager<T> adapterDelegateManager;
    protected T items;

    public AbsDelegationAdapter() {
        this(new AdapterDelegateManager<T>());
    }

    public AbsDelegationAdapter(AdapterDelegateManager<T> manager) {
        if (manager == null) {
            throw new NullPointerException("null");
        }
        this.adapterDelegateManager = manager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterDelegateManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegateManager.getViewType(items, position);
    }

    public T getItems() {
        return items;
    }

    public void setItems(@NonNull T items) {
        if (items == null) {
            throw new NullPointerException("null");
        }
        this.items = items;
    }
}
