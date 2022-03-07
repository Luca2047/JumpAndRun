package de.linkl.Tools;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundPlayer {

    public static File lastFile;

    public static File theme;
    public static File menuTheme;
    public static File playerJump;
    public static File coin;
    public static File bump;
    public static File playerDown;
    public static File spring;

    public float volume;

    Clip clip;

    public void load() {                                                                                            // lädt alle Sounds die benötigt werden
        theme = new File("src/de/linkl/Sounds/theme.wav");
        menuTheme = new File("src/de/linkl/Sounds/menuTheme.wav");
        playerJump = new File("src/de/linkl/Sounds/playerJump.wav");
        coin = new File("src/de/linkl/Sounds/coin.wav");
        bump = new File("src/de/linkl/Sounds/bump.wav");
        playerDown = new File("src/de/linkl/Sounds/playerDown.wav");
        spring = new File("src/de/linkl/Sounds/spring.wav");

        this.volume = -25f;
    }

    public void play(File file) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));

            FloatControl controller = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            controller.setValue(volume);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loop(File file) {                                                                                   // wie play bloß, dass der Sound geloopt wird
        try {

            if (file == lastFile) {                                                                                 // falls die Methode in einer Schleife aufgerufen wird (oft der Fall),
                                                                                                                    // wird der Sound nicht immer wieder gestartet
            } else {

                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));

                FloatControl controller = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                controller.setValue(volume);

                lastFile = file;

                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        clip.stop();
    }
}
