package org.toys;

import org.toys.exceptions.EmptyStoreException;
import org.toys.models.Shop;
import org.toys.models.Toy;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Scanner sc = new Scanner(System.in);

        Command command = selectCommand(sc);

        switch (command) {
            case SHOW_ALL -> shop.printToys();
            case ADD_TOY -> askToyAndAdd(sc, shop);
            case WIN_TOY -> {
                try {
                    Toy toy = shop.winToy();
                    System.out.println("You won " + toy.getName());
                } catch (EmptyStoreException ignore) {
                    System.out.println("At the moment we don't have toys.");
                }
            }
            case SHOW_WON_TOYS -> shop.printWonToys();
        }
    }

    public static Command selectCommand(Scanner sc) {
        System.out.println("""
                Enter number of the command:
                1. Show all toys.
                2. Add toy.
                3. Win toy.
                4. Show the won toys.""");

        while (true) {
            Integer val;
            try {
                val = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException ignore) {
                sc.nextLine();
                System.out.println("Invalid command format");
                continue;
            }

            for (Command c : Command.values()) {
                if (c.getCommand().equals(val)) {
                    return c;
                }
            }

            System.out.println("Command not found");
        }
    }

    public static void askToyAndAdd(Scanner sc, Shop shop) {
        System.out.print("Enter toy name: ");
        String name = sc.nextLine();

        System.out.print("Enter toy weight (greater than 100): ");
        int weight;
        while (true) {
            try {
                weight = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException ignore) {
                sc.nextLine();
                System.out.println("Invalid weight format");
                continue;
            }

            if (weight <= 100) {
                System.out.println("Weight must be greater than 100");
            } else {
                break;
            }
        }

        shop.addToy(name, weight);
    }
}