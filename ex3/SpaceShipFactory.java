/**
 * This class is used by the SpaceWar class to create all the sspaceship
 * objects according to the command line arguments.
 * @author Roi Greenberg
 */
public class SpaceShipFactory {
    
    private static SpaceShip[] spaceShips;
    /**
     * Used by the SpaceWar class to create all the sspaceship
     * objects according to the command line arguments.
     * 
     * @param args array of strings supllied by the user.
     * 
     * @return spaceShips array os SpaceShip according to args.
     * @author רועי
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
    	spaceShips = new SpaceShip[args.length];
    	int index = 0;
    	for (String ship: args) {
            switch (ship) {
                case "r": spaceShips[index] = new Runner();
                    index++;
                    break;
                case "h": spaceShips[index] = new Humen();
                    index++;
                    break;
                case "b": spaceShips[index] = new Basher();
                    index++;
                    break;
                case "a": spaceShips[index] = new Aggressive();
                    index++;
                    break;
                case "d": spaceShips[index] = new Drunkard();
                    index++;
                    break;
                case "s": spaceShips[index] = new Special();
                    index++;
                    break;
                }
    		
    	}
        return spaceShips;
    }
 
}
