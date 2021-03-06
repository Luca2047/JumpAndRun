package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Bee extends GameObject{

    private boolean attacking;
    private int hoverTimer;
    private final int startY;

    BufferedImage[] idle;
    BufferedImage[] attack;

    AnimationHandler animationHandler;

    public Bee(int x, int y) {
        super(x, y);
        this.id = ObjectID.BEE;
        this.width = 36;
        this.height = 34;
        this.speedY = 1;
        this.alive = true;
        this.showHitbox = false;
        this.attacking = false;
        this.startY = this.y;

        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(idle);
        animationHandler.setDelay(110);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y, width, height, null);

        if (showHitbox) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(getTotalBounds());
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (!alive) {                                                   // wenn tot, dann wird er aus der Liste des Objekt Handlers entfernt
            objects.remove(this);
        } else {
            if (!attacking) {                                           // legt den Zustand der Biene fest, also ob sie angreift oder normal fliegt
                if (hoverTimer >= 30) {
                    speedY = -speedY;
                    hoverTimer = 0;
                }
            } else if (y <= startY) {
                y = startY;
                attacking = false;
            }

            this.y += speedY;
            animationHandler.tick();                                    // updatet die Animation
            hoverTimer++;
            attack(objects);
        }
    }

    public void attack(LinkedList<GameObject> objects) {
        for (GameObject tempObject : objects) {
            if (tempObject.getId() == ObjectID.TILE) {
                if (getTotalBounds().intersects(tempObject.getTotalBounds())) {               // wenn die Hitbox(unten) des Gegners sich mit der dieses Tiles ??berschneidet, fliegt er wieder nach oben
                    animationHandler.setAnimation(idle);
                    y = tempObject.getY() - height;
                    speedY = -2;
                }
            }
            if (tempObject.getId() == ObjectID.PLAYER) {
                if ((Math.abs(getX() - tempObject.getX()) <= 25) && !attacking && (tempObject.getY() > getY())) {           // wird ausgel??st, wenn der Spieler sich der Biene n??hert
                    animationHandler.setAnimation(attack);
                    speedY = 10;
                    attacking = true;
                }
            }
        }
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x+(width/4),y+(height/6),width-(width/2),height-(height/2));
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    public void loadSprites() {                                             // l??dt die Bilder mit allen Teilen der Animation und l??dt die einzelnen Teile als Subimages in ein Array
        try {
            BufferedImage[] fullImage = new BufferedImage[2];
            fullImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/bee/bee_idle.png")));
            fullImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/bee/bee_attack.png")));

            idle = new BufferedImage[6];
            attack = new BufferedImage[8];

            for (int i=0; i<8; i++) {                                                           // hier wird das gesamte Bild durchgegangen und die einzelnen Subimages gemacht
                attack[i] = fullImage[1].getSubimage(i*36, 0, 36, 34);
                if (i<6) {
                    idle[i] = fullImage[0].getSubimage(i*36, 0, 36, 34);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
