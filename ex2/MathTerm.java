/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents the base class for all other MathTerms 
 * classes you will implement in the exercise. It defines the interface
 * (the public part of the classes) for all other math terms which will
 * extend it. Most importantly, it defines the method "toLatex" that 
 * should be overridden in each of the extending classes. This class 
 * is not meant to be instantiated at any point, the only classes that
 * will be instantiated are classes that extend it. Thus, the 
 * "toLatex" method should be left unimplemented (return "").
 * It will be implemented, however, in any of this class' subclasses.
 * The rest of the interface (setExponentTerm, setters and getters, etc.)
 * or any additions you may want to add to this class (as long as they 
 * are hidden do not modify its interface), can and should be implemented
 * in this class. Later on this course, we will find a more elegant way
 * to "enforce" subclasses to implement a method rather than leaving 
 * unimplemented methods (abstract, interface). Note: The interface 
 * allows to "annotate" the term with bar (upper line), negation or with 
 * other MathTerm as exponent. When more than one of these annotations 
 * are set, please evaluate the latex representation in the following 
 * order: exponent, bar and then negation.
 * @author roigreenberg
 */
public class MathTerm extends java.lang.Object{
	
    
    protected boolean isBarred;
    protected boolean isNegated;
    protected MathTerm exponentTerm;
    /**
	 * Default constructor
	 */
    public MathTerm(){
    	
    	
    }
    /**
	 * This method gets a math term to be placed as an exponent for the 
	 * current math term. For example. If our current MathTerm is "a" 
	 * and the user passes "b". Then our Mathterm will be rendered as 
	 * "a^{ b }".
	 * @param exponentTerm - The MathTerm to be placed as an exponent
	 * 		 of the current term.
	 */
    public void setExponentTerm(MathTerm exponentTerm){
    	
         this.exponentTerm = exponentTerm;
    }
    /**
	 * Returns the exponent math term.
	 * @return The exponent MathTerm of this term.
	 */
    public MathTerm getExponentTerm(){
    	
        return exponentTerm;
    }
    /**
	 * Setting whether this MathTerm should be barred or not (a straight
	 * line on top of the term: see Latex's \overline{}).
	 * @param isBarred - true if we want this term to be barred.
	 */
    public void setIsBarred(boolean isBarred){
    	
        this.isBarred = isBarred;
    }
    /**
	 * isBarred getter
	 * @returns whether this math term was set to be barred.
	 */
    public boolean getIsBarred(){
    	
        return isBarred;
    }
    /**
	 * Sets whether this math term should be negated (see Latex's \neg{}).
	 * @param isNegated -  true if we want this term to be nagated
	 */
    public void setIsNegated(boolean isNegated){
    	
        this.isNegated = isNegated;
    }
    /**
	 * isNegated getter.
	 * @param True if this term should be negated.
	 */
    public boolean getIsNegated(){
    	
        return isNegated;
    }
    /**
	 * This method should be implemented in any of MathTerm derivatives
	 */
    public java.lang.String toLatex(){
    	
        return "";
    }
    /**
	 * set the term with exponent, barres and/or negated
	 * @return new string with the represention in latex of
	 * 	exponent, barred or negated.
	 */
    protected java.lang.String setTerm(java.lang.String term){
    	
        if (this.getExponentTerm() != null){
            term = term + "^{ " + this.getExponentTerm().toLatex() + " }";
        }
        if (this.getIsBarred()){
            term ="\\overline{ " + term + " }";
        }
        if (this.getIsNegated()){
            term ="\\neg{ " + term + " }";
        }
        return term;
    }
}
