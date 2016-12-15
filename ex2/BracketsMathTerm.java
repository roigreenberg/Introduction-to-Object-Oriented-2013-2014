/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class represents a math term between brackets
 * @author רועי
 */
public class BracketsMathTerm extends MathTerm{

    MathTerm internalTerm;
    /**
	 * The constructor receives the MathTerm they will be rendered
	 * as the term inside the brackets
	 * @param internalTerm - The term that resides within the brackets
	 */
    public BracketsMathTerm(MathTerm internalTerm){
    	
        this.internalTerm = internalTerm;
    }
    /**
	 * Generates the latex representation of for this bracket math term
	 * @overrides toLatex in class BinaryMathTerm
	 * @return string that represent a term in brackets in latex
	 */
    public java.lang.String toLatex(){
    	
       
        return this.setTerm("\\left( " + internalTerm.toLatex() +
        		" \\right)");
    }
}
