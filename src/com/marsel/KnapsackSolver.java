package com.marsel;

import java.util.List;

/**
 * Created by Marcel on 11.12.2015.
 */
public abstract class KnapsackSolver {
    protected List<Item> items;
    protected int capacity;

    public KnapsackSolver(List<Item> items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

//    public int getTotalValue(){
//        int tempValue = 0;
//        for(Item x:items)
//            tempValue += x.getValue();
//        return tempValue;
//    }

}
