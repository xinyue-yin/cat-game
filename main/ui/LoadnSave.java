package ui;

import model.Animal;
import model.Eagle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadnSave {
    public LoadnSave() {

    }

    //EFFECTS: load the existing Eagle
    public Animal load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("save"));
        Animal c = new Eagle(lines.get(0));
        c.setAge(Integer.parseInt(lines.get(1)));
        c.setName(lines.get(2));
        c.setDied(Boolean.parseBoolean(lines.get(3)));
        return c;
    }

    //EFFECTS: save a into file save
    public void save(Animal a) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("save","UTF-8");
        writer.println(a.getColor());
        writer.println(a.getAge());
        writer.println(a.getName());
        writer.println(a.getDied());
        writer.close();
    }

    //EFFECTS: clears the file
    public void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("save");
        writer.print("");
        writer.close();
    }




/*    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("save"));;
        PrintWriter writer = new PrintWriter("output","UTF-8");
        lines.add("Trey Coordinator");
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("Name: " + partsOfLine.get(0) + " ");
            System.out.println("Role: " + partsOfLine.get(1));
            writer.println(line);
        }
        writer.close(); //note -- if you miss this, the file will not be written at all.
    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }*/

}
