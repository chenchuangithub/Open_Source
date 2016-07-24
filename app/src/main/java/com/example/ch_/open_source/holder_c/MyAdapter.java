package com.example.ch_.open_source.holder_c;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ch_.open_source.R;

import java.util.List;

/**
 * Created by ch_ on 2016/7/9.
 */
public class MyAdapter extends RecyclerView.Adapter<HeaderAndFooterWrapper.ViewHolder> {
    private List<String> list;

    public MyAdapter(List data) {
        this.list = data;
    }

    @Override
    public HeaderAndFooterWrapper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HeaderAndFooterWrapper.ViewHolder viewHolder = new HeaderAndFooterWrapper.ViewHolder(parent.getContext(),LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeaderAndFooterWrapper.ViewHolder holder, int position) {
        holder.setText(R.id.text, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
