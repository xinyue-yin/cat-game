package model;

import model.exceptions.AnimalIsDeadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCat {
    private Cat c;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void before() {
        c = new Cat("grey");
        c.setName("Test");
    }

    @Test
    void testMeowAlive() {
        System.setOut(new PrintStream(output));
        c.setAge(2);
        c.meow();
        assertEquals("Test has ignored you.", output.toString().trim());
    }

    @Test
    void testMeowDied() {
        System.setOut(new PrintStream(output));
        c.setAge(30);
        c.meow();
        assertEquals("Test meowed at you from heaven.", output.toString().trim());
    }

    @Test
    void testPlayAlive() {
        System.setOut(new PrintStream(output));
        try {
            c.play();
        } catch (AnimalIsDeadException e) {
            fail();
        }
        assertEquals("Test scratched you." + System.lineSeparator() + "Finished playing.",
                output.toString().trim());
    }

    @Test
    void testPlayDied() {
        c.setAge(Cat.maxAge);
        try {
            c.play();
            fail();
        } catch (AnimalIsDeadException e) {}
    }
}
