package ziggy.actions;

import ziggy.core.GameData;
import ziggy.core.Sound;

/**
 * Action to plays a sound file.
 */
public class PlaySound implements Action {

  /**
   * Sound object.
   */
  private final Sound sound;
  
  /**
   * Constructor.
   * @param filename Sound filename.
   */
  public PlaySound(String filename) {
    sound = new Sound("sounds/"+filename); 
  }
  
  /**
   * Plays the sound. It has no effect on the game data.
   * @param gd Game data.
   */
  @Override
  public void execute(GameData gd) {
    sound.play();
  }
}
