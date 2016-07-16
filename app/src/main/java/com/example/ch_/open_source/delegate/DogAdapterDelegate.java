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
import com.example.ch_.open_source.model.Dog;

import java.util.List;

/**
 * Created by ch_ on 2016/7/10.
 */
public class DogAdapterDelegate implements AdapterDelegate<List<DisplayItem>> {
    private LayoutInflater mInflater;

    public DogAdapterDelegate(Activity activity) {
        mInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isViewForType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof Dog;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new DogViewHolder(mInflater.inflate(R.layout.item_animal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        DogViewHolder viewHolder1 = (DogViewHolder)viewHolder;
        Dog dog = (Dog) items.get(position);
        ((DogViewHolder) viewHolder).name.setText(dog.getName()+viewHolder1.getItemViewType());
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public DogViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
