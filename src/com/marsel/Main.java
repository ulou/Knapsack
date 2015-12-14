package com.marsel;

import com.marsel.design.Colors;
import com.marsel.solvers.BranchAndBound;
import com.marsel.solvers.Dynamic;
import com.marsel.solvers.Greedy;
import com.marsel.utils.Item;
import com.marsel.utils.Knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        return Colors.YELLOW + "Algorithm took: " + Long.toString(Time()) + " [ms].\n\n" + Colors.RESET; // print execution time
    }

    public boolean loadFile(String fileName) {
        Scanner fileScanner;

        try {
            fileScanner = new Scanner(new File("src/inputs/" + fileName));
            this.numberOfItems = fileScanner.nextInt();

            while (fileScanner.hasNext()) {
                tempItems.add(new Item(fileScanner.nextInt(), fileScanner.nextInt()));
        }
        } catch (FileNotFoundException e) {
//            System.out.println(Colors.RED + "File " + fileName + " doesn't exist!" + Colors.RESET);
            return false;
        } catch (InputMismatchException e) {
            System.out.println(Colors.RED + "File " + fileName + " has the wrong format!" + Colors.RESET);
            return false;
        } catch (NoSuchElementException e) {
            System.out.println(Colors.RED + "File " + fileName + " has the wrong format!" + Colors.RESET);
            return false;
        }
        return true;
    }

    public int showMenu() {
        int choose;
        System.out.print("-------- " + Colors.WHITE + "Knapsack problem"
                        + Colors.RESET + " -------\n" + Colors.BLUE
                        + "1. Solve using B&B\n"
                        + "2. Solve using Dynamic\n"
                        + "3. Solve using Greedy\n"
                        + "4. Solve using Approximation\n\n"
                        + "0. Exit\n" + Colors.RESET
                        + "-----------------------------------\n"
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
        try {
            choose = scan.nextInt();
            return results.get(choose - 1);
        } catch (Exception e) {
            System.out.println(Colors.RED + "Wrong choice. Try again!" + Colors.RESET);
        }
        return "";
    }

    public int setCapacity(int capacity) {
        Scanner scan = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.print("Input knapsack capacity: ");
        capacity = scan.nextInt();
        System.out.println("-----------------------------------");
        return capacity;
    }

    public static void main(String[] args) throws IndexOutOfBoundsException, NullPointerException, OutOfMemoryError {

        int capacity = 0;
        Main timer = new Main();

        while (true) {
            try {
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
                        if (timer.loadFile(timer.files())) {
                            capacity = timer.setCapacity(capacity);
                            Knapsack bag = new Knapsack(timer.tempItems, capacity);
                            Dynamic dynamic = new Dynamic(bag.getItems(), capacity);

                            timer.start();
                            dynamic.solve();
                            timer.stop();

                            System.out.println(timer.end());
                        }
                        break;
                    case 3:
                        if (timer.loadFile(timer.files())) {
                            capacity = timer.setCapacity(capacity);
                            Knapsack bag = new Knapsack(timer.tempItems, capacity);
                            Greedy greedy = new Greedy(bag.getItems(), capacity);

                            timer.start();
                            greedy.solve();
                            timer.stop();

                            System.out.println(timer.end());
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(Colors.RED + "Wrong choice. Try again!" + Colors.RESET);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Colors.RED + "Wrong input data. Try again!" + Colors.RESET);
            } catch (OutOfMemoryError e) {
                System.out.println(Colors.RED + "Out of memory. Capacity is too big. Try again!" + Colors.RESET);
            }
        }
    }
}