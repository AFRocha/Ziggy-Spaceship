package ziggy.core;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

/**
 * Utility class to handle sound samples.
 */
public final class Sound {

  /**
   * Sound clip object.
   */
  private final Clip clip;

  /**
   * Construct sound object.
   * @param path Path for sound file.
   */
  public Sound(String path) {
    try {
      clip = AudioSystem.getClip();
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
      clip.open(inputStream);
    } catch (Throwable e) {
      throw new RuntimeException("Error initialising sound", e);
    }
  }

  /**
   * Play sound.
   */
  public void play() {
    stop();
    clip.setFramePosition(0);
    clip.start();
  }
  /**
   * Play sound in loop.
   */
  public void loop() {
    stop();
    clip.setFramePosition(0);
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /**
   * Stop sound.
   */
  public void stop() {
    clip.stop();
  }

  /**
   * Check if sound sample is playing.
   * @return {@code true} is sound is being played.
   */
  public boolean playing() {
    return clip.isRunning();
  }

  /**
   * Object finalizer to help garbage collector.
   */
  protected void finalize() {
    clip.close();
  }

  /**
   * Object for volume control.
   */
  private static FloatControl volumeControl = null;

  /**
   * Set sound volume.
   * @param level Volume level (0-100).
   */
  private static void setSoundVolume(int level) {
    if (volumeControl == null) 
      return;
    if (level > 100)
      level = 100;
    if (level < 0)
      level = 0;
    float min = volumeControl.getMinimum();
    float max = volumeControl.getMaximum();
    float v = min + level * (max-min) / 100;
    volumeControl.setValue(v);
  }

  /**
   * Get sound volume.
   * @return Volume level (0-100).
   */
  static int getSoundVolume() {
    if (volumeControl == null) 
      return -1;
    float min = volumeControl.getMinimum();
    float max = volumeControl.getMaximum();
    float v = volumeControl.getValue();
    return Math.round(100 * (v-min) / (max-min));
  }

  static {
    try {
      Mixer mixer = AudioSystem.getMixer(null);
      mixer.open();
      Port.Info[] types = { Port.Info.SPEAKER, Port.Info.HEADPHONE, Port.Info.LINE_OUT };
      for (Port.Info type : types) {
        if (AudioSystem.isLineSupported(type)) { 
          System.err.println(type + " sound device is active!");
          Line l = (Port) AudioSystem.getLine(type);
          l.open();
          volumeControl = (FloatControl) l.getControl(FloatControl.Type.VOLUME);
          break;
        } 
      }
    } 
    catch (Throwable ex) {
      ex.printStackTrace(System.err);
      System.err.println("Could not initialize sound properly!");
    }
  }


  /**
   * Increase sound volume.
   */
  public static void volumeUp() {
    setSoundVolume(Math.min(getSoundVolume() + 10, 100));
    System.err.println("Sound volume:" + getSoundVolume());
  }

  /**
   * Decrease sound volume.
   */
  public static void volumeDown() {
    setSoundVolume(Math.max(getSoundVolume() - 10, 0));
    System.err.println("Sound volume:" + getSoundVolume());
  }
}
