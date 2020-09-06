package ui;

import model.Eagle;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadnSaveTest {

    @Test
    public void testLoadnSave() throws IOException {
        LoadnSave ls = new LoadnSave();
        Eagle e = new Eagle("Grey");
        e.setName("G");
        e.setAge(2);
        ls.save(e);
        Eagle e1 = (Eagle) ls.load();
        assertEquals("Grey", e1.getColor());
        assertEquals("G", e1.getName());
        assertEquals(2, e1.getAge());
        assertEquals(false, e1.getDied());
    }

}
