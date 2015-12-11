package com.marsel;

import java.util.*;

/**
 * Created by Marcel on 11.12.2015.
 * Best-First Search with BnB
 */
public class BranchAndBound extends KnapsackSolver {

    public BranchAndBound(List<Item> items, int capacity) {
        super(items, capacity);
    }

    public void solve(){
        Collections.sort(items);

        Node best = new Node();
        Node root = new Node();

        root.bound(this.items, this.capacity);

        Queue<Node> queue = new LinkedList<Node>();//Queue<Node>();
        queue.add(root);

        while (queue.size() != 0){
            Node node = queue.poll();


            if(node.bound > best.value && node.level < this.items.size() - 1){
                Node with = new Node(node);

                Item item = this.items.get(node.getLevel());
                with.weight += item.weight;

                if(with.weight <= this.capacity){
                    with.takenItems.add(this.items.get(node.level));
                    with.value += item.value;
                    with.bound(this.items, this.capacity);

                    if(with.value > best.value)
                        best = with;
                    if(with.bound > best.value)
                        queue.add(with);
                }

                Node without = new Node(node);
                without.bound(this.items, this.capacity);

                if(without.bound > best.value)
                    queue.add(without);
            }
        }

        show(best);
    }

    public void show(Node node){

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        System.out.println(ANSI_GREEN + "List: " + ANSI_RESET);
        for (Item x:items)
            System.out.println(x.getWeight() + " " + x.getValue());

        System.out.println(ANSI_GREEN + "Solved list: " + ANSI_RESET);
        for (Item x:node.takenItems)
            System.out.println(x.getWeight() + " " + x.getValue());

        System.out.println(ANSI_GREEN + "Solved list total value: " + node.getValue() + ANSI_RESET);
    }
}
