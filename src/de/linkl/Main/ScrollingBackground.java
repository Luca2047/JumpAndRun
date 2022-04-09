package de.linkl.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ScrollingBackground {

    BufferedImage image;
    private int width;
    private int height;
    private double x;
    private double x2;

    public ScrollingBackground(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            this.width = image.getWidth();
            this.height = image.getHeight();
            x = 0;
            x2 = width;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics g) {
        x -= 1;
        x2 -= 1;
        if (x+image.getWidth() <= 0) {
            x = image.getWidth();
        }
        if (x2+image.getWidth() <= 0) {
            x2 = image.getWidth();
        }
        g.drawImage(image, (int)x, 0, width, height, null);
        g.drawImage(image, (int)x2, 0, width, height, null);
    }


}
