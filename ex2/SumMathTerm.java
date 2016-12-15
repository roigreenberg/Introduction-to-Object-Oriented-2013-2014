/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents a mathematical sum. It comprised of 3 MathTerms:
 *  - Term beneath the sigma sign.
 *  - Term above the sigma sign.
 *  - Term being summed. 
 *  Use the \sum latex command, to generate the latex representation of
 *  this MathTerm.
 * @author רועי
 */
public class SumMathTerm extends MathTerm{
	
    private MathTerm subTerm;
    private MathTerm superTerm;
    private MathTerm sumTerm;
    /**
	 * The constructor receives the 3 MathTerm that comprises the sum 
	 * math term.
	 * @param subTerm - The term beneath the sigma.
     * @param superTerm - The term above the sigma.
     * @param sumTerm - The summed term.
	 */
    public SumMathTerm(MathTerm subTerm, MathTerm superTerm, 
    		MathTerm sumTerm){
    	
        this.subTerm = subTerm;
        this.superTerm = superTerm;
        this.sumTerm = sumTerm;
    }
    /**
	 * Generates the latex representation of the this simple math term.
	 * @overrides     toLatex in class MathTerm
	 * @return latex representation using the \sum command.
	 */
    public java.lang.String toLatex(){
    	
        return this.setTerm("\\sum_{ " + this.subTerm.toLatex() + " }^{ " 
        + this.superTerm.toLatex() + " }{ " + this.sumTerm.toLatex() + " }");
    }
}
