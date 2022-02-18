package de.linkl.Tools;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;

public class SoundPlayer {

    public static File music;
    public static File playerJump;

    private float volume;

    Clip clip;

    public void load() {
        music = new File("/de/linkl/Sounds/beginning_of_time.wav");
        this.volume = -50f;
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
            clip.open(AudioSystem.getAudioInputStream(file));

            FloatControl controller = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            controller.setValue(volume);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
