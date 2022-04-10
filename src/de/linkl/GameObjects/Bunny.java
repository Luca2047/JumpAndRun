package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Bunny extends GameObject{

    BufferedImage[] runRight;
    BufferedImage[] runLeft;

    AnimationHandler animationHandler;

    public Bunny(int x, int y) {
        super(x, y);
        this.id = ObjectID.ENEMY;
        this.width = 34;
        this.height = 44;
        this.speedX = 4;
        this.facingRight = false;
        this.alive = true;
        showHitbox = false;
        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(runLeft);
        animationHandler.setDelay(60);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y, width, height, null);

        if (showHitbox) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.draw(getBoundsBottom());
            g2d.draw(getBoundsTop());
            g2d.draw(getBoundsRight());
            g2d.draw(getBoundsLeft());
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (!alive){
            objects.remove(this);
        } else {
            if (falling) {                                           // lässt den Gegner fallen, bzw. ändert die y-Geschwindigkeit um g (Gravitationskonstante)
                this.speedY += g;
                if (this.speedY > maximumFallSpeed) {
                    speedY = maximumFallSpeed;
                }
            }
            this.x += this.speedX;                                              // ändert die x und y Position um die jeweilige Geschwindigkeit
            this.y += this.speedY;

            if (speedX > 0) {
                animationHandler.setAnimation(runRight);
                facingRight = true;
            } else {
                animationHandler.setAnimation(runLeft);
                facingRight = false;
            }

            animationHandler.tick();
            collisions(objects);
        }
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(x + (width / 6), y + (height / 2), width - (width / 3), height - (height / 2));
    }
    public Rectangle getBoundsTop() {
        return new Rectangle(x + (width / 6), y + (height/4), width - (width / 3), height - (height / 2));
    }
    public Rectangle getBoundsRight() {
        return new Rectangle(x + (width - (width / 10)), y + (height / 4), width / 10, height - (height / 2));
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle(x, y + (height / 4), width / 10, height - (height / 2));
    }
    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x,y,width,height);
    }

    public void collisions(LinkedList<GameObject> objects) {
        for (GameObject tempObject : objects) {
            if (tempObject.getId() == ObjectID.TILE) {

                if (getBoundsBottom().intersects(tempObject.getTotalBounds())) {               // wenn die Hitbox(unten) des Gegners sich mit der dieses Tiles überschneidet
                    y = tempObject.getY() - height;
                    speedY = 0;
                    falling = false;
                } else {
                    falling = true;
                }

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
        }
    }

    public void loadSprites() {
        try {
            BufferedImage[] fullImage = new BufferedImage[2];
            fullImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/bunny/bunny_runRight.png")));
            fullImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/bunny/bunny_runLeft.png")));

            runRight = new BufferedImage[12];
            runLeft = new BufferedImage[12];

            for (int i=0; i<12; i++) {
                runRight[i] = fullImage[0].getSubimage(i*34, 0, 34, 44);
                runLeft[i] = fullImage[1].getSubimage(374-i*34, 0, 34, 44);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
