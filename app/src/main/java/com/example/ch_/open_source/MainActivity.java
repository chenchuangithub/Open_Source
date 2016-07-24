package com.example.ch_.open_source;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ch_.open_source.delegate.CatAdapterDelegate;
import com.example.ch_.open_source.delegate.DogAdapterDelegate;
import com.example.ch_.open_source.delegate.TigerAdapterDelegate;
import com.example.ch_.open_source.holder_c.HeaderAndFooterWrapper;
import com.example.ch_.open_source.holder_c.MyAdapter;
import com.example.ch_.open_source.library.AdapterDelegateManager;
import com.example.ch_.open_source.model.Cat;
import com.example.ch_.open_source.model.DisplayItem;
import com.example.ch_.open_source.model.Dog;
import com.example.ch_.open_source.model.Tiger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(createManager());
        mAdapter.setItems(getAnimals());
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<DisplayItem> getAnimals(){
        List<DisplayItem> animals = new ArrayList<>();
        animals.add(new Dog("this is type:"));
        animals.add(new Dog("this is type:"));
        animals.add(new Dog("this is type:"));
        animals.add(new Cat("this is type:"));
        animals.add(new Cat("this is type:"));
        animals.add(new Cat("this is type:"));
        animals.add(new Tiger("this is type:"));
        animals.add(new Tiger("this is type:"));
        animals.add(new Tiger("this is type:"));
        return animals;
    }
    private AdapterDelegateManager<List<DisplayItem>> createManager(){
        AdapterDelegateManager<List<DisplayItem>> manager = new AdapterDelegateManager<>();
        manager.addDelegate(new CatAdapterDelegate(this));
        manager.addDelegate(new DogAdapterDelegate(this));
        manager.addDelegate(new TigerAdapterDelegate(this));
        return manager;
    }
}
