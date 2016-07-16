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
import com.example.ch_.open_source.model.Cat;
import com.example.ch_.open_source.model.DisplayItem;

import java.util.List;

/**
 * Created by ch_ on 2016/7/10.
 */
public class CatAdapterDelegate implements AdapterDelegate<List<DisplayItem>> {
    private LayoutInflater mInflater;

    public CatAdapterDelegate(Activity activity) {
        mInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isViewForType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof Cat;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(mInflater.inflate(R.layout.item_animal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder1 = (ViewHolder)viewHolder;
        Cat cat = (Cat)items.get(position);
        ((ViewHolder) viewHolder).name.setText(cat.getName()+viewHolder1.getItemViewType());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
