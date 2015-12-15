package com.marsel.solvers;

import com.marsel.design.Colors;
import com.marsel.utils.Item;
import com.marsel.utils.Node;

import java.util.Collections;
import java.util.List;

/**
 * Created by Marcel on 13.12.2015.
 */
public class Dynamic extends KnapsackSolver {

    private int[][] table;

    public Dynamic(List<Item> items, int capacity) {
        super(items, capacity);
    }

    public void solve() {
        Node node = new Node();
        this.fillTable();
        showTable();
        int col = this.capacity;

        for (int row = this.items.size(); row > 0; row--) {
            if (this.table[row][col] != this.table[row - 1][col]) {
                node.takenItems.add(this.items.get(row - 1));
                col -= this.items.get(row - 1).weight;
                node.value += this.items.get(row - 1).value;
            }
        }
        Collections.sort(node.takenItems);
        printSolution(node);
    }

    public void fillTable() {
        this.table = new int[this.items.size() + 1][this.capacity + 1];
        for (int row = 1; row <= this.items.size(); row++) {
            Item item = this.items.get(row - 1);
            for (int col = 0; col <= this.capacity; col++) {
                if (item.weight > col)
                    this.table[row][col] = this.table[row - 1][col];
                else
                    this.table[row][col] = Math.max(this.table[row - 1][col], this.table[row - 1][col - item.weight] + item.value);
            }
        }
    }

    public void showTable() {
        for (int i = 0; i < this.items.size() + 1; i++) {
            for (int j = 0; j < this.capacity + 1; j++) {
                System.out.format(Colors.GREEN + table[i][j] + Colors.GREEN + " ");
            }
            System.out.println();
        }
    }
}
