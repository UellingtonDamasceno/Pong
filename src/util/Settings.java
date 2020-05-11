package util;

/**
 *
 * @author Uellington Conceição
 */
public class Settings {
    public enum Direction{
        NORTH("SOUTH"), 
        SOUTH("NORTH"),
        EAST("WEAST"),
        WEAST("EAST");
        
        private String opposite;
        
        private Direction(String opposite){
            this.opposite = opposite;
        }
        
        public Direction getOpposite(){
            return Direction.valueOf(opposite);
        }
    }
}
