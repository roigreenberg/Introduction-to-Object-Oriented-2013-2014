

import java.awt.Image;


import oop.ex3.*;

/**
 * This class implement the user spaceship.
 * This ship available to execute all 4 actions according to user choose 
 * The actions are:
 * 1. Teleport 2. Move 3. activate shield 4. fire 
 
 * @author Roi Greenberg
 */
public class Humen extends SpaceShip {

    
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * THe actions that tried to execute for this ship are 
     * 1. Teleport 2. Move 3. activate shield 4. fire 
     * Also it regenerate unit of energy at the end of the turn  
     * 
     * @param game the game object to which this ship belongs.
     * 
     * override doAction in class SpaceShip
     */
    @Override
    public void doAction(SpaceWars game) {
        this.shieldState=false;
        if (game.getGUI().isTeleportPressed()==true) {
                this.teleport();
        }
        getMove(game);

        if (game.getGUI().isShieldsPressed()){
                this.shieldOn();
        }

        if (game.getGUI().isShotPressed()){
                this.fire(game);
        }
        this.fireCounter++;

        regeneration();
        
    }
    
    /**
     * moving the ship in this round. 
     * This is called once per round by doAction.
     * the ship will move according to user choose
     * 
     * @param game the game object to which this ship belongs.
     * 
     * override getMove in class SpaceShip
     */
    private void getMove(SpaceWars game){
        int turn = STAY_STRIGHT;
        if (game.getGUI().isLeftPressed()==true){
                turn = this.left;
        } else if (game.getGUI().isRightPressed()==true){
                turn = this.right;
        }
        this.getPhysics().move(game.getGUI().isUpPressed(), turn);
    }
    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     * 
     * override getImage in class SpaceShip
     */
    @Override
    public Image getImage(){
        if (this.shieldState){
                return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
    return GameGUI.SPACESHIP_IMAGE;
    	
   
        
    }
    
    /**
     * this method is used only testing and for the special ship
     * @return name the type of this ship
     */
    @Override public String toString(){
        String name = "humen";
        return name;
    }

}
