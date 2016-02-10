package ziggy.core;

import java.util.Random;

/**
 * Utility class for pseudo-random number generation.
 */
public class RNG {

  /**
   * The actual generator.
   */
  private static final Random rng = new Random(0);

  /**
   * Get a random number from {@code 0} to a maximum value.
   * @param max Maximum value.
   * @return Random number between {@code 0} and {@code max}.
   * @throws IllegalArgumentException if {@code max <= 0}.
   */
  public static double random(double max) {
    synchronized (rng) {
      if (max < 0) {
        throw new IllegalArgumentException(max + " <= 0 ");
      }
      return rng.nextDouble() * max;
    }
  }

  /**
   * Get a random number in a given range. 
   * @param min Maximum value.
   * @param max Maximum value.
   * @return Random number between {@code min} and {@code max}.
   * @throws IllegalArgumentException if {@code min > max}.
   */
  public static double random(double min, double max) {
    synchronized (rng) {
      if (min >= max) {
        throw new IllegalArgumentException(min + " > " + max);
      }
      return min + random(max-min);
    }
  }
}
