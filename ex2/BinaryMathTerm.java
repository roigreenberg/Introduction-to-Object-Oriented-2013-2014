/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class represents a term composed of exactly two independent
 * math terms
 * @author רועי
 */

public class BinaryMathTerm extends MathTerm {

    protected MathTerm firstTerm;
    protected MathTerm secondTerm;
    /**
	 * Constructs an new BinaryMathTerm
	 * @param firstTerm - the first math term
	 * @param seconfTerm - the second math term
	 */
    public BinaryMathTerm(MathTerm firstTerm, MathTerm secondTerm){
    	
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        
    }
    /**
	 * Unimplemented in this class. However, should be implemented in 
	 * any of its subclasses.
	 */
    public java.lang.String toLatex(){
    	
        return "";
    }
}
