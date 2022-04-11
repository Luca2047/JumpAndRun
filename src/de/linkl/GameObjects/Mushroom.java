package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Mushroom extends GameObject{

    AnimationHandler animationHandler;
    BufferedImage[] idle;

    public Mushroom(int x, int y) {
        super(x, y);
        this.id = ObjectID.MUSHROOM;
        this.width = 32;
        this.height = 32;
        this.alive = true;
        showHitbox = false;
        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(idle);
        animationHandler.setDelay(40);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y, width, height, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        animationHandler.tick();
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x,y,width,height);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle(x + (width / 6), y + (height/4), width - (width / 3), height - (height / 2));
    }



    public void loadSprites() {                                             // lädt die Bilder mit allen Teilen der Animation und lädt die einzelnen Teile als Subimages in ein Array
        try {
            BufferedImage fullImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/mushroom/mushroom_idle.png")));

            idle = new BufferedImage[14];

            for (int i=0; i<14; i++) {                                      // hier wird das gesamte Bild durchgegangen und die einzelnen Subimages gemacht
               idle[i] = fullImage.getSubimage(i*32, 0, 32, 32);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
