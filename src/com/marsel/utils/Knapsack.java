package com.marsel.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcel on 11.12.2015.
 */
public class Knapsack {
    public List<Item> items;
    public int capacity;

    public Knapsack(int[] weight, int[] value, int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<Item>();
        for (int i = 0; i < weight.length; i++) {
            items.add(new Item("" + i, weight[i], value[i]));
        }
    }

    public Knapsack() {
        this.items = new ArrayList<Item>();
    }

    public List<Item> getItems() {
        return items;
    }

}
