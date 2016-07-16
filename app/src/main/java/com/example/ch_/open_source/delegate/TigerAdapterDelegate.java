package com.example.ch_.open_source.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ch_.open_source.R;
import com.example.ch_.open_source.library.AdapterDelegate;
import com.example.ch_.open_source.model.DisplayItem;
import com.example.ch_.open_source.model.Tiger;

import java.util.List;

/**
 * Created by ch_ on 2016/7/10.
 */
public class TigerAdapterDelegate implements AdapterDelegate<List<DisplayItem>> {
    private LayoutInflater mInflater;

    public TigerAdapterDelegate(Activity activity) {
        this.mInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isViewForType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof Tiger;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TigerViewHolder(mInflater.inflate(R.layout.item_animal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        TigerViewHolder viewHolder1 = (TigerViewHolder) viewHolder;
        Tiger tiger = (Tiger) items.get(position);
        ((TigerViewHolder) viewHolder).name.setText(tiger.getName()+viewHolder1.getItemViewType());
    }

    static class TigerViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public TigerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
