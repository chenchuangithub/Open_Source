package com.example.ch_.open_source;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ch_.open_source.holder_c.HeaderAndFooterWrapper;
import com.example.ch_.open_source.holder_c.MyAdapter;
import com.example.ch_.open_source.model.Cat;
import com.example.ch_.open_source.model.DisplayItem;
import com.example.ch_.open_source.model.Dog;
import com.example.ch_.open_source.model.Tiger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this,getAnimals());
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<DisplayItem> getAnimals(){
        List<DisplayItem> animals = new ArrayList<>();
        animals.add(new Dog("xiaohei_dog"));
        animals.add(new Dog("xiaohuang_dog"));
        animals.add(new Dog("xiaohong_dog"));
        animals.add(new Cat("xiaohei_cat"));
        animals.add(new Cat("xiaohuang_cat"));
        animals.add(new Cat("xiaohong_cat"));
        animals.add(new Tiger("xiaohei_tiger"));
        animals.add(new Tiger("xiaohuang_tiger"));
        animals.add(new Tiger("xiaohong_tiger"));
        return animals;
    }
}
