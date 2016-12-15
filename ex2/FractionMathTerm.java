/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents a special case of binary math term.
 * It should be rendered as a fraction
 * @param firstTerm
 * @param secondTerm
 * @author רועי
 */
public class FractionMathTerm extends BinaryMathTerm    {
	/**
	 * Constructs a new Fraction term
	 * @param firstTerm - Term on the numerator ("Mone")
	 * @param secondTerm - Term on the denominator ("Mechane")
	 */
    public FractionMathTerm(MathTerm firstTerm, MathTerm secondTerm){
    	
        super(firstTerm,secondTerm);
        
    }
    /**
	 * Generates the latex representation of this fraction math term.
	 * @overrides toLatex in class BinaryMathTerm
	 * @returns latex representation of this fraction math term using 
	 * 			the \frac latex command
	 */
    public java.lang.String toLatex(){
    		
            return this.setTerm("\\frac{ " + this.firstTerm.toLatex() +
            		" }{ " + this.secondTerm.toLatex() + " }");
    }
}
