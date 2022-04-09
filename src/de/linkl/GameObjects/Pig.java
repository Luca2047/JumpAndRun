package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.Handler.CoinHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Pig extends GameObject {

    AnimationHandler animationHandler;
    BufferedImage[] idleRight;
    BufferedImage[] idleLeft;
    BufferedImage[] runRight;
    BufferedImage[] runLeft;
    BufferedImage[] walkRight;
    BufferedImage[] walkLeft;

    private boolean raged = false;
    private int timerForRage = 0;

    public Pig(int x, int y, boolean facingRight) {
        super(x, y);
        this.facingRight = facingRight;
        this.id = ObjectID.PIG;
        this.width = 36;
        this.height = 30;
        if (facingRight) {
            this.speedX = 2;
        } else {
            this.speedX = -2;
        }
        this.alive = true;
        showHitbox = true;
        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(idleLeft);
        animationHandler.setDelay(40);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y+2, width, height, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (!alive){
            objects.remove(this);
        } else {
            collisions(objects);

            this.x += this.speedX;                                              // ändert die x und y Position um die jeweilige Geschwindigkeit

            if (speedX > 0 && !raged) {
                animationHandler.setAnimation(walkRight);
                facingRight = true;
            } else if (!raged){
                animationHandler.setAnimation(walkLeft);
                facingRight = false;
            } else if (facingRight){
                animationHandler.setAnimation(runRight);
            } else {
                animationHandler.setAnimation(runLeft);
            }
            if (raged) {
                timerForRage++;
            }

            animationHandler.tick();
        }
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle(x + (width / 6), y, width - (width / 3), height - (height / 2));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(x + (width - (width / 10)), y + (height / 4), width / 10, height - (height / 2));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(x, y + (height / 4), width / 10, height - (height / 2));
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(x + (width / 6), y + (height / 2), width - (width / 3), height - (height / 2));
    }

    public void loadSprites() {
        try {
            BufferedImage[] fullImage = new BufferedImage[6];
            fullImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_idleRight.png")));
            fullImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_idleLeft.png")));
            fullImage[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_runRight.png")));
            fullImage[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_runLeft.png")));
            fullImage[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_walkRight.png")));
            fullImage[5] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/pig/pig_walkLeft.png")));

            idleRight = new BufferedImage[9];
            idleLeft = new BufferedImage[9];
            runRight = new BufferedImage[12];
            runLeft = new BufferedImage[12];
            walkRight = new BufferedImage[16];
            walkLeft = new BufferedImage[16];

            for (int i=0; i<16; i++) {
                if (i<12) {
                    runRight[i] = fullImage[2].getSubimage(i*36, 0, 36, 30);
                    runLeft[i] = fullImage[3].getSubimage(396-i*36, 0, 36, 30);
                }
                if (i<9) {
                    idleRight[i] = fullImage[0].getSubimage(i*36, 0, 36, 30);
                    idleLeft[i] = fullImage[1].getSubimage(i*36, 0, 36, 30);
                }
                walkRight[i] = fullImage[4].getSubimage(i*36, 0, 36, 30);
                walkLeft[i] = fullImage[5].getSubimage(540-i*36, 0, 36, 30);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collisions(LinkedList<GameObject> objects) {
        for (GameObject tempObject : objects) {
            if (tempObject.getId() == ObjectID.TILE) {

                if (getBoundsRight().intersects(tempObject.getTotalBounds())) {          // wenn die Hitbox(rechts) des Gegners sich mit der dieses Tiles überschneidet
                    x = tempObject.getX() - width;
                    speedX = -speedX;
                    facingRight = !facingRight;
                }
                if (getBoundsLeft().intersects(tempObject.getTotalBounds())) {           // wenn die Hitbox(links) des Gegners sich mit der dieses Tiles überschneidet
                    x = tempObject.getX() + tempObject.getWidth();
                    speedX = -speedX;
                    facingRight = !facingRight;
                }
            }

            if (tempObject.getId() == ObjectID.PLAYER) {
                if (getBoundsTop().intersects(tempObject.getTotalBounds())) {
                    if (!raged) {
                        raged = true;
                        speedX *= 2;
                    } else if (timerForRage >= 20){
                        setAlive(false);
                        CoinHandler.collectedCoins += 2;
                    }
                }
            }
        }
    }
}