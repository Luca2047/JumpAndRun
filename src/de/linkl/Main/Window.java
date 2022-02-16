package de.linkl.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame implements ActionListener {

    private final Dimension dimension;
    BufferedImage icon;

    private JButton start;
    private JButton settings;
    private JButton exit;

    public Window(int width, int height, String title, Game game) {

        dimension = new Dimension(width, height);
        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/frameIcon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setFocusable(true);
        this.requestFocus();
        this.setIconImage(icon);

        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setLocationRelativeTo(null);

        this.add(game);
        this.setVisible(true);

        game.start();
    }

    public Window(int width, int height, String title) {

        dimension = new Dimension(width, height);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocus();
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setLocationRelativeTo(null);

        start = new JButton("Start");
        start.setBounds(width/2 - 100,40,200,60);
        start.addActionListener(this);
        this.add(start);

        exit = new JButton("Exit");
        exit.setBounds(width/2 - 100,260,200,60);
        exit.addActionListener(this);
        this.add(exit);

        settings= new JButton("Settings");
        settings.setBounds(width/2 - 100,150,200,60);
        settings.addActionListener(this);
        this.add(settings);

        /*Title = new JLabel("Java Game");
        Title.setBounds(width/2, 40, 300, 300);
        this.add(Title);*/

        MenuBackground background = new MenuBackground(1280, 710, "/de/linkl/Graphics/menuBackground.jpg");
        this.add(background);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            dispose();
            Window gameWindow = new Window(1280, 710, "Java Game", new Game());
        }
        if(e.getSource() == settings) {

        }
        if(e.getSource()== exit) {
            System.exit(0);
        }
    }
}
