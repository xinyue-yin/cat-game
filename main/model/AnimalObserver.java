package model;

import java.util.Observable;
import java.util.Observer;

public class AnimalObserver implements Observer {

    //EFFECTS: println
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Finished " + arg + ".");
    }
}
