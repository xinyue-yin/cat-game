package ui.interaction;

import model.Eagle;
import model.exceptions.AnimalIsDeadException;
import ui.LoadnSave;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractEagle implements InteractAnimal {
    Scanner input = new Scanner(System.in);
    Eagle e1;
    Scanner food = new Scanner(System.in);
    LoadnSave ls = new LoadnSave();

    public InteractEagle(){}

    //EFFECTS: load the save file or create a new Eagle
    public void generate() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            e1 = (Eagle) ls.load();
            System.out.println(e1.getName() + " welcomes you!");
            loop();
        } catch (Exception e) {
            e1 = new Eagle("Orange");
            start();
        }
    }

    //MODIFIES: c1
    //EFFECTS: start
    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("A new " + e1.getColor() + " eagle is born!");
        System.out.println("Give it a name:");
        e1.naming(input.nextLine());
        System.out.println(e1.getName() + " is staring at you.");
        loop();
    }

    //EFFECTS: loop the project or break based on the user's save
    public void loop() throws FileNotFoundException, UnsupportedEncodingException {
        while (true) {
            System.out.println("To interact, type: feed, fly, getAge, or quit.");
            String interact = input.nextLine();
            if (interact.equals("quit")) {
                System.out.println("Save?");
                String save = input.nextLine();
                if (save.equals("yes")) {
                    ls.save(e1);
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
            e1.setAge(Eagle.maxAge);
        } finally {
            System.out.println("Action done.");
        }
    }

    //EFFECTS: perform different interactions based on interact
    public void choices(String interact) throws AnimalIsDeadException {
        switch (interact) {
            case "feed":
                System.out.println("How much food do you want to feed it?");
                try {
                    e1.feed(food.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                }
                break;
            case "fly":
                e1.fly();
                break;
            case "getAge":
                e1.printAge();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
