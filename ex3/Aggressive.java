/**
 * This class implement the Aggresive spaceship.
 * This ship available to execute 2 actions 
 * The actions are:
 * 1. Move(run to closest ship) 2. fire 
 
 * @author Roi Greenberg
 */
public class Aggressive extends SpaceShip {
        

    private static final boolean ACCELARATE = true;

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * Thde actions that tried to execute for this ship are 
     * 1. Move 2. fire
     * Also it regenerate unit of energy at the end of the turn  
     * 
     * @param game the game object to which this ship belongs.
     * 
     * override doAction in class SpaceShip
     */
    @Override
    public void doAction(SpaceWars game) {

        getMove(game, ACCELARATE);

        if (this.getPhysics().distanceFrom(this.closestShip(game))<0.2){
                this.fire(game);
        }
        this.fireCounter++;

        regeneration();
    }
    
    /**
     * this method is used only testing and for the special ship
     * @return name the type of this ship
     */
    @Override public String toString(){
        String name = "Aggressive";
        return name;
    }
}
