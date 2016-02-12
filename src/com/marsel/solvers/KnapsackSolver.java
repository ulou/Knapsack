package com.marsel.solvers;

import com.marsel.design.Colors;
import com.marsel.utils.Item;
import com.marsel.utils.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcel on 11.12.2015.
 */
public abstract class KnapsackSolver {
    protected List<Item> solvedList;
    protected int solvedListValue;
    protected int solvedListWeight;
    protected List<Item> items;
    protected int capacity;

    public KnapsackSolver(List<Item> items, int capacity) {
        this.solvedList = new ArrayList<>();
        this.solvedListValue = 0;
        this.solvedListWeight = 0;
        this.items = items;
        this.capacity = capacity;
    }

    public abstract void solve();

    public int getListValue() {
        int temp = 0;
        for (Item x : items)
            temp += x.getValue();
        return temp;
    }

    public void printSolution() {
        if (items.size() < 50) {
            System.out.println(Colors.YELLOW + "All items list: " + Colors.RESET);
            for (Item x : items)
                System.out.println(x.getWeight() + " " + x.getValue());
        }

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "Solved list: " + Colors.RESET);
        for (Item x : solvedList)
            System.out.println(x.getWeight() + " " + x.getValue());

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "All items list value: " + getListValue() + Colors.RESET + "\n");
        System.out.println(Colors.YELLOW + "Solved list value: " + solvedListValue + Colors.RESET);
        System.out.println(Colors.YELLOW + "Solved list weight: " + solvedListWeight + Colors.RESET);
        System.out.println("-----------------------------------");
        items.clear();
        solvedList.clear();
    }

    public void printSolution(Node node) {
        if (items.size() < 50) {
            System.out.println(Colors.YELLOW + "All items list: " + Colors.RESET);
            for (Item x : items)
                System.out.println(x.getWeight() + " " + x.getValue());
        }

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "Solved list: " + Colors.RESET);
        for (Item x : node.getTakenItems())
            System.out.println(x.getWeight() + " " + x.getValue());

        System.out.println("-----------------------------------");
        System.out.println(Colors.YELLOW + "All items list value: " + getListValue() + Colors.RESET + "\n");
        System.out.println(Colors.YELLOW + "Solved list value: " + node.getValue() + Colors.RESET);
        System.out.println(Colors.YELLOW + "Solved list weight: " + node.getWeight() + Colors.RESET);
        System.out.println("-----------------------------------");
        items.clear();
        node.getTakenItems().clear();
    }


}
