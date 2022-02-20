package de.linkl.Tools;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundPlayer {

    public static File lastFile;

    public static File theme;
    public static File playerJump;

    private float volume;

    Clip clip;

    public void load() {
        theme = new File("src/de/linkl/Sounds/gameTheme.wav");
        this.volume = -30f;
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

    public void loop(File file) {
        try {
            clip = AudioSystem.getClip();
            if (file == lastFile) {

            } else {

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
