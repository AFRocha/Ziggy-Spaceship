package ziggy.core;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Background image.
 */
public final class Background implements Drawable {

  /**
   * Image handle.
   */
  private final Image img;
   
  /**
   * Constructs a new background loading 
   * the image from the specified path. 
   * @param imagePath Path of the image file.
   */
  public Background(String imagePath) {
    img = new ImageIcon(imagePath).getImage();
  }
  

  /**
   * Draw the background.
   * @param g The graphics object for rendering.
   */
  public void draw(Graphics g) {
    g.drawImage(img, 0, 0, null);    
  }
}
