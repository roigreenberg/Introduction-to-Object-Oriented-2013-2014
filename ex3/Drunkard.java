import java.util.Random;
//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * This class implement a drunkard spaceship.
 * This ship tring to execute all 4 actions available in a random way 
 * The actions are:
 * 1. Teleport 2. Move 3. activate shield 4. fire 
 
 * @author Roi Greenberg
 */
public class Drunkard extends SpaceShip {
    
    //determine the range of the frequency of the changing
    private static final int CHANGE_RANGE = 200;
    private static final int CHANGE_START = 150;
    //determine the range of the options to turn (-1,0,1)
    private static final int TURN_RANGE = 3;
    private static final int TURN_FIX = 1;
    private final Random randomTurn = new Random();
    private int changeMoveCounter;
    private int change;
    private int turn;
    private boolean acceleration;
    private boolean teleport;
    private boolean shield;
    private boolean fire;
    
    /**
     * Construct a new drunkard ship.
     * in addition to what it inhirate from the SpaceShip class the ship
     * determine for the first time the action that will happend randomaly 
     * by that algorthm:
     *  it draw a random number (named 'change') in the range determind above
     *  also it will reset a counter, and choose where to turn, and if to
     *  accelerate, try to teleport, fire or rise a shield.
     */
    public Drunkard() {
        Random randomTurn = new Random();
        this.changeMoveCounter = 0;
        this.change = this.randomTurn.nextInt(CHANGE_RANGE)+CHANGE_START;
        this.turn = this.randomTurn.nextInt(TURN_RANGE) - TURN_FIX;
        this.acceleration = this.randomTurn.nextBoolean();
        this.teleport = this.randomTurn.nextBoolean();
        this.shield = this.randomTurn.nextBoolean();
        this.fire = this.randomTurn.nextBoolean();

    }

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * THe actions that tried to execute for this ship are 
     * 1. Teleport 2. Move 3. activate shield 4. fire 
     * Every 'change' number of rounds all the parameters are 
     *  chanching randomaly.
     * Teleporting, firing and rising a shield are happening every squere
     *  'change' number of rounds iff their state was dtaw 'true'.
     * Also it regenerate unit of energy at the end of the turn  .
     * 
     * @param game the game object to which this ship belongs.
     * 
     * Override doAction in class SpaceShip
     *
     */
    @Override
    public void doAction(SpaceWars game) {
        
        this.shieldState=false;
        if (this.teleport && this.changeMoveCounter%
                ((int)Math.floor(Math.sqrt(this.change)))==0) {
            
            this.teleport();
        }
        getMove(game);

        if (this.shield && this.changeMoveCounter%
                ((int)Math.floor(Math.sqrt(this.change)))==0){
            this.shieldOn();
                
        }

        if (this.fire && this.changeMoveCounter%
                ((int)Math.floor(Math.sqrt(this.change)))==0){
            this.fire(game);
                
        }
        this.fireCounter++;
        
        if (this.changeMoveCounter%this.change==0){
            this.change = this.randomTurn.nextInt(CHANGE_RANGE)+CHANGE_START;
            this.turn = this.randomTurn.nextInt(TURN_RANGE) - TURN_FIX;
            this.acceleration = this.randomTurn.nextBoolean();
            this.teleport = this.randomTurn.nextBoolean();
            this.shield = this.randomTurn.nextBoolean();
            this.fire = this.randomTurn.nextBoolean();            

        }
        
        this.changeMoveCounter++;
        regeneration();
        

    }

    /**
     * moving the ship in this round. 
     * This is called once per round by doAction.
     * the ship will move according to what draw randomaly this time.
     * 
     * @param game the game object to which this ship belongs.
     * 
     * override getMove in class SpaceShip
     */
    private void getMove(SpaceWars game){
        this.getPhysics().move(this.acceleration, this.turn);
    }
    
    
//     all the code below is uses for testing and/or for the special ship 
    
    
    /**
     * determine if the ship is trying to fire
     * uses for the special ships only
     * @return if the ship trying to fire
     */
    public boolean toTeleport(){
        if (this.teleport && this.changeMoveCounter%
              ((int)Math.floor(Math.sqrt(this.change)))==0){
            return true;
        }
        return false;
    }
    /**
     * return the current turn draw
     * uses for the special ships only
     * @return the current turn draw
     */
    public int turn(){
        return this.turn;
    }
    /**
     * return the current accelerate draw
     * uses for the special ships only
     * @return the current accelerate draw
     */
    public boolean accelerate(){
        return this.acceleration;
    }
    /**
     * determine if the ship is trying to fire
     * uses for the special ships only
     * @return if the ship trying to fire
     */
    public boolean toFire(){
        if (this.fire && this.changeMoveCounter%
              ((int)Math.floor(Math.sqrt(this.change)))==0){
            return true;
        }
        return false;
    }
    /**
     * determine if the ship is trying to rise the shield
     * uses for the special ships only
     * @return if the ship trying to rise the shield
     */
    public boolean toShield(){
        if (this.shield && this.changeMoveCounter%
              ((int)Math.floor(Math.sqrt(this.change)))==0){
            return true;
        }
        return false;
    }
    /**
     * this method is used only testing and for the special ship
     * @return name the type of this ship
     */
    @Override public String toString(){
        String name = "Drunkard";
        return name;
    }
}
