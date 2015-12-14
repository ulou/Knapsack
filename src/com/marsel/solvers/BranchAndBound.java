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
        Collections.sort(items); // sortujemy liste nierosnąco (descending order)

        Node best = new Node(); // "lepszy" węzeł, metoda Best-First Search
        Node root = new Node(); // korzeń

        root.bound(this.items, this.capacity); // obliczamy granice(ograniczenia) dla wartośći rozwiązania, które możemy uzyskać z potomków danego węzła

        Queue<Node> queue = new LinkedList<Node>(); // kolejka priorytetowa
        queue.add(root); // dodajemy korzen drzewa do kolejki

        while (queue.size() != 0) {
            Node node = queue.poll();


            if (node.bound > best.value && node.level < this.items.size() - 1) { // węzeł jest obiecujący
                Node with = new Node(node);

                Item item = this.items.get(node.getLevel());
                with.weight += item.weight;

                if (with.weight <= this.capacity) {
                    with.takenItems.add(this.items.get(node.level));
                    with.value += item.value;
                    with.bound(this.items, this.capacity);

                    if (with.value > best.value)
                        best = with;
                    if (with.bound > best.value)
                        queue.add(with);
                }

                Node without = new Node(node);
                without.bound(this.items, this.capacity);

                if (without.bound > best.value)
                    queue.add(without);
            }
        }

        printSolution(best);
    }

}
