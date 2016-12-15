/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents a simple operation between two other terms.
 * For example, "a+b", "c*d" or "c=d"
 * @author רועי
 */
public class SimpleBinaryOpMathTerm extends BinaryMathTerm {
	
    private char sign;
    /**
	 * Instantiate a new SimpleBinaryOpMathTerm
	 * @param firstTerm - The first term of the binary operation.
     * @param secondTerm - The second term of the binary operation.
     * @param sign - The operation sign. 
     * 		  Can be any of the following: "+,-,*,<,>,=".
	 */
    public SimpleBinaryOpMathTerm(MathTerm firstTerm, MathTerm secondTerm, 
            char sign){
    	
        super(firstTerm,secondTerm);
        
        this.sign = sign;
    }
    /**
	 * Generates the latex representation of this arithmetic operation
	 * math term
	 * @overrides     toLatex in class BinaryMathTerm
	 * @returns The latex representation of the operation: 
	 * 			"firstTerm operationSign secondTerm".
	 */
    public java.lang.String toLatex(){
    	
        if (sign == '*') {
            return this.setTerm(this.firstTerm.toLatex()+ " \\cdot " 
            		+ this.secondTerm.toLatex());
        }
        else {
            return this.setTerm(this.firstTerm.toLatex() + this.sign 
            		+ this.secondTerm.toLatex());
        }
    }
}
