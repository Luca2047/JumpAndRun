package de.linkl.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DeathHandler {

    BufferedImage[] firstNum;
    BufferedImage[] secondNum;
    BufferedImage allNumbers;
    BufferedImage skullSymbol;
    
    public static int deathcount = 0;
    private int firstValue = 0;
    private int secondValue = 0;
    private final int width = 32;
    private final int height = 32;
    private int scale = 3;
    
    public DeathHandler() {
        loadSprites();
    }

    public void tick() {
        if (deathcount == 0) {                      // kontrolliert die Werte der zwei Ziffern der Anzeige
            secondValue = 0;
        }
        firstValue = deathcount - 10*secondValue;
        if (firstValue > 9) {
            secondValue++;
            firstValue = 0;
        }
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(firstNum[firstValue], x + 8*scale, y, 8*scale, 10*scale, null);
        g.drawImage(secondNum[secondValue], x, y, 8*scale, 10*scale, null);
        g.drawImage(skullSymbol, x-35, y, width, height, null);
    }
    
    public void loadSprites() {                                 // lädt die Bilder mit allen Zahlen und lädt die einzelnen Ziffern als Subimages in ein Array
        try {
            allNumbers = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/textgray.png")));

            firstNum = new BufferedImage[11];
            secondNum = new BufferedImage[11];
            for (int i=0; i<10; i++) {
                firstNum[i] = allNumbers.getSubimage(i*8, 30, 8, 10);
                secondNum[i] = firstNum[i];
            }
            
            skullSymbol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/skull.png")));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScale(int x) {
        scale = x;
    }
    
}
