package ui.interaction;

import model.exceptions.AnimalIsDeadException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface InteractAnimal {
    void generate() throws FileNotFoundException, UnsupportedEncodingException;

    void start() throws FileNotFoundException, UnsupportedEncodingException;

    void loop() throws FileNotFoundException, UnsupportedEncodingException;

    void testForException(String string);

    void choices(String s) throws AnimalIsDeadException;


}
