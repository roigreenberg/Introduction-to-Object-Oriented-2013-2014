/**
 * This class implement the Runner spaceship.
 * This ship available to execute 2 actions 
 * The actions are:
 * 1. Teleport 2. Move(run away from closest ship) 
 
 * @author Roi Greenberg
 */
public class Runner extends SpaceShip {
    

    /**
     * Construct a Runner space ship.
     * inhirate from SpaceShip class.
     * also turn the left and right so the ship will run away from closest ship
     */
    public Runner() {
            this.left = -1;
            this.right = 1;
    }

    private static final boolean ACCELARATE = true;

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * THe actions that tried to execute for this ship are 
     * 1. Teleport 2. Move
     * Also it regenerate unit of energy at the end of the turn  
     * @param game the game object to which this ship belongs.
     * 
     * override doAction in class SpaceShip
     */
    @Override
    public void doAction(SpaceWars game) {
        
        if (this.getPhysics().distanceFrom(this.closestShip(game))<0.2 && 
                this.getPhysics().angleTo(this.closestShip(game))<0.2){
            this.teleport();
        }
        
        getMove(game, ACCELARATE);
        
        regeneration();
        
        
    }
    
    /**
     * this method is used only testing and for the special ship
     * @return name the type of this ship
     */
    @Override public String toString(){
        String name = "Runner";
        return name;
    }
}
