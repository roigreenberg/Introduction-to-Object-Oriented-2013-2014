/**
 * This class implement the Special spaceship.
 * This ship available to execute all 4 actions 
 * The ship specialty is that it allwas act as the closest ship
 * 
 * @author Roi Greenberg
 */
public class Special extends SpaceShip {
    

    private static final boolean ACCELARATE = true;
    // helper ship variable. explaind in doAction
    private final Drunkard demoShip = new Drunkard();
    
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * this ship behevior is to always the same as the closest ship
     * (exept when it closest to another special ship then it will try to 
     * teleport while accelereting)
     * in case of being closest to Drunkard ship, the randomaly behavior will 
     * be calculated in the demoShip, Drunkard class ship that created only for
     * this and then be used by the ship itself.
     * 
     * see all other classes for explanation about their doAction
     * 
     * @param game the game object to which this ship belongs.
     * 
     *
     * 
     * override doAction in class SpaceShip
     */
    @Override
    public void doAction(SpaceWars game){
        
            String ship = game.getClosestShipTo(this).toString();
            
            switch (ship) {
                
                case "Runner":
                    this.left = -1;
                    this.right = 1;
                    if (this.getPhysics().distanceFrom(this.closestShip(game))
                            <0.2 && 
                        this.getPhysics().angleTo(this.closestShip(game))<0.2){
                        this.teleport();
                    }
        
                    getMove(game, ACCELARATE);
                    break;
                    
                case "humen":
                    this.shieldState=false;
                    if (game.getGUI().isTeleportPressed()==true) {
                            this.teleport();
                    }
                    
                    int turn = STAY_STRIGHT;
                    if (game.getGUI().isLeftPressed()==true){
                            turn = this.left;
                    } else if (game.getGUI().isRightPressed()==true){
                            turn = this.right;
                    }
                    this.getPhysics().move(game.getGUI().isUpPressed(), turn);

                    if (game.getGUI().isShieldsPressed()){
                            this.shieldOn();
                    }

                    if (game.getGUI().isShotPressed()){
                            this.fire(game);
                    }
                    this.fireCounter++;
                    break;
                    
                case "Basher": 
                    this.left = 1;
                    this.right = -1;
                    this.shieldState = false;
                
                    getMove(game, ACCELARATE);

                    if (this.getPhysics().distanceFrom(this.closestShip(game))<0.2){
                            this.shieldOn();
                    }
                    break;
                    
                case "Aggressive":
                    this.left = 1;
                    this.right = -1;
                    getMove(game, ACCELARATE);

                    if (this.getPhysics().distanceFrom(this.closestShip(game))<0.2){
                            this.fire(game);
                    }
                    this.fireCounter++;
                    
                    break;
                case "Drunkard": 
                    
                    
                    this.shieldState=false;
                    
                    if (demoShip.toTeleport()){
                        this.teleport();
                    }
                    
                    this.getPhysics().move(demoShip.accelerate(),
                            demoShip.turn());
                    
                    if (demoShip.toShield()){
                        this.shieldOn();
                    }
                    
                    if (demoShip.toFire()){
                        this.fire(game);
                    }
                    this.fireCounter++;
                    
                    break;
                    
                case "Special":
                    
                    this.teleport();
                    
                    break;
                
                }
            regeneration();
    }
    
    /**
     * this method is used only testing and for the special ship
     * @return name the type of this ship
     */
    @Override public String toString(){
        String name = "Special";
        return name;
    }
}
