package com.marsel;

/**
 * Created by Marcel on 11.12.2015.
 */
public class Item implements Comparable{
    public String name;
    public int weight;
    public int value;
    public float ratio;

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.ratio = (float) value / (float) weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getRatio() {
        return ratio;
    }

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
