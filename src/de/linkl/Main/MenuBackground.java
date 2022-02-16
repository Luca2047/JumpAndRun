package de.linkl.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuBackground extends JLabel {

    BufferedImage image;
    private String path;
    private int width;
    private int height;

    public MenuBackground(int width, int height, String path) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.setSize(width, height);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, width, height, null);
    }


}
