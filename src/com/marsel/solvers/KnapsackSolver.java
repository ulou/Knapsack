package com.marsel.solvers;

import com.marsel.design.Colors;
import com.marsel.utils.Item;
import com.marsel.utils.Node;

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

    public int getListValue() {
        int temp = 0;
        for (Item x : items)
            temp += x.getValue();
        return temp;
    }

    public abstract void solve();

    public void printSolution(Node node) {
        if (items.size() < 50) {
            System.out.println(Colors.YELLOW + "All items list: " + Colors.RESET);
            for (Item x : items)
                System.out.println(x.getWeight() + " " + x.getValue());
        }

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "Solved list: " + Colors.RESET);
        for (Item x : node.takenItems)
            System.out.println(x.getWeight() + " " + x.getValue());

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "All items list value: " + getListValue() + Colors.RESET);
        System.out.println(Colors.YELLOW + "Solved list value: " + node.getValue() + Colors.RESET);
        System.out.println("-----------------------------------");
        items.clear();
        node.takenItems.clear();
    }
}
