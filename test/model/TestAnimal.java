package model;

import model.exceptions.AnimalIsDeadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestAnimal {
    private Animal a;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void before() {
        a = new Cat("Black");
        a.naming("Test");
    }

    @Test
    public void testConstruct() {
        assertEquals("Black", a.getColor());
        assertEquals(0, a.getAge());
        assertEquals("Test", a.getName());
    }

    @Test
    public void testGetters() {
        assertEquals("Test", a.getName());
        assertEquals(0, a.getAge());
        assertEquals("Black", a.getColor());
        assertFalse(a.getDied());
    }

    @Test
    public void testFeedAlive() {
        System.setOut(new PrintStream(output));
        try {
            a.feed(3);
        } catch (AnimalIsDeadException e) {
            fail();
        }
        assertEquals(3, a.getAge());
        assertEquals("You have fed Test 3 tons of food." + System.lineSeparator() + "Finished feeding.",
                output.toString().trim());
    }

    @Test
    public void testFeedDied() {
        try {
            a.feed(30);
            fail();
        } catch (AnimalIsDeadException e) {}
    }

    @Test
    public void testPrintAgeAlive() {
        System.setOut(new PrintStream(output));
        a.setAge(2);
        try {
            a.printAge();
        } catch (AnimalIsDeadException e) {
            fail();
        }
        assertEquals("Test is now 2 years old.", output.toString().trim());
    }

    @Test
    public void testPrintAgeDied() {
        a.setDied(true);
        try {
            a.printAge();
            fail();
        } catch (AnimalIsDeadException e) {}
    }
}


