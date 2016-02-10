package ziggy.actions;

import ziggy.core.GameData;

/**
 * Interface for game actions.
 */
public interface Action {
  /**
   * Execute action, possibly modifying game data.
   * @param gd Game data
   */
  void execute(GameData gd);
}
