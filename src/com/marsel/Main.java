package com.marsel;

import com.marsel.design.Colors;
import com.marsel.solvers.BranchAndBound;
import com.marsel.utils.Knapsack;

public class Main {

    private long start, stop;

    public void start() {
        start = System.currentTimeMillis(); // start timing
    }

    public void stop() {
        stop = System.currentTimeMillis(); // stop timing
    }

    public long Time() {
        return stop - start;
    }

    public String end() {
        return Colors.CYAN + "It took " + Long.toString(Time()) + " [ms]." + Colors.RESET; // print execution time
    }

    public static void main(String[] args) {

        final int capacity = 10;
        int[] weight = {7, 8, 6, 4, 3, 9};
        int[] value = {75, 150, 250, 35, 10, 100};

//        final int capacity = 30;
//        int[] weight = {9, 13, 3, 5, 7, 8, 10, 3, 9 , 6};
//        int[] value  = {116, 103, 84, 83, 54, 72, 89, 103, 43, 115};

//        final int capacity = 90;
//        int[] weight = {9, 13, 3, 5, 7, 8, 10, 3, 9 , 6, 11, 7, 15, 5, 9, 12, 17, 13, 4, 7, 12, 8, 9, 17, 18, 2, 3, 11, 28, 15, 12, 16};
//        int[] value  = {116, 103, 84, 83, 54, 72, 89, 103, 43, 115, 121, 90, 133, 60, 70, 122, 155, 75, 44, 56, 78, 78, 145, 51, 100, 13, 30, 66, 120, 125, 90, 150};

        Main timer = new Main();
        Knapsack bag = new Knapsack(weight, value, capacity);
        BranchAndBound bnb = new BranchAndBound(bag.getItems(), capacity);

        timer.start();
        bnb.solve();
        timer.stop();

        System.out.println(timer.end());
    }
}