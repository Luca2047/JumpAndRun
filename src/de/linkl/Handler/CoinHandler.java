package de.linkl.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CoinHandler {

    BufferedImage[] firstNum;
    BufferedImage[] secondNum;
    BufferedImage allNumbers;
    BufferedImage[] coinIcon;

    AnimationHandler animationHandler;

    public static int collectedCoins = 0;
    private int firstValue = 0;
    private int secondValue = 0;
    private final int scale = 3;
    private final int width = 32;
    private final int height = 32;


    public CoinHandler() {
        loadSprites();
        animationHandler = new AnimationHandler();
        animationHandler.setAnimation(coinIcon);
        animationHandler.setDelay(60);
    }

    public void tick() {
        if (collectedCoins == 0) {
            secondValue = 0;
        }
        firstValue = collectedCoins - 10*secondValue;
        if (firstValue > 9) {
            secondValue++;
            firstValue = 0;
        }
        animationHandler.tick();
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(firstNum[firstValue], x + 8*scale, y, 8*scale, 10*scale, null);
        g.drawImage(secondNum[secondValue], x, y, 8*scale, 10*scale, null);
        g.drawImage(animationHandler.getImage(), x-35, y, width, height, null);
    }

    public void loadSprites() {
        try {
            allNumbers = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/textgray.png")));

            firstNum = new BufferedImage[11];
            secondNum = new BufferedImage[11];
            for (int i=0; i<10; i++) {
                firstNum[i] = allNumbers.getSubimage(i*8, 30, 8, 10);
                secondNum[i] = firstNum[i];
            }


            BufferedImage fullImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/coin/coinVersion2.png")));
            coinIcon = new BufferedImage[14];
            for (int i=0; i<14; i++) {
                coinIcon[i] = fullImage.getSubimage(i * 32, 0, 32, 32);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
