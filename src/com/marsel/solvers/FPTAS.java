package com.marsel.solvers;

import com.marsel.utils.Item;
import com.marsel.utils.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marcel on 23.01.2016.
 */
public class FPTAS extends KnapsackSolver {

    public FPTAS(List<Item> items, int capacity) {
        super(items, capacity);
    }

    @Override
    public void solve() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input epsilon: ");
        float epsilon;
        epsilon = scanner.nextFloat();

        int maxValue = findMax(items);
        double scale = (epsilon * maxValue) / items.size();
        List<Item> scaledItems = new ArrayList<>();
        for (Item x : items)
            scaledItems.add(new Item(x.getWeight(), (int) (x.getValue() / scale)));
        boolean[] itemsWereChose = reverseDynamic(scaledItems, new Node());
        for (int i = 0; i < itemsWereChose.length; i++) {
            if (itemsWereChose[i]) {
                solvedList.add(items.get(i));
                solvedListValue += items.get(i).getValue();
                solvedListWeight += items.get(i).getWeight();
            }
        }

        printSolution();
    }

    public boolean[] reverseDynamic(List<Item> list, Node node) {
        int totalValue = 0;
        int tempK = 0;
        int tempY = 0;

        for (Item x : items)
            totalValue += x.getValue();
        int[][] table = new int[list.size() + 1][totalValue + 1];

        for (int i = 1; i < totalValue; i++) {
            table[0][i] = Integer.MAX_VALUE;
        }

        for (int y = 1; y <= totalValue; y++) {
            for (int k = 1; k <= list.size(); k++) {
                if (y - list.get(k - 1).getValue() < 0 || table[k - 1][y - list.get(k - 1).getValue()] == Integer.MAX_VALUE) {
                    table[k][y] = table[k - 1][y];
                } else {
                    table[k][y] = min(
                            table[k - 1][y],
                            table[k - 1][y - list.get(k - 1).getValue()] + list.get(k - 1).getWeight()
                    );
                }
                if (table[list.size()][y] <= capacity && table[list.size()][y] != 0) {
                    tempK = k;
                    tempY = y;
                }
            }
        }

        boolean[] solution = new boolean[list.size()];
        int tempCapacity = capacity;
        int y = tempY;
        boolean isDone = false;
        for (int k = tempK; k >= 1 && !isDone; k--) {
            if (table[k][y] != table[k - 1][y]) {
                solution[k - 1] = true;
                tempCapacity -= list.get(k - 1).getWeight();
                isDone = true;
                while (y > 0) {
                    if (table[k - 1][y] <= tempCapacity) {
                        isDone = false;
                        break;
                    }
                    y--;
                }
            }
        }
        for (int i = 0; i < solution.length; i++) {
            if (solution[i]) {
                solvedList.add(list.get(i));
            }
        }
        return solution;
    }


    public int findMax(List<Item> items) {
        int tmp = 0;
        for (Item x : items) {
            if (x.getValue() > tmp)
                tmp = x.getValue();
        }
        return tmp;
    }

    public int min(int a, int b) {
        if (a < b)
            return a;
        return b;
    }
}
