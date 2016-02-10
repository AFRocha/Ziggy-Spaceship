package ziggy.core;

import java.awt.Graphics;

/**
 * Interface for drawable objects.
 */
public interface Drawable {
  /**
   * Draw the object.
   * @param g Graphics object for rendering.
   */
  void draw(Graphics g);
}
