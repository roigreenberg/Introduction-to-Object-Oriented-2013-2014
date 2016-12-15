/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents a math term which is either a single letter
 * variable (x,y,a,b,etc..) or a number (may be a floating point number).
 * The latex representation is straight forward the name of the variable,
 * or the number itself. However, in case this term represents a number,
 * the class will allow to user to control the precision of its latex
 * representation, that is - the number of digits to the right of the 
 * floating dot.
 * @author רועי
 */

public class SimpleMathTerm extends MathTerm {
	
    protected java.lang.String termName;
    private int precisionDigits;
    protected java.lang.String term ;  
    /**
	 * Constructs a new instance given a simple term "name"
	 * @param termName - A string of either a single letter variable
	 *  (x,y,z,a,b..) or a number (may be a floating point number).
	 */
    public SimpleMathTerm(java.lang.String termName){
    	
        this.termName = termName;
        // another variable so the original termName wont be change
        this.term = termName;
    }
    /**
	 * Sets the number of digits of precision in case this term 
	 * represents a number.
	 * change term according to the precisionDigits.
	 * @param precisionDigits - Number of digits right of the 
	 * 			floating point on the latex representation.
	 */
    public void setPrecisionDigits(int precisionDigits){
    	
        this.precisionDigits = precisionDigits;
        double number = Double.parseDouble(this.termName);
        double precisNum = (int) (number*(Math.pow(10,this.precisionDigits)))
        		/((double) (Math.pow(10,this.precisionDigits)));
        this.term =  Double.toString(precisNum);
        if (precisionDigits == 0) {
        	int naturalNum = (int) precisNum;
        	this.term =  Integer.toString(naturalNum);
        }
        	
    }
    /**
	 * Checks the given name. And determines whether it's numeric.
	 *
	 * @Returns true if this term represents a number.
	 */
    public boolean isNumeric() {
    	
        if (this.termName.charAt(0)=='-') {
            return Character.isDigit(this.termName.charAt(1));
        }
        
        return Character.isDigit(this.termName.charAt(0));
        
    }
    /**
	 * Generates the latex representation of the this simple math term.
	 * @overrides     toLatex in class MathTerm
	 * @return the Latex representation. If this term represents a
	 * variable, this method returns the variable name. Otherwise,
	 * if the term represents a number it should be trimmed to 
	 * according to the precision parameter.
	 * also change the term is exponent, barred or negated in needed
	 */
    public java.lang.String toLatex(){
    	
    	return this.setTerm(this.term);
    }
}
