package com.marsel.solvers;

import com.marsel.utils.Item;
import com.marsel.utils.Node;

import java.util.List;

/**
 * Created by Marcel on 14.12.2015.
 */
public class BruteForce extends KnapsackSolver {
    public BruteForce(List<Item> items, int capacity) {
        super(items, capacity);
    }

    public void solve() {
        Node best = new Node();
        int bestValue = 0;
        int bestPosition = 0;

        for (int i = 0; i < Math.pow(2, items.size()); i++) {
            int total = 0;
            int weight = 0;
            for (int j = 0; j < items.size(); j++) {
                //jeżeli bit "not included" to pomin"
                if (((i >> j) & 1) != 1)
                    continue;
                total += items.get(j).value;
                weight += items.get(j).weight;
            }
            if (weight <= capacity && total > bestValue) {
                bestPosition = i;
                bestValue = total;
            }
        }
        for (int j = 0; j < items.size(); j++) {
            // jeżeli bit pasuje, wtedy dodaj
            if (((bestPosition >> j) & 1) == 1) {
                best.takenItems.add(items.get(j));
                best.value += items.get(j).value;
            }
        }
        printSolution(best);
    }
}
