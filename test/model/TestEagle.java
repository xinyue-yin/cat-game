package model;

import model.exceptions.AnimalIsDeadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestEagle {
    private Eagle e;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void before() {
        e = new Eagle("grey");
        e.setName("Test");
    }

    @Test
    void testFlyAlive() {
        System.setOut(new PrintStream(output));
        e.fly();
        assertEquals("Flap, Flap.", output.toString().trim());
    }

    @Test
    void testFlyDied() {
        System.setOut(new PrintStream(output));
        e.setAge(30);
        e.fly();
        assertEquals("Test is flying in heaven now.", output.toString().trim());
    }


    @Test
    void testPlayAlive() {
        System.setOut(new PrintStream(output));
        try {
            e.play();
        } catch (AnimalIsDeadException e) {
            fail();
        }
        assertEquals("You played throw and catch." + System.lineSeparator() + "Finished playing.",
                output.toString().trim());
    }

    @Test
    void testPlayDied() {
        e.setAge(Cat.maxAge);
        try {
            e.play();
            fail();
        } catch (AnimalIsDeadException e) {}
    }

    @Test
    void testFlyWithInt() {
        System.setOut(new PrintStream(output));
        e.fly(20);
        assertEquals("Test flew 20 miles and came back.", output.toString().trim());
    }
}

