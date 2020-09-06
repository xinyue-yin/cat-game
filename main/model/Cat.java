package model;


import model.exceptions.AnimalIsDeadException;

public class Cat extends Animal {

    public Cat(String c) {
        super(c);
        maxAge = 8;
        System.out.println("hey");
    }

    //MODIFIES: this
    //EFFECTS: output to screen
    public void play() throws AnimalIsDeadException {
        if (this.getDied() == true) {
            throw new AnimalIsDeadException();
        } else {
            System.out.println(this.getName() + " scratched you.");
        }
        setChanged();
        notifyObservers("playing");
    }

    //EFFECTS: Lets this meow.
    public void meow() {
        if (died == false) {
            System.out.println(name + " has ignored you.");
        } else {
            System.out.println(name + " meowed at you from heaven.");
        }
    }

}
