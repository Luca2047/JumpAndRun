package de.linkl.GameObjects.BackgroundObjects;

import de.linkl.GameObjects.GameObject;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class FloatingIsland extends GameObject {

    BufferedImage image;

    public FloatingIsland(int x, int y, ObjectID id) {
        super(x, y, id);
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/map/floatingIsland.png")));
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {

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
