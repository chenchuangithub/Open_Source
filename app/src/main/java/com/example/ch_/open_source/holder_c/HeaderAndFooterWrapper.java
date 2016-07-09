package com.example.ch_.open_source.holder_c;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ch_ on 2016/7/9.
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 10000;
    private static final int BASE_ITEM_TYPE_FOOTER = 20000;
    private SparseArrayCompat<View> mHeaders = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooters = new SparseArrayCompat<>();
    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    private boolean isHeaderViewPos(int pos) {
        return pos < getHeadersCount();
    }

    private boolean isFooterViewPos(int pos) {
        return pos >= getFootersCount() + getItemCount();
    }

    public void addHeaderView(View view) {
        mHeaders.put(mHeaders.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view) {
        mFooters.put(mFooters.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaders.get(viewType) != null) {
            ViewHolder viewHolder = ViewHolder.createViewHolder(parent.getContext(), mHeaders.get(viewType));
            return viewHolder;
        } else if (mFooters.get(viewType) != null) {
            ViewHolder viewHolder = ViewHolder.createViewHolder(parent.getContext(), mFooters.get(viewType));
            return viewHolder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            return;
        } else if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaders.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooters.keyAt(position - getHeadersCount() - getRealCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getRealCount() + mHeaders.size() + mFooters.size();
    }

    public int getHeadersCount() {
        return mHeaders.size();
    }

    public int getFootersCount() {
        return mFooters.size();
    }

    public int getRealCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (mHeaders.get(viewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else if (mFooters.get(viewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
        }

        public static ViewHolder createViewHolder(Context context, View view) {
            ViewHolder viewHolder = new ViewHolder(context, view);
            return viewHolder;
        }

        public void setText(@IdRes int id, String str) {
            textView = (TextView) itemView.findViewById(id);
            textView.setText(str);
        }

        public View getView(@IdRes int viewId) {
            View view = itemView.findViewById(viewId);
            return view;
        }

        public void setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
            View view = itemView.findViewById(viewId);
            view.setOnClickListener(listener);
        }
    }
}
