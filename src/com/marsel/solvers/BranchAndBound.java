package com.marsel.solvers;

import com.marsel.utils.Item;
import com.marsel.utils.Node;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Marcel on 11.12.2015.
 * Best-First Search with BnB
 */
public class BranchAndBound extends KnapsackSolver {

    public BranchAndBound(List<Item> items, int capacity) {
        super(items, capacity);
    }

    public void solve() {
        Collections.sort(items); // sort list in descending order

        Node best = new Node(); // better node, Best-First Search
        Node root = new Node(); // root

        root.bound(this.items, this.capacity); // root bound
        Queue<Node> queue = new LinkedList<>(); // priority queue
        queue.add(root);

        while (queue.size() != 0) {
            Node node = queue.poll();

            if (node.getBound() > best.getValue() && node.getLevel() < this.items.size() - 1) {
                Node with = new Node(node);

                Item item = this.items.get(node.getLevel());
                with.setWeight(with.getWeight() + item.weight);

                if (with.getWeight() <= this.capacity) {
                    with.getTakenItems().add(this.items.get(node.getLevel()));
                    with.setValue(with.getValue() + item.value);
                    with.bound(this.items, this.capacity);

                    if (with.getValue() > best.getValue())
                        best = with;
                    if (with.getBound() > best.getValue())
                        queue.add(with);
                }

                Node without = new Node(node);
                without.bound(this.items, this.capacity);

                if (without.getBound() > best.getValue())
                    queue.add(without);
            }
        }

        printSolution(best);
    }
}
