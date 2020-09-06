package ui.interaction;

import model.Cat;
import model.exceptions.AnimalIsDeadException;
import ui.LoadnSave;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class InteractCat implements InteractAnimal {
    static Scanner input = new Scanner(System.in);
    static Cat c1;
    static Scanner food = new Scanner(System.in);
    static LoadnSave ls = new LoadnSave();

    public InteractCat() {
    }

    //EFFECTS: load the save file or create a new Cat
    public void generate() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            c1 = (Cat) ls.load();
            System.out.println(c1.getName() + " welcomes you!");
            loop();
        } catch (Exception e) {
            c1 = new Cat("Orange");
            start();
        }
    }

    //MODIFIES: c1
    //EFFECTS: start
    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("A new " + c1.getColor() + " cat is born!");
        System.out.println("Give it a name:");
        c1.naming(input.nextLine());
        System.out.println(c1.getName() + " is staring at you.");
        loop();
    }

    //EFFECTS: loop the project or break based on the user's save
    public void loop() throws FileNotFoundException, UnsupportedEncodingException {
        while (true) {
            System.out.println("To interact, type: feed, meow, getAge, or quit.");
            String interact = input.nextLine();
            if (interact.equals("quit")) {
                System.out.println("Save?");
                String save = input.nextLine();
                if (save.equals("yes")) {
                    ls.save(c1);
                    break;
                } else {
                    ls.clear();
                    break;
                }
            } else {
                testForException(interact);
            }
        }
    }

    //EFFECTS: check for exceptions
    public void testForException(String interact) {
        try {
            choices(interact);
        } catch (AnimalIsDeadException e) {
            System.out.println("Animal is dead");
            c1.setAge(c1.maxAge);
        } finally {
            System.out.println("Action done.");
        }
    }

    //EFFECTS: perform different interactions based on interact
    public void choices(String interact) throws AnimalIsDeadException {
        switch (interact) {
            case "feed":
                System.out.println("How much food do you want to feed it?");
                c1.feed(food.nextInt());
                break;
            case "meow":
                c1.meow();
                break;
            case "getAge":
                c1.printAge();
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}

