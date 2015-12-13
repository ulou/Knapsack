package com.marsel.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcel on 11.12.2015.
 */
public class Node {
    public int level; // poziom drzewa
    public List<Item> takenItems; // zabrane przedmioty (rozwiazania)
    public int value; // wartosc całej gałęzi drzewa (wartosc przedmiotów)
    public int weight; // waga
    public double bound; // górna granica

    public Node() {
        this.takenItems = new ArrayList<Item>();
    }

    public Node(Node parent) {
        this.level = parent.level + 1;
        this.takenItems = new ArrayList<Item>(parent.takenItems);
        this.value = parent.value;
        this.weight = parent.weight;
        this.bound = parent.bound;
    }

    // funkcja odpowiadająca za policzenie granicy(ograniczenie wartości rozwiązania jakie możemy uzyskać po rozwinięciu danego wezła)
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

    public void setBound(double bound) {
        this.bound = bound;
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

    public void setTakenItems(List<Item> takenItems) {
        this.takenItems = takenItems;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

//    @Override @Supression('unused')
//    public int compareTo(Node o) {
//        return (int) (o.bound - this.bound);
//    }
}
