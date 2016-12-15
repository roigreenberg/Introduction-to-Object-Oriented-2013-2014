/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import il.ac.huji.cs.oop.mastermind.*;
//import java.util.Arrays;
/**
 *
 * @author רועי
 */
public class Mastermind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Implement the 'mastermind' game
         * The user set up the game parameters (non-negetive numbers for 
         * code length, number of values, and max number of guesses) then start 
         * to try and guess the code that randomaly generate by the 
         * "CodeGenerator". 
         * After every guess the computer will show how close was the guess by
         * number of bulls(direct hit) and cows(undirect hits).
         * After finish the game the computer will show the game statistics
         * and ask the user if he wish to continue and if so if he wish to 
         * reset the game parameters.
         * 
         * 
         */
        
        
        
        MastermindUI ui = MastermindUIFactory.newMastermindUI();
        
        
        boolean continuePlaying = true;
        while (continuePlaying) {
        
            int length = ui.askNumber("Enter code length:");
            while (length <= 0) {
                ui.displayErrorMessage("Value must be positive:");
                length = ui.askNumber("Enter code length:");
                }

            int posValue = ui.askNumber("Enter number of values:");
            while (posValue <= 0) {
                ui.displayErrorMessage("Value must be positive:");
                posValue = ui.askNumber("Enter number of values:");
                }

            int maxGuesses = ui.askNumber("Enter max number of guesses:");
            while (maxGuesses <= 0) {
                ui.displayErrorMessage("Value must be positive:");
                maxGuesses = ui.askNumber("Enter max number of guesses:");
                }

            ui.reset(length, posValue, maxGuesses);
            int gamePlayed = 0;
            int gameWon = 0;
            int totalMoves = 0;

            boolean reset_game = false;        
        
            
            while (!reset_game) {
                
                int curGuesses = 0; //current number of guesses
                boolean winStatus = false;
                Code code = CodeGenerator.newCode(length,posValue);

                while (curGuesses < maxGuesses && !winStatus) {

                    int matches = 0;
                    int bulls = 0;
                    Code userGuess = ui.askGuess("Enter guess:",length);
                    
                   /* look up for matches in both codes and 
                    * takes the minimal value which promise no duplicates
                    */ 
                    for (int i = 0; i < posValue; i++) {
                        int matchUserToComp = 0;
                        int matchCompToUser = 0;
                        for (int j = 1; j <= length; j++) {
                            if (userGuess.getValue(j) == i)
                            	matchUserToComp++;
                                
                            if (code.getValue(j) == i)
                            	matchCompToUser++;
                            
                        }
                        matches += Math.min(matchUserToComp,matchCompToUser);

                    }
                    for (int j = 1; j <= length; j++)
                        if (userGuess.getValue(j) == code.getValue(j)) 
                                    bulls++;
                    
                    ui.showGuessResult(userGuess, bulls, matches-bulls);

                    curGuesses++;
                    if (bulls == length) {
                        winStatus = true;
                        gameWon++;
                        totalMoves += curGuesses;
                        ui.displayMessage("You won in " + curGuesses +
                                " turns!");
                    }
                }    
                if (winStatus == false) {
                    ui.displayMessage("You lost! You failed to find the"
                            + " code!");
                }
                    
                gamePlayed++;                
                ui.showStats(gamePlayed, gameWon,
                        (double) gameWon/gamePlayed,
                        (double) totalMoves/gameWon );
                    

                continuePlaying = ui.askYesNo("Another game?");
                if (!continuePlaying){
                    ui.close();
                    break;
                }
                reset_game = ui.askYesNo("Do you want to change the game "
                        + "options?");
                if (!reset_game) 
                    ui.clear();
                } 
            
    }
    
        
    
    }   
}       // TODO code application logic here
    
    

