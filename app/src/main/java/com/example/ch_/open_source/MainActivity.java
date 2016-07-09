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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            list.add(i+"");
        }
        mAdapter = new MyAdapter(list);
        HeaderAndFooterWrapper adapterWrapper = new HeaderAndFooterWrapper(mAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.header,null,false);
        adapterWrapper.addHeaderView(view);
        TextView view1 = new TextView(this);
        view1.setText("haha");
        adapterWrapper.addHeaderView(view1);
        mRecyclerView.setAdapter(adapterWrapper);
    }
}
