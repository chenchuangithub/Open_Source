package com.example.ch_.open_source.library;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ch_ on 2016/7/9.
 */
public class AdapterDelegateManager<T> {
    static final int FALL_BACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1;
    /*
    map for view type
     */
    SparseArrayCompat<AdapterDelegate<T>> delegate = new SparseArrayCompat<>();
    /**
     * 默认的delegate
     */
    private AdapterDelegate<T> fallbackDelegate;

    public AdapterDelegateManager<T> addDelegate(@NonNull AdapterDelegate<T> adapterDelegate) {
        int viewType = delegate.size();
        while (delegate.get(viewType) != null) {
            viewType++;
            if (viewType == FALL_BACK_DELEGATE_VIEW_TYPE) {
                throw new IllegalArgumentException("fail");
            }
        }
        return addDelegate(viewType, false, adapterDelegate);
    }

    public AdapterDelegateManager<T> addDelegate(int viewType, AdapterDelegate<T> adapterDelegate) {
        return addDelegate(viewType, false, adapterDelegate);
    }

    public AdapterDelegateManager<T> addDelegate(int viewType, boolean isAllowReplaceDelegate, AdapterDelegate<T> adapterDelegate) {
        if (delegate == null) {
            throw new NullPointerException("adapterDelegate is null");
        }
        if (viewType == FALL_BACK_DELEGATE_VIEW_TYPE) {
            throw new IllegalArgumentException("the view type can't be used");
        }
        if (!isAllowReplaceDelegate && delegate.get(viewType) != null) {
            throw new IllegalArgumentException("error");
        }
        if(delegate.indexOfValue(adapterDelegate) != -1){
            throw new IllegalArgumentException("adapterDelegate aready exist");
        }
        delegate.put(viewType, adapterDelegate);
        return this;
    }

    public AdapterDelegateManager<T> removeDelegate(AdapterDelegate<T> adapterDelegate) {
        if (adapterDelegate == null) {
            throw new NullPointerException("adapterDelegate is null");
        }
        int indexToRemove = delegate.indexOfValue(adapterDelegate);
        if (indexToRemove >= 0) {
            delegate.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 根据key值删除一个delagate
     *
     * @param viewType
     * @return
     */
    public AdapterDelegateManager<T> removeDelegate(int viewType) {
        delegate.remove(viewType);
        return this;
    }

    /**
     * find the viewType
     *
     * @param item
     * @param position
     * @return
     */
    public int getViewType(@NonNull T item, int position) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        int size = delegate.size();
        for (int i = 0; i < size; i++) {
            AdapterDelegate<T> adapterDelegate = delegate.valueAt(i);
            if (adapterDelegate.isViewForType(item, position)) {
                return delegate.keyAt(i);
            }
        }
        throw new NullPointerException("no found");
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> adapterDelegate = getDelegateForViewType(viewType);
        if (adapterDelegate == null) {
            throw new NullPointerException("null");
        }
        RecyclerView.ViewHolder viewHolder = adapterDelegate.onCreateViewHolder(parent);
        if (viewHolder == null) {
            throw new NullPointerException("null");
        }
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull T item, int position, RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> adapterDelegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (adapterDelegate == null) {
            throw new NullPointerException("null");
        }
        adapterDelegate.onBindViewHolder(item, position, viewHolder);
    }

    public AdapterDelegate<T> getDelegateForViewType(int viewType) {
        AdapterDelegate<T> adapterDelegate = delegate.get(viewType);
        if (adapterDelegate == null) {
            if (fallbackDelegate == null) {
                return null;
            } else {
                return fallbackDelegate;
            }
        }
        return adapterDelegate;
    }

    public int getViewType(@NonNull AdapterDelegate<T> adapterDelegate) {
        if (adapterDelegate == null) {
            throw new NullPointerException("null");
        }
        int index = delegate.indexOfValue(adapterDelegate);
        if (index == -1) {
            return -1;
        }
        return delegate.keyAt(index);
    }

    public void setFallbackDelegate(@NonNull AdapterDelegate<T> fallbackDelegate) {
        if (fallbackDelegate == null) {
            throw new NullPointerException("null");
        }
        this.fallbackDelegate = fallbackDelegate;
    }

    public AdapterDelegate<T> getFallbackDelegate() {
        return fallbackDelegate;
    }
}
