package de.linkl.Handler;

import java.awt.image.BufferedImage;

public class AnimationHandler {

    private BufferedImage[] animation;
    private int currentImage;

    private long startTime;
    private long delay;

    public void setAnimation(BufferedImage[] images) {
        this.delay = delay;
        this.animation = images;
        if (currentImage >= animation.length) {
            currentImage = 0;
        }
    }

    public void tick() {
        if (delay == -1) {                                                      // bei Animationen, die nur aus einem Bild bestehen, muss man den Delay auf -1 stellen
            return;
        }

        long pastTime = (System.nanoTime() - startTime)/1000000;
        if (pastTime > delay) {                                                 // immer wenn die vergangene Zeit größer als der Delay ist, wird das nächste Bild ausgewählt
            currentImage++;
            startTime = System.nanoTime();
        }

        if (currentImage == animation.length) {                                 // wenn das letzte Bild in der Animation erreicht wird, wird wieder das erste Bild ausgewählt
            currentImage = 0;
        }
    }

    public BufferedImage getImage() {
        return animation[currentImage];
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

}
