/**
 * 
 */
package oop.ex7.variables;

import java.util.ArrayList;

import oop.ex7.main.SjavaException;
import oop.ex7.methods.Methods;

/**
 * class of instance represent array.
 * this class use as decorator of the array instance
 * @author roigreenberg
 *
 */
public class ArrayVar extends Variables {
	private Variables valueVar;
	/**
	 * the constructor
	 * set the constructor with the values of the array type
	 * change 'isArray' to be true and initialized the variable
	 * @param valueVar - variable of the type of the array
	 */
	public ArrayVar(Variables valueVar){
		super (valueVar.name, valueVar.type, valueVar.value);
		this.isArray = true;
		this.valueVar = valueVar;
		this.initVar();
	}
	
	
	/**
	 * override the method to so it will check the correctness agains variable type 
	 * @see oop.ex7.variables.Variables#isValueCorrect(java.lang.String, 
	 * java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public boolean isValueCorrect(String data, ArrayList<Variables> variables
			,ArrayList<Variables> localVariables, ArrayList<Methods> methods)
					throws SjavaException {
		return valueVar.isValueCorrect(data, variables,
				localVariables, methods);
	}

	
}
