package com.example.ch_.open_source;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ch_.open_source.delegate.CatAdapterDelegate;
import com.example.ch_.open_source.delegate.DogAdapterDelegate;
import com.example.ch_.open_source.delegate.TigerAdapterDelegate;
import com.example.ch_.open_source.library.AdapterDelegateManager;
import com.example.ch_.open_source.model.DisplayItem;

import java.util.List;

/**
 * Created by ch_ on 2016/7/10.
 */
public class MainAdapter extends RecyclerView.Adapter {
    private AdapterDelegateManager<List<DisplayItem>> mAdapterManager;
    private List<DisplayItem> items;

    public MainAdapter(Activity activity, List<DisplayItem> items) {
        mAdapterManager = new AdapterDelegateManager<>();
        mAdapterManager.addDelegate(new DogAdapterDelegate(activity));
        mAdapterManager.addDelegate(new CatAdapterDelegate(activity));
        mAdapterManager.addDelegate(new TigerAdapterDelegate(activity));
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapterManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mAdapterManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterManager.getViewType(items,position);
    }
}
