package com.example.ch_.open_source.library;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by ch_ on 2016/7/9.
 */
public class ListDelegationAdapter<T extends List<?>> extends AbsDelegationAdapter<T> {
    public ListDelegationAdapter() {

    }

    public ListDelegationAdapter(@NonNull AdapterDelegateManager<T> adapterDelegateManager) {
        super(adapterDelegateManager);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
