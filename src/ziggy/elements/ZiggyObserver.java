package ziggy.elements;

import ziggy.util.Coord2D;

/**
 * Interface for Ziggy observers.
 */
public interface ZiggyObserver {
  /**
   * Method invoked after each Ziggy step.
   * @param pos Ziggy's position.
   */
  void onZiggyUpdate(Coord2D pos);
}
