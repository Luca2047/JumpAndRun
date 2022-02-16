package de.linkl.GameObjects;

import de.linkl.GameObjects.GameObject;
import de.linkl.Handler.AnimationHandler;
import de.linkl.Main.Game;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Objects;

public class Turtle extends GameObject {

    private boolean attack = false;
    private int attackTimer = 0;

    BufferedImage[] idle1;
    BufferedImage[] idle2;
    BufferedImage[] spikesIn;
    BufferedImage[] spikesOut;

    AnimationHandler animationHandler;

    public Turtle(int x, int y, ObjectID id) {
        super(x, y, id);
        this.id = ObjectID.TURTLE;
        this.width = 44;
        this.height = 26;
        this.alive = true;
        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(idle2);
        animationHandler.setDelay(60);
        showHitbox = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y + 6, width, height, null);

        if (showHitbox) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.draw(getTotalBounds());
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (!alive){
            objects.remove(this);
        } else {

            animationHandler.tick();
            collisions(objects);
        }
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x+(width/8),y+(height/6)+6,width-(width/4), height-(height/4));
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    public void collisions(LinkedList<GameObject> objects) {
        for (GameObject tempObject : objects) {
            if (tempObject.getId() == ObjectID.PLAYER) {
                if (Math.abs(getX() - tempObject.getX()) <= 80 && !attack) {
                    animationHandler.setAnimation(idle1);
                    attack = true;
                }
                else {
                    if (!attack) {
                        animationHandler.setAnimation(idle2);
                    }
                    attack = false;
                }
            }
        }
    }

    public void loadSprites() {
        try {
            BufferedImage[] fullImage = new BufferedImage[4];
            fullImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/turtle/turtle_spikesOut.png")));
            fullImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/turtle/turtle_spikesIn.png")));
            fullImage[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/turtle/turtle_idle2.png")));
            fullImage[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/turtle/turtle_idle1.png")));

            spikesIn = new BufferedImage[8];
            spikesOut = new BufferedImage[8];
            idle2 = new BufferedImage[14];
            idle1 = new BufferedImage[14];

            for (int i = 0; i < 14; i++) {
                if (i < 8) {
                    spikesIn[i] = fullImage[0].getSubimage(i * 44, 0, 44, 26);
                    spikesOut[i] = fullImage[1].getSubimage(i * 44, 0, 44, 26);
                }
                idle2[i] = fullImage[2].getSubimage(i * 44, 0, 44, 26);
                idle1[i] = fullImage[3].getSubimage(i * 44, 0, 44, 26);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}