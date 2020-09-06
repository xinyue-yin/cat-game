package model;

import model.exceptions.AnimalIsDeadException;

public class Eagle extends Animal {

    public Eagle(String c) {
        super(c);
        maxAge = 12;
    }

    //MODIFIES: this
    //EFFECTS: output to screen
    public void play() throws AnimalIsDeadException {
        if (this.getDied() == true) {
            throw new AnimalIsDeadException();
        } else {
            System.out.println("You played throw and catch.");
        }
        setChanged();
        notifyObservers("playing");
    }

    //EFFECTS: println
    public void fly() {
        if (died == false) {
            System.out.println("Flap, Flap.");
        } else {
            System.out.println(name + " is flying in heaven now.");
        }
    }

    //EFFECTS: println
    public void fly(int i) {
        System.out.println(this.getName() + " flew " + i + " miles and came back.");
    }
}
