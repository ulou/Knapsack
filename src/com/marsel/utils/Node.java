package com.marsel.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcel on 11.12.2015.
 */
public class Node {
    private int level; // deep level
    private List<Item> takenItems; // solution, list of items
    private int value; // node value
    private int weight; // node weight
    private double bound; // upper bound

    public Node() {
        this.takenItems = new ArrayList<Item>();
    }

    public Node(Node parent) {
        this.level = parent.level + 1;
        this.takenItems = new ArrayList<>(parent.takenItems);
        this.value = parent.value;
        this.weight = parent.weight;
        this.bound = parent.bound;
    }

    public void bound(List<Item> items, int capacity) {
        double tempWeight = this.weight;
        this.bound = this.value;
        Item tempItem = null;

        for (int i = level; i < items.size(); i++) {
            tempItem = items.get(i);
            if (tempWeight + tempItem.getWeight() > capacity)
                break;

            tempWeight += tempItem.getWeight();
            this.bound += tempItem.getValue();
        }

        if (tempItem != null)
            this.bound += (capacity - tempWeight) * tempItem.getRatio();

    }

    public double getBound() {
        return bound;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Item> getTakenItems() {
        return takenItems;
    }

    public int getLevel() {
        return level;
    }


}
