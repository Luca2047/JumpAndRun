package de.linkl.GameObjects;
import de.linkl.Handler.AnimationHandler;
import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Coin extends GameObject {

    AnimationHandler animationHandler;
    BufferedImage[] spinCoin;

    public Coin(int x, int y) {
        super(x, y);
        this.x += 5;
        this.id = ObjectID.COIN;
        this.width = 16;
        this.height = 16;
        this.alive = true;

        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(spinCoin);
        animationHandler.setDelay(60);
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y + (int)(width*scale/2), (int)(width*scale), (int)(width*scale), null);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (!alive) {
            objects.remove(this);
        }
        animationHandler.tick();
    }
    public void loadSprites () {
        try {
            BufferedImage fullImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/coin/coinVersion2.png")));
            spinCoin = new BufferedImage[14];

            for (int i=0; i<14; i++) {
                spinCoin[i] = fullImage.getSubimage(i * 32, 0, 32, 32);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

