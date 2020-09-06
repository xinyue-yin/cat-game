package model;

import model.exceptions.AnimalIsDeadException;

import java.util.Observable;

public class Animal extends Observable {
    protected int age;
    protected String name;
    protected boolean died;
    protected String color;
    public static int maxAge;

    public Animal(String c) {
        age = 0;
        died = false;
        color = c;
        maxAge = 20;
        addObserver(new AnimalObserver());
    }

    //GETTERS:
    //EFFECTS: returns the name of this
    public String getName() {
        return name;
    }

    //EFFECTS: returns the color of this
    public String getColor() {
        return color;
    }

    //EFFECTS: returns the age of this
    public Integer getAge() {
        return age;
    }

    //EFFECTS: returns true if this is dead
    public Boolean getDied() {
        return died;
    }

    //SETTERS:
    //MODIFIES: this
    //EFFECTS: Sets the name of this to n and println
    public void naming(String n) {
        setName(n);
        System.out.println("You named it " + n + "!");
    }

    //EFFECTS: Sets name
    public void setName(String n) {
        name = n;
    }

    //EFFECTS: Sets the age
    public void setAge(int a) {
        if (a < maxAge) {
            age = a;
        } else {
            age = maxAge;
            setDied(true);
        }
    }

    //EFFECTS: Sets died
    public void setDied(Boolean b) {
        died = b;
    }

    //REQUIRES: f must be a positive integer
    //MODIFIES: this
    //EFFECTS: feed this f tons of food
    public void feed(int f) throws AnimalIsDeadException {
        if ((age + f) < maxAge) {
            System.out.println("You have fed " + name + " " + f + " tons of food.");
            age = age + f;
            setChanged();
            notifyObservers("feeding");
        } else {
            setDied(true);
            throw new AnimalIsDeadException();
        }
    }

    //EFFECTS: prints the age of this
    public void printAge() throws AnimalIsDeadException {
        if (died == true) {
            throw new AnimalIsDeadException();
        } else {
            System.out.println(name + " is now " + age + " years old.");
        }
    }
}
