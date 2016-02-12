package com.marsel.utils;

/**
 * Created by Marcel on 11.12.2015.
 */
public class Item implements Comparable {
    public int weight;
    public int value;
    public float ratio;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = (float) value / (float) weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
    public float getRatio() {
        return ratio;
    }

    // Comparator descending order
    @Override
    public int compareTo(Object o) {
        Item g2 = (Item) o;

        if (this.ratio > g2.ratio)
            return -1;
        if (this.ratio < g2.ratio)
            return 1;
        return 0;
    }

}
