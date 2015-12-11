package com.marsel;

import com.marsel.design.Colors;
import com.marsel.solvers.BranchAndBound;
import com.marsel.utils.Item;
import com.marsel.utils.Knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private long start, stop;
    public int numberOfItems;
    public List<Item> tempItems = new ArrayList<Item>();

    public void start() {
        start = System.currentTimeMillis(); // start timing
    }

    public void stop() {
        stop = System.currentTimeMillis(); // stop timing
    }

    public long Time() {
        return (stop - start);
    }

    public String end() {
        return Colors.GREEN + "Algorithm took: " + Long.toString(Time()) + " [ms]." + Colors.RESET; // print execution time
    }

    public boolean loadFile(String fileName) {
        Scanner fileScanner;

        try {
            fileScanner = new Scanner(new File("src/inputs/" + fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " doesn't exist!");
            e.printStackTrace();
            return false;
        }

        this.numberOfItems = fileScanner.nextInt();

        while (fileScanner.hasNextInt()) {
            tempItems.add(new Item(fileScanner.nextInt(), fileScanner.nextInt()));
        }
        return true;
    }

    public int showMenu() {
        int choose;
        System.out.print(Colors.BLUE + "-------- Knapsack algorithm --------\n"
                        + "1. Solve knapsack - from file\n"
                        + "2. Solve knapsack - using random data\n"
                        + "0. Exit\n"
                        + "-----------------------------------\n" + Colors.RESET
                        + "Choose: "
        );
        Scanner scan = new Scanner(System.in);
        choose = scan.nextInt();
        return choose;
    }

    public String files() {
        List<String> results = new ArrayList<String>();
        int choose;

        Scanner scan = new Scanner(System.in);

        File[] files = new File("src/inputs/").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.

        System.out.println("-----------------------------------");

        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                results.add(file.getName());
            }
        }

        for (String x : results)
            System.out.println(Colors.BLUE + (results.indexOf(x) + 1) + ". " + x.toString() + Colors.RESET);

        System.out.println("-----------------------------------");
        System.out.print("Choose file: ");
        choose = scan.nextInt();

        return results.get(choose - 1);
    }

    public int setCapacity(int capacity) {
        Scanner scan = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.print("Input knapsack capacity: ");
        capacity = scan.nextInt();
        System.out.println("-----------------------------------");
        return capacity;
    }

    public static void main(String[] args) throws IndexOutOfBoundsException, NullPointerException {

        int capacity = 0;
        Main timer = new Main();

        while (true) {
            switch (timer.showMenu()) {
                case 1:
                    if (timer.loadFile(timer.files())) {
                        capacity = timer.setCapacity(capacity);
                        Knapsack bag = new Knapsack(timer.tempItems, capacity);
                        BranchAndBound bnb = new BranchAndBound(bag.getItems(), capacity);

                        timer.start();
                        bnb.solve();
                        timer.stop();

                        System.out.println(timer.end());
                    }
                    break;
                case 2:
                    Random rand = new Random();
                    for (int i = 0; i < 100; i++) {
                        System.out.println(rand.nextInt(20) + " " + rand.nextInt(200));
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice. Try again!");
            }
        }
    }
}