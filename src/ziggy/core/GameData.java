package ziggy.core;

import java.util.LinkedHashSet;

import ziggy.elements.GameElement;
import ziggy.elements.Star;
import ziggy.elements.Ziggy;
import ziggy.util.Coord2D;

/**
 * Game data, representing all game elements.
 */
public final class GameData {
  // @invariant   getZiggy() != null
  //           && starCount >= 0
  //           && elementCount() >= 0;
  /**
   * Ziggy.
   */
  private final Ziggy ziggy;
  /**
   * All game elements.
   */
  private final LinkedHashSet<GameElement> elements;
  /**
   * Star count.
   */
  private int starCount;

  /**
   * Initalize game data.
   * @param ziggyPosition Ziggy's initial position
   */
  // @requires ziggyPosition != null;
  // @ensures getZiggy().getPosition().equals(ziggyPosition);
  // @ensures isElementActive(getZiggy());
  // @ensures elementCount() == 1;
  // @ensures starCount() == 0;
  public GameData(Coord2D ziggyPosition) {
    ziggy = new Ziggy(ziggyPosition);
    elements = new LinkedHashSet<>();
    elements.add(ziggy);

  }

  /**
   * Get Ziggy instance.
   * @return the {@code Ziggy} instance for the game.
   */
  public Ziggy getZiggy() {
    return ziggy;
  }

  /**
   * Get number of stars.
   * @return Number of {@code Star} elements currently defined.
   */
  public int starCount() {
    return starCount;
  }

  /**
   * Get number of elements, including Ziggy if it still alive.
   * @return Number of {@code Star} elements currently defined.
   */
  public int elementCount() {
    return elements.size();
  }

  /**
   * Check if element is defined
   * @param e Game element.
   * @return {@code true} if element is defined.
   * @throws IllegalArgumentException if {@code e == null}
   */
  public boolean isElementDefined(GameElement e) {
    return elements.contains(e);
  }

  /**
   * Check if Ziggy is alive.
   * @return {@code true} if Ziggy is alive.
   */
  // @ensures \result == isElementActive(getZiggy());
  public boolean isZiggyAlive() {
    return isElementDefined(getZiggy());
  }

  /**
   * Add a game element.
   * @param e Game element
   * @throws IllegalArgumentException if {@code e == null}
   * or {@code e} is already defined element.
   */
  // @requires !isElementActive(e);
  // @ensures isElementActive(e);
  // @ensures elementCount() == \old(elementCount()) + 1;
  // @ensures elements instanceof Star 
  //     ==> starCount() == \old(starCount()) + 1;
  public void addGameElement(GameElement e) {
    if (e == null) {
      throw new IllegalArgumentException("Null game element");
    }
    if (elements.add(e)) {
      if (e instanceof Star) {
        starCount++;
      }
    } else {
      throw new IllegalArgumentException("Element already exists.");
    }
  }

  /**
   * Remove a game element.
   * @param e Game element
   * @throws IllegalArgumentException if {@code e == null}
   * or {@code e} is not defined.
   */
  // @requires isElementActive(e);
  // @ensures !isElementActive(e);
  // @ensures elementCount() == \old(elementCount()) - 1;
  // @ensures elements instanceof Star 
  //     ==> starCount() == \old(starCount()) - 1;
  public void removeGameElement(GameElement e) {
    if (e == null) {
      throw new IllegalArgumentException("Null game element");
    }
    if (elements.remove(e)) {
      if (e instanceof Star) {
        starCount--;
      }
    } else {
      throw new IllegalArgumentException("Element does not exist.");
    }
  }

  /**
   * Get all elements in an array.
   * The array elements will be ordered by the order of insertion
   * through {@code addGameElement}.
   * @return An array of {@code GameElement} instances.
   */
  // @ensures \result != null;
  // @ensures \result.length == elementCount();
  // @ensures \forall int i,
  //    i >= 0 && i < \result.length ==>
  //       isElementActive(\result[i]);
  public GameElement[] elements() {
    return elements.toArray(new GameElement[elements.size()]);
  }
}
