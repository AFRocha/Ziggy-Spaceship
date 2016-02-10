package ziggy.core;

/**
 * Game state.
 */
public enum GameState {
   /**
    * Game is active.
    */
   ACTIVE("Game is active"),
   /**
    * Game is paused.
    */
   PAUSED("Game is paused"),
   /**
    * Game was lost.
    */
   GAME_LOST("Game lost"),
   /**
    * Game was won.
    */
   GAME_WON("Game won");
   
   /**
    * Description.
    */
   private String desc;
   
   /**
    * Constructor.
    * @param desc State description
    */
   private GameState(String desc) {
     this.desc = desc;
   }
   
   /**
    * Get textual representation.
    * @return A {@code String} object.
    */
   @Override
   public String toString() {
     return desc;
   }
}
