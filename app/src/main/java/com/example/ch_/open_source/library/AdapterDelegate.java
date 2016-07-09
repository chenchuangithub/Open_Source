package com.example.ch_.open_source.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ch_ on 2016/7/9.
 */
public interface AdapterDelegate<T> {
    /**
     * call to determine where this AdapterDelegate is responsible for the given data element
     *
     * @param items
     * @param position
     * @return
     */
    public boolean isViewForType(@NonNull T items, int position);

    /**
     * creates the holder for given data source item
     *
     * @param parent
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * Called to bind the {@link RecyclerView.ViewHolder} to the item of the datas source set
     *
     * @param items
     * @param position
     * @param viewHolder
     */
    public void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder viewHolder);
}
