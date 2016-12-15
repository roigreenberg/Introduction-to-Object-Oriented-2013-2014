/**
 * 
 */
package oop.ex7.methods;

import java.util.ArrayList;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.main.SjavaException;
import oop.ex7.variables.Variables;

/**
 * @author roigreenberg
 *
 */
public class VoidMethod extends Methods {

	/**
	 * the constructor
	 * change the returnType to null.
	 * @param type - method returning type
	 * @param name - method name
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public VoidMethod(String type, String name)
			throws DuplicateDeclarationExcaption {
		super(name,type);
		returnType = null;
	}
	/**
	 * checks if the return statement is legal
	 * oeride the method to check if the return is null
	 * @see oop.ex7.methods.Methods#isReturnLegal(java.lang.String,
	 *  java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void isReturnLegal(String returnValue, ArrayList<Variables> variables,
			ArrayList<Variables> localVariables, ArrayList<Methods> methods)
					throws SjavaException{
		if (returnValue != null)
			throw new WrongReturnStatementExcaption();
	
	}

}
