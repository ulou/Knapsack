package com.marsel.solvers;

import com.marsel.utils.Item;
import com.marsel.utils.Node;

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

        Node node = new Node();

        //noinspection Convert2streamapi
        for (Item x : items) {
            if (node.weight + x.getWeight() <= this.capacity) {
                node.weight += x.getWeight();
                node.takenItems.add(x);
                node.value += x.getValue();
            }
        }

        printSolution(node);
    }
}
