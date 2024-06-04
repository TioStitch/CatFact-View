package de.tiostitch.catfact.view;

import javax.swing.*;

public final class Main
extends JFrame {

    private Main() {
        setTitle("CatFact View - by TioStitch");
        setSize(498, 278);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new Panel());

        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main();
    }
}
