package de.linkl.GameObjects;

import de.linkl.State.ObjectID;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected int x, y;
    protected int width, height;
    protected double scale = 1;
    protected float speedX, speedY;
    protected boolean alive;
    protected boolean facingRight;

    protected final float g = 0.6f;                                          // Gravitationskonstante
    protected final int maximumFallSpeed = 20;
    protected boolean showHitbox = false;

    protected ObjectID id;
    protected boolean isUpdating = true;

    protected boolean falling = true;
    protected boolean jumping = false;

    public GameObject(int x, int y, int width, int height, ObjectID id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public GameObject(int x, int y, ObjectID id) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);                                                // Definiert wie das Object dargestellt werden soll
    public abstract void tick(LinkedList<GameObject>objects);                               // Definiert, was das Object bei jedem Tick macht
    public abstract Rectangle getTotalBounds();                                                  // Definiert die gesamte Hitbox des Objects
    public abstract Rectangle getBoundsTop();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {                                                                 // Bei der Abfrage von width und height sollten die get-Methoden verwendet
        return width;                                                                       // werden, da die Bilder erst
    }

    public int getHeight() {
        return height;
    }

    public ObjectID getId() {
        return id;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(ObjectID id) {
        this.id = id;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public double getScale() {
        return scale;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
