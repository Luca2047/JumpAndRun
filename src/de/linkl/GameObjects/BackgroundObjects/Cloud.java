package de.linkl.GameObjects.BackgroundObjects;

import de.linkl.GameObjects.GameObject;
import de.linkl.Main.Game;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Cloud extends GameObject {

    private int x, y;
    private int width, height;
    BufferedImage image;

    public Cloud(int x, int y, ObjectID id) {
        super(x, y, id);
        this.x = x;
        this.y = y;
        this.scale = 0.8;
        this.speedX = -2;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/map/cloud.png")));
            width = (int)(image.getWidth()*scale);
            height = (int)(image.getHeight()*scale);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        this.x += speedX;

        if (this.x <= -Game.totalWidth - this.width) {
            this.x = Game.totalWidth;
        }
    }

    @Override
    public Rectangle getTotalBounds() {
        return null;
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

}
