package ziggy.core;

import static ziggy.core.GameState.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ziggy.actions.ActionQueue;
import ziggy.elements.GameElement;
import ziggy.elements.Ziggy;
import ziggy.elements.ZiggyObserver;
import ziggy.util.Coord2D;
import ziggy.util.GameLevelReader;

/**
 * Main game class.
 */
public final class Game implements KeyListener, Constants, Drawable  {

  /**
   * Game state.
   */
  private GameState state;
  /**
   * Game data.
   */
  private GameData data;
  /**
   * Background image.
   */
  private final Background bImage;
  /**
   * Background music.
   */
  private final Sound bMusic;
  /**
   * Action queue.
   */
  private final ActionQueue actionQueue;

  /**
   * Game level.
   */
  private int level;
  
  /**
   * Font for game status.
   */
  private static Font ARIAL_20 = new Font("Arial", Font.PLAIN, 20);
 
  /**
   * Fire release.
   */
  private int fireCountdown;
  
  /**
   * Minimum steps between bullet fire.
   */
  private static final int FIRE_INTERVAL = 6;
  /**
   * Constructor.
   */
  public Game() {
    bImage = new Background("images/background.png");
    bMusic = new Sound("sounds/trippygaia1.mid");
    bMusic.loop();
    actionQueue = new ActionQueue();
    load(0);
  }
  /**
   * Load game configuration.
   * @param level Game level.
   */
  private void load(int level) {
    data = GameLevelReader.readGameLevel(level);
    state = ACTIVE;
    this.level = level;
    fireCountdown = FIRE_INTERVAL;
  }

  /**
   * Get game state.
   * @return State of the game.
   */
  public GameState getGameState() {
    return state;
  }

  /**
   * Perform a game step.
   */
  public synchronized void step() {
    if (state != ACTIVE)
      return;
    
    // First update each element's state in isolation
    Ziggy z = data.getZiggy();
    GameElement[] elements = data.elements();
    for (GameElement ge : elements) {
      ge.step(actionQueue);
    }
    if (fireCountdown != 0) {
      fireCountdown --;
    }
    
    actionQueue.flush(data);

    // Handle Ziggy observers
    elements = data.elements();
    for (GameElement ge : elements) {
      if (ge instanceof ZiggyObserver) {
        ZiggyObserver zo = (ZiggyObserver) ge;
        zo.onZiggyUpdate(z.getPosition());
      }
    }

    // Finally handle collisions.
    elements = data.elements();
    for (int i = 0; i < elements.length; i++) {
      GameElement elemA = elements[i];
      Coord2D posA = elemA.getPosition();
      for (int j = i+1; j < elements.length; j++) {
        GameElement elemB = elements[j];
        Coord2D posB = elemB.getPosition();
        if (posA.distanceTo(posB) <= ICON_SIZE) {
          elemA.collideWith(elemB, actionQueue);
          elemB.collideWith(elemA, actionQueue);
        }
      }
    }
    actionQueue.flush(data);

    if (!data.isZiggyAlive()) {
      state = GAME_LOST;
    } else if (data.starCount() == 0) {
      state = GAME_WON;
    }
  }

  /** 
   * Draw the game contents.
   * @param g Graphics object for rendering.
   */
  @Override
  public synchronized void draw(Graphics g) {
    bImage.draw(g);
    GameElement[] elements = data.elements();
    // Elements drawn in reverse order of definition
    // (Ziggy is drawn last)
    for (int i = elements.length-1; i >= 0; i--){
      elements[i].draw(g);
    }
    if (data.isZiggyAlive()) {
      // Draw "direction arrow"
      Ziggy z = data.getZiggy();
      Coord2D p = z.getPosition();
      double d = z.getDirection();
      Coord2D p1 = p.move(ICON_SIZE/2, d-10);
      Coord2D p2 = p.move(ICON_SIZE/2+10, d);
      Coord2D p3 = p.move(ICON_SIZE/2, d+10);
      int x1 = (int) Math.round(p1.getX());
      int y1 = (int) Math.round(p1.getY());
      int x2 = (int) Math.round(p2.getX());
      int y2 = (int) Math.round(p2.getY());
      int x3 = (int) Math.round(p3.getX());
      int y3 = (int) Math.round(p3.getY());
      g.setColor(Color.YELLOW);
      g.fillPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
    }
    // Display game status
    String status = String.format("Level %d | %s | star count: %d | element count: %d", level, state.toString(), data.starCount(), data.elementCount());
    char ch[] = status.toCharArray();
    g.setFont(ARIAL_20);
    g.setColor(Color.BLUE);
    g.drawChars(ch, 0, ch.length, 0, AREA_LENGTH + 20);
  }

  /**
   * Handler for key presses.
   * @param e key event.
   */
  @Override
  public synchronized void keyPressed(KeyEvent e) {

    Command c = null;
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        c = Command.TURN_LEFT;
        break;
      case KeyEvent.VK_RIGHT:
        c = Command.TURN_RIGHT;
        break;
      case KeyEvent.VK_UP:
        c = Command.ACCELARATE;
        break;
      case KeyEvent.VK_DOWN:
        c = Command.BRAKE;
        break; 
      case KeyEvent.VK_SPACE:
        if (fireCountdown == 0) {
          c = Command.FIRE;
          fireCountdown = FIRE_INTERVAL;
        }
        break;
      case 'P':
        if (state == ACTIVE)
          state = PAUSED;
        else if (state == PAUSED)
          state = ACTIVE;
        break;
      case 'R':
        load(level);
        break;
      case 'K':
        Sound.volumeDown();
        break;
      case 'L':
        Sound.volumeUp();
        break;
      case 'M':
        if (bMusic.playing()) {
          bMusic.stop();
        } else {
          bMusic.loop();
        }
        break;
      case '0': case '1': case '2':
      case '3': case '4': case '5':
      case '6': case '7': case '8':
      case '9':
        load(e.getKeyCode() - '0');
        break;
      default:
    }
    if (state == ACTIVE && c != null) {
      data.getZiggy().handleCommand(c, actionQueue);
    }
  }

  /**
   * Handler for key releases. 
   * Nothing is done by this method.
   * @param e key event.
   */
  @Override
  public final void keyReleased(KeyEvent e) {
    // DO NOTHING
  }

  /**
   * Handler for typed keys. 
   * Nothing is done by this method.
   * @param e key event.
   */
  @Override
  public final void keyTyped(KeyEvent e) { 
    // DO NOTHING
  }
}
