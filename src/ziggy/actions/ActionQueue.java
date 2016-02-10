package ziggy.actions;

import ziggy.core.GameData;

/**
 * Action queue.
 */
public class ActionQueue {
  
  /**
   * Array of actions.
   */
  private Action[] actions;
  /**
   * Queue size.
   */
  private int size;
   
  /**
   * Constructs a new action queue.
   * The queue's initial capacity is 5.
   */
  public ActionQueue() {
    actions = new Action[5];
    size = 0;
  }
  
  /**
   * Add an action to the queue.
   * @param a Action to add.
   */
  public synchronized void add(Action a) {
    if (size == actions.length) {
      Action[] newData = new Action[actions.length * 2];
      for (int i = 0; i < size; i++) {
        newData[i] = actions[i];
      }
      actions = newData;
    }
    actions[size++] = a;
  }
  
  /**
   * Get size of the queue.
   * @return Number of actions in the queue.
   */
  public synchronized int size() {
    return size;
  }
 
  /**
   * Flush the queue, handling all actions.
   * On exit, the queue will be empty.
   * @param gd Game data.
   */
  public synchronized void flush(GameData gd) {
    for (int i=0; i < size; i++) {
      actions[i].execute(gd);
      actions[i] = null;
    }
    size = 0;
  }
}
