/**
 * 
 */
package oop.ex7.arraysToCompiler;

import oop.ex7.methods.Methods;
import oop.ex7.variables.Variables;

/**
 * this class used for saving value need to assign in variable for later 
 * compling checks
 * @author roigreenberg
 */
public class VariableAsign {
	public String value;
	public Variables var;
	/**
	 * * the constructor - create an instance of variable assignment
	 * @param value - the value to assign
	 * @param var - the variable
	 */
	public VariableAsign(String value, Variables var){
		this.value = value;
		this.var = var;

	}
}

