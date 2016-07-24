package com.example.ch_.open_source;

import com.example.ch_.open_source.library.AbsDelegationAdapter;
import com.example.ch_.open_source.library.AdapterDelegate;
import com.example.ch_.open_source.library.AdapterDelegateManager;
import com.example.ch_.open_source.model.DisplayItem;

import java.util.List;

/**
 * Created by ch_ on 2016/7/10.
 */
public class MainAdapter extends AbsDelegationAdapter<List<DisplayItem>> {
    public MainAdapter(AdapterDelegateManager<List<DisplayItem>> manager){
        super(manager);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
