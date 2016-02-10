package ziggy.core;

/**
 * Commands sent to Ziggy.
 */
public enum Command {
  /**
   * Turn left (10 degrees at a time).
   */
  TURN_LEFT,
  /**
   * Turn left (10 degrees at a time).
   */
  TURN_RIGHT,
  /**
   * Accelerate (1 speed unit at a time).
   */
  ACCELARATE,
  /**
   * Brake (1 speed unit at a time).
   */
  BRAKE,
  /**
   * Fire bullet.
   */
  FIRE;
}
