package de.tiostitch.catfact.view;

import com.google.gson.Gson;
import de.tiostitch.catfact.view.objects.CatFact;
import de.tiostitch.catfact.view.utils.ContentBuilder;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.Consumer;

public final class Panel
extends JPanel {

    private static final ImageIcon background = new ImageIcon("src/resources/black-cat.gif");
    private static ArrayList<String> dialogue_line = new ArrayList<>();

    public Panel() {

        final Timer task = new Timer(1000 / 24, e -> repaint());
        task.start();

        final Consumer<Boolean> update_fact = (bool) -> {
            dialogue_line = ContentBuilder.getFact();
        };

        final Timer factUpdate = new Timer(10000, e -> update_fact.accept(true));
        factUpdate.start();

    }

    @Override
    public void paint(Graphics graphics) {
        final Graphics2D g2D = (Graphics2D) graphics;

        g2D.drawImage(background.getImage(), 0, 0, null);

        final Font font = new Font("BOLD", Font.BOLD, 17);

        g2D.setFont(font);
        g2D.setColor(Color.WHITE);

        for (int index = 0; index < dialogue_line.size(); index++) {
            g2D.drawString(dialogue_line.get(index), 20, (200 + (index*20)));
        }
    }
}
