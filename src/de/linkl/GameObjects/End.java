package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class End extends GameObject {

    BufferedImage fullImage;
    BufferedImage[] animation;
    AnimationHandler animationHandler;

    public End(int x, int y) {
        super(x, y);
        this.width = 32*2;
        this.height = 64*2;
        this.id = ObjectID.END;
        loadSprites();

        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(animation);
        animationHandler.setDelay(140);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, (int)(y-height/1.5), width, height, null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        animationHandler.tick();
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x+width/4,y-height/2,width-width/2,height);
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    public void loadSprites() {                             // lädt die Bilder mit allen Teilen der Animation und lädt die einzelnen Teile als Subimages in ein Array
        try {
            fullImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/greenPortal.png")));
            animation = new BufferedImage[8];

            for (int i=0;i<8;i++) {                         // hier wird das gesamte Bild durchgegangen und die einzelnen Subimages gemacht
                animation[i] = fullImage.getSubimage(i*32,0,32,64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
