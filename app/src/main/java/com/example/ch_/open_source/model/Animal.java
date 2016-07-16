package com.example.ch_.open_source.model;

/**
 * Created by ch_ on 2016/7/10.
 */
public class Animal implements DisplayItem{
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
