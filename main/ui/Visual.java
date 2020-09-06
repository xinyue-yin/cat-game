package ui;


import model.Cat;
import model.exceptions.AnimalIsDeadException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class Visual extends JFrame implements ActionListener {
    private JSplitPane splitPane;
    private JPanel top;
    private JPanel bottom;
    private JLabel label;
    private JTextField field;
    private JLabel imageLabel = new JLabel();
    Cat interactCat = new Cat("Black");

    public Visual() {
        super("The Cat");
        splitPane = new JSplitPane();
        top = new JPanel();
        bottom = new JPanel();
        interactCat.setName("Victor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sets splitPane
        setPreferredSize(new Dimension(390, 700));
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(600);
        splitPane.setTopComponent(top);
        splitPane.setBottomComponent(bottom);

        //Buttons
        JButton btn = new JButton("Name");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        JButton btn2 = new JButton("Meow");
        btn2.setActionCommand("meow");
        btn2.addActionListener(this);
        JButton btn3 = new JButton("Feed");
        btn3.setActionCommand("feed");
        btn3.addActionListener(this);

        label = new JLabel("Victor");
        field = new JTextField(5);

        //sets top panel
        top.setLayout(new BorderLayout());
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                "Cat.gif"));
        imageLabel.setIcon(ii);
        top.add(imageLabel, java.awt.BorderLayout.CENTER);

        //sets bottom panel
        bottom.setLayout(new FlowLayout());
        bottom.add(label);
        bottom.add(field);
        bottom.add(btn);
        bottom.add(btn2);
        bottom.add(btn3);

        //BGM
        try {
            InputStream music = this.getClass().getResourceAsStream("music.wav");
            AudioStream audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println("Error");
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: response to different action events
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "myButton":
                naming();
                break;
            case "meow" :
                meow();
                break;
            default:
                feed();
                break;
        }
    }

    //EFFECTS: feeds the cat
    private void feed() {
        try {
            interactCat.feed(1);
            catMeow();
            JOptionPane.showMessageDialog(null, "You have fed " + interactCat.getName() + " 1 ton of food.");
        } catch (AnimalIsDeadException e1) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
                    "dead.gif"));
            imageLabel.setIcon(imageIcon);
            JOptionPane.showMessageDialog(null, interactCat.getName() + " is dead.");
        }
    }

    //EFFECTS: let the cat meow
    private void meow() {
        if (interactCat.getDied()) {
            JOptionPane.showMessageDialog(null, interactCat.getName() + " is dead.");
        } else {
            JOptionPane.showMessageDialog(null, interactCat.getName() + " has ignored you.");
        }
    }

    //MODIFIES: interactCat, label
    //EFFECTS: change the name of the cat
    private void naming() {
        interactCat.setName(field.getText());
        JOptionPane.showMessageDialog(null, "You named it " + interactCat.getName() + ".");
        label.setText(interactCat.getName());
    }

    public static void main(String[] args) {
        new Visual();
    }

    public void catMeow() {
        try {
            InputStream meow = this.getClass().getResourceAsStream("meow.wav");
            AudioStream audioStream = new AudioStream(meow);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
