/**
 * 
 */
package oop.ex7.methods;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.main.Parser;
import oop.ex7.main.Regex;
import oop.ex7.main.SjavaException;
import oop.ex7.variables.VarFactory;
import oop.ex7.variables.Variables;

/**
 * The class for the method that return arrays
 * Override some of the method to act for the array type (like int for int[])
 * 
 * @author roigreenberg
 *
 */
public class ArrayMethod extends Methods {
	
	/**
	 * the constructor
	 * change returnType to the array type as mention above
	 * @param type - method returning type
	 * @param name - method name
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public ArrayMethod(String type, String name) 
			throws DuplicateDeclarationExcaption {
		
		super(name, "array");
		returnType = VarFactory.createVariable(type, "returnType", true,
				Parser.emptyVar, Parser.emptyVar);
	}
	
	/**
	 * Override the method so it will check return of array and of instance
	 *  of the array
	 * @see oop.ex7.methods.Methods#isReturnLegal(java.lang.String, 
	 * 	java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void isReturnLegal(String returnValue, ArrayList<Variables> variables,
			ArrayList<Variables> localVariables ,ArrayList<Methods> methods)
					throws SjavaException {
		if (returnValue != null && returnValue.matches(Regex.ARR_VALUE)){

			Matcher matchReturn = Regex.pattArrValue.matcher(returnValue);
			matchReturn.find();
			if (matchReturn.group(1) != null){
				Matcher match = Regex.pattMultiVar.matcher(matchReturn.group(1));
				while (match.find()) {
					if (!this.returnType.isValueCorrect(match.group(1), 
							variables, localVariables, methods)){
						throw new SjavaException();
					}
				}
			} 
		} else {
			super.isReturnLegal(returnValue, variables, localVariables, methods);
			
		}
	
	}

}
