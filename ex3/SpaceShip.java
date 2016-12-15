
import java.awt.Image;
import oop.ex3.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{

    private static final int START_HEALTH = 20;
    private static final int HEALTH_REDUCE = 1;
    private static final int MAX_ENERGY_LEVEL = 200;
    private static final int ENERGY_SHIELD_REDUCE = 3;
    private static final int ENERGY_FIRE_REDUCE = 20;
    private static final int ENERGY_HIT_REDUCE = 10;
    private static final int ENERGY_TELEPORT_REDUCE = 150;
    private static final int ENERGY_COLISION_RISING = 20;

    private static final int REGENERATE_ENERGY = 1;
    
    private final int LEFT = 1;
    private final int RIGHT = -1;
    private SpaceShipPhysics position;

    /**
     * all the protected variable as protected because it uses in subclasses
     */
    
    protected int left = LEFT;
    protected int right = RIGHT;
    protected int maxEnergy=MAX_ENERGY_LEVEL;
    protected int currentEnergy = maxEnergy;
    protected int health = START_HEALTH;
    protected int fireCounter = FIRE_LIMIT;
    protected final int STAY_STRIGHT = 0;
    protected static final int FIRE_LIMIT = 8;
    protected boolean shieldState = false;
    

    /**
     * Construct a spaceship.
     * create the physics object for the spaceship.
     */
    protected SpaceShip(){
            this.position = new SpaceShipPhysics();
    }
	
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    abstract void doAction(SpaceWars game);
    
    /**
     * Gets the physics object of the closest ship
     * @param game the game object.
     * @return the physics object of the closest ship
     */
    protected SpaceShipPhysics closestShip(SpaceWars game){
        return game.getClosestShipTo(this).getPhysics();
    }
    
    /**
     * moving the ship in this round. 
     * This is called once per round by doAction.
     * this used for ships that turn according to closest ship angle
     * @param game the game object to which this ship belongs.
     * @param acceleration define if the ship will accelerate
     */
    protected void getMove(SpaceWars game, boolean acceleration){
    	int turn = this.right;
        if (this.getPhysics().angleTo(this.closestShip(game))>0){
                turn = this.left;
        }
        this.getPhysics().move(acceleration, turn);
    }
    
    
    /**
     * Regenerate the current energy of the ship by REGENERATE_ENERGY
     * unit(s)
     */
    protected void regeneration(){
        if (currentEnergy < maxEnergy){
            currentEnergy+=REGENERATE_ENERGY;
	}
    }
    
    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
    	if (shieldState == false){
            gotHit();
    	} else {
            maxEnergy+=ENERGY_COLISION_RISING;
            currentEnergy+=ENERGY_COLISION_RISING;
    	}
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
    	maxEnergy=MAX_ENERGY_LEVEL;
    	currentEnergy = maxEnergy;
    	health = START_HEALTH;
    	setPhysics(new SpaceShipPhysics());
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health==0;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    protected SpaceShipPhysics getPhysics() {
        return position;
    }
    /**
     * Sets the physics object that controls this ship in case
     * it need a new position(such as after teleporting or death)
     * 
     * @param pos new position to set to he ship
     */
    protected void setPhysics(SpaceShipPhysics pos) {
    	this.position = pos;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
    	if (shieldState == false){
            this.health-=HEALTH_REDUCE;
            isDead();

        } else {
            this.maxEnergy-=ENERGY_HIT_REDUCE;
            if (this.maxEnergy<0){
                this.maxEnergy=0;
            }
            if (this.currentEnergy>this.maxEnergy){
                this.currentEnergy=this.maxEnergy;
            }
            
    		
    	}
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
    	if (this.shieldState){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    	}
    	return GameGUI.ENEMY_SPACESHIP_IMAGE;
     
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
    	if (this.currentEnergy >= ENERGY_FIRE_REDUCE  && 
            this.fireCounter>=FIRE_LIMIT){
            this.currentEnergy -= ENERGY_FIRE_REDUCE;
            game.addShot(this.position);
            this.fireCounter=0;
    		
    	}
    	
       
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
    	if (this.currentEnergy > ENERGY_SHIELD_REDUCE){
            this.currentEnergy -= ENERGY_SHIELD_REDUCE;
            this.shieldState = true;
    	}
    	
        
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
    	if (this.currentEnergy>=ENERGY_TELEPORT_REDUCE) {
    	    setPhysics(new SpaceShipPhysics());
            this.currentEnergy-=ENERGY_TELEPORT_REDUCE;

    	}
       
    }
    
     /**
     * this method is using as getter for the JUNIT tests only
     * @return the reduce by hit
     */
    public static int getHealthReduce(){
        return HEALTH_REDUCE;
    }
    /**
     * this method is using as getter for the JUNIT tests only
     * @return the energy reduce by hit
     */
    public static int getEnergyReduce(){
        return ENERGY_HIT_REDUCE;
    }
    /**
     * this method is using as getter for the JUNIT tests only
     * @return the energy reduce by hit
     */
    public static int getEnergyRising(){
        return ENERGY_COLISION_RISING;
    }
    /**
     * this method is using as getter for the JUNIT tests only
     * @return the energy regenerate every turn
     */
    public static int getRegenerate(){
        return REGENERATE_ENERGY;
    }
    /**
     * this method is using as getter for the JUNIT tests only
     * @return the energy reduce while firing
     */
    public static int getEnergyFireReduce(){
        return ENERGY_FIRE_REDUCE;
    }
    
    
}
