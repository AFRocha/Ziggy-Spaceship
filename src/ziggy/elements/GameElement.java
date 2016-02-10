package ziggy.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import ziggy.actions.ActionQueue;
import ziggy.core.Constants;
import ziggy.core.Drawable;
import ziggy.util.Coord2D;

/**
 * Base class for game elements.
 */
public abstract class GameElement implements Constants, Drawable {

  /**
   * Icon.
   */
  private Image icon;
  

  /**
   * Position.
   */
  private Coord2D position;
  
  /**
   * Constructor.
   * @param position Initial position.
   */
  protected GameElement(Coord2D position) {  
    this.position = position;
    changeIcon(getClass().getSimpleName() + ".png");
  }

  /**
   * Get element position.
   * @return The current position.
   */
  public final Coord2D getPosition() {
    return position;
  }
    

  /**
   * Set position
   * @param p New position.
   */
  public final void setPosition(Coord2D p) {
    position = p;
  }

  /**
   * Change icon.
   * @param iconPath Path for the icon.
   */
  protected final void changeIcon(String iconPath) {
    icon = new ImageIcon("images/"+iconPath).getImage();
  }



  /**
   * Element step.
   * This base implementation does nothing.
   * @param aq Action queue.
   */
  public void step(ActionQueue aq) {
   
  }

  /**
   * Handle collision.
   * This base implementation does nothing.
   * @param other Other game element.
   * @param aq Action queue.
   */
  public void collideWith(GameElement other, ActionQueue aq) {
	
	  
  }
  
  /**
   * Draw the element.
   * @param g Graphics object for rendering.
   */
  @Override
  public final void draw(Graphics g) {
    Coord2D p = getPosition();
    int x = (int) Math.round(p.getX()) - ICON_SIZE / 2;
    int y = (int) Math.round(p.getY()) - ICON_SIZE / 2;
    g.drawImage(icon, x, y, null );
  }


  /**
   * Get textual representation.
   * @return A {@code String object}.
   */
  @Override
  public  String toString() {
    return String.format("%s pos: %d,%d\n",
        getClass().getSimpleName(),
        Math.round(getPosition().getX()),
        Math.round(getPosition().getY()));
  }

}
