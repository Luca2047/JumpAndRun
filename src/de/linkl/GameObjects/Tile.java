package de.linkl.GameObjects;

import de.linkl.State.ObjectID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Tile extends GameObject {

    static BufferedImage fullimage;
    BufferedImage image;
    int type;

    public Tile(int x, int y, int type) {
        super(x, y);
        this.id = ObjectID.TILE;
        this.type = type;
        width = 32;
        height = 32;

        if (fullimage == null) {        // für bessere Performance soll das gesamte Bild nur einmal geladen werden
            try {
                fullimage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/map/ground_tileset.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (type) {
            case 2:                                                                                                 // siehe gimp tileset Bild, legt fest welche Textur für den Block verwendet wird
                image = fullimage.getSubimage(64, 256, 32, 32);
                break;
            case 3:
                image = fullimage.getSubimage(0, 0, 32, 32);
                break;
            case 4:
                image = fullimage.getSubimage(32, 0, 32, 32);
                break;
            case 5:
                image = fullimage.getSubimage(64, 0, 32, 32);
                break;
            case 6:
                image = fullimage.getSubimage(0, 32, 32, 32);
                break;
            case 7:
                image = fullimage.getSubimage(32, 32, 32, 32);
                break;
            case 8:
                image = fullimage.getSubimage(64, 32, 32, 32);
                break;
            case 9:
                image = fullimage.getSubimage(0, 64, 32, 32);
                break;
            case 10:
                image = fullimage.getSubimage(32, 64, 32, 32);
                break;
            case 11:
                image = fullimage.getSubimage(64, 64, 32, 32);
                break;
            case 12:
                image = fullimage.getSubimage(128, 0, 32, 32);
                break;
            case 13:
                image = fullimage.getSubimage(160, 0, 32, 32);
                break;
            case 14:
                image = fullimage.getSubimage(128, 32, 32, 32);
                break;
            case 15:
                image = fullimage.getSubimage(160, 32, 32, 32);
                break;
            case 16:
                image = fullimage.getSubimage(224, 0, 32, 32);
                break;
            case 17:
                image = fullimage.getSubimage(224, 32, 32, 32);
                break;
            case 18:
                image = fullimage.getSubimage(288, 0, 32, 32);
                break;
            case 19:
                image = fullimage.getSubimage(320, 0, 32, 32);
                break;
            case 20:
                image = fullimage.getSubimage(192, 96, 32, 32);
                break;
            case 21:
                image = fullimage.getSubimage(224, 96, 32, 32);
                break;
            case 22:
                image = fullimage.getSubimage(256, 96, 32, 32);
                break;
            case 23:
                image = fullimage.getSubimage(288, 96, 32, 32);
                break;
            case 24:
                image = fullimage.getSubimage(0, 256, 32, 32);
                break;
            case 25:
                image = fullimage.getSubimage(0, 288, 32, 32);
                break;
            case 26:
                image = fullimage.getSubimage(0, 320, 32, 32);
                break;
            case 27:
                image = fullimage.getSubimage(0, 384, 32, 32);
                break;
            case 28:
                image = fullimage.getSubimage(32, 384, 32, 32);
                break;
            case 29:
                image = fullimage.getSubimage(64, 384, 32, 32);
                break;
            case 30:
                image = fullimage.getSubimage(128, 224, 32, 32);
                break;
            case 31:
                image = fullimage.getSubimage(160, 224, 32, 32);
                break;
            case 32:
                image = fullimage.getSubimage(352, 288, 32, 32);
                break;
            case 33:
                image = fullimage.getSubimage(288, 160, 32, 32);
                break;
            case 34:
                image = fullimage.getSubimage(320, 160, 32, 32);
                break;
            case 35:
                image = fullimage.getSubimage(320, 128, 32, 32);
                break;
            case 36:
                image = fullimage.getSubimage(416, 128, 32, 32);
                break;
            case 37:
                image = fullimage.getSubimage(416, 160, 32, 32);
                break;
            case 38:
                image = fullimage.getSubimage(448, 160, 32, 32);
                break;
            case 39:
                image = fullimage.getSubimage(480, 160, 32, 32);
                break;
            case 40:
                image = fullimage.getSubimage(480, 128, 32, 32);
                break;
            case 41:
                image = fullimage.getSubimage(480, 96, 32, 32);
                break;
            case 42:
                image = fullimage.getSubimage(416, 256, 32, 32);
                break;
            case 43:
                image = fullimage.getSubimage(416, 288, 32, 32);
                break;
            case 44:
                image = fullimage.getSubimage(448, 288, 32, 32);
                break;
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
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }
}
