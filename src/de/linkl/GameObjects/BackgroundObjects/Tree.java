package de.linkl.GameObjects.BackgroundObjects;

import de.linkl.GameObjects.GameObject;
import de.linkl.State.ObjectID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Tree extends GameObject {

    BufferedImage image1;
    BufferedImage image2;

    public Tree(int x, int y, ObjectID id) {
        super(x, y, id);
        this.x = x+ this.height;
    }


    @Override
    public void render(Graphics g) {

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
