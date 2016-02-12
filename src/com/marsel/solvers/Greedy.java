package com.marsel.solvers;

import com.marsel.utils.Item;

import java.util.Collections;
import java.util.List;

/**
 * Created by Marcel on 13.12.2015.
 */
public class Greedy extends KnapsackSolver {
    public Greedy(List<Item> items, int capacity) {
        super(items, capacity);
    }

    public void solve() {
        Collections.sort(items);

        //noinspection Convert2streamapi
        for (Item x : items) {
            if (solvedListWeight + x.getWeight() <= this.capacity) {
                solvedListWeight += x.getWeight();
                solvedList.add(x);
                solvedListValue += x.getValue();
            }
        }
        printSolution();
    }

}
