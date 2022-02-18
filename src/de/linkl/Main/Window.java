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

public class Window extends JFrame implements ActionListener{

    Dimension dimension;
    BufferedImage frameIcon;

    protected JButton start;
    protected JButton exit;
    protected JLabel title;

    public Window(int width, int height, String title) {


        dimension = new Dimension(width, height);
        try {
            frameIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/frameIcon.png")));
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

        this.setIconImage(frameIcon);

        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setLocationRelativeTo(null);

        start = new JButton("Start");
        start.setBounds(590, 200, 100, 50);
        start.addActionListener(this);
        this.add(start);

        exit = new JButton("Exit");
        exit.setBounds(590, 300, 100, 50);
        exit.addActionListener(this);
        this.add(exit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            start.setVisible(false);
            exit.setVisible(false);
            this.remove(start);
            this.remove(exit);
            Game.inMenu = false;
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
