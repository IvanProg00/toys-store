package org.toys.models;

import org.toys.exceptions.EmptyStoreException;
import org.toys.storage.ShopStorage;
import org.toys.storage.UserStorage;

import java.util.List;
import java.util.Random;

public class Shop {
    public List<Toy> toys;
    public List<UserToy> userToys;

    public Shop() {
        this.toys = ShopStorage.getToys();
        this.userToys = UserStorage.getToys();
    }

    public void addToy(String toyName, Integer newWeight) {
        boolean found = false;

        for (Toy toy : toys) {
            if (toy.getName().equals(toyName)) {
                toy.setCount(toy.getCount() + 1);
                toy.setWeight(newWeight);
                found = true;
                break;
            }
        }

        if (!found) {
            int id = 0;
            if (!toys.isEmpty()) {
                id = toys.get(toys.size() - 1).getId();
            }

            toys.add(new Toy(++id, toyName, 1, newWeight));
        }

        ShopStorage.updateToys(toys);
    }


    private void addUserToy(String toyName) {
        boolean found = false;

        for (UserToy toy : userToys) {
            if (toy.getName().equals(toyName)) {
                toy.setCount(toy.getCount() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            int id = 0;
            if (!userToys.isEmpty()) {
                id = userToys.get(userToys.size() - 1).getId();
            }

            userToys.add(new UserToy(++id, toyName, 1));
        }

        UserStorage.updateToys(userToys);
    }

    public Toy winToy() throws EmptyStoreException {
        if (toys.isEmpty()) {
            throw new EmptyStoreException();
        }

        int totalWeight = 0;

        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        Random random = new Random();
        double rNum = random.nextDouble();
        double r = rNum * totalWeight;
        int i = 0;

        for (; i < toys.size(); i++) {
            r -= toys.get(i).getWeight();
            if (r < 0.) {
                break;
            }
        }

        Toy toy;
        if (i < toys.size()) {
            toy = toys.get(i);
        } else {
            toy = toys.get(toys.size() - 1);
        }

        toy.setCount(toy.getCount() - 1);
        if (toy.getCount() <= 0) {
            toys.remove(i);
        }
        ShopStorage.updateToys(toys);
        addUserToy(toy.getName());

        return toy;
    }

    public void printToys() {
        if (toys.isEmpty()) {
            System.out.println("We don't have toys!!!");
        } else {
            for (Toy toy : toys) {
                System.out.println(toy);
            }
        }
    }

    public void printWonToys() {
        if (userToys.isEmpty()) {
            System.out.println("At the moment you didn't win any toy.");
        } else {
            for (UserToy toy : userToys) {
                System.out.println(toy);
            }
        }
    }
}
