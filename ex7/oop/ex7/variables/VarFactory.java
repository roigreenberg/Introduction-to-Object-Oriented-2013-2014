/**
 * 
 */
package oop.ex7.variables;

import java.util.ArrayList;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.main.Parser;
import oop.ex7.methods.Methods;

/**
 * This class is the factory that creates all types of variables.
 * called in the parser to make sure we're adding the right type of variables.
 * @author roigreenberg
 */
public class VarFactory {
	private static Variables var;
	/**
	 * this method create variable instance according to the given parameters
	 * @param varType - the variable returning type
	 * @param varName - the variable name
	 * @param isArray - true iff the variable is an array
	 * @param localVariables 
	 * @param variables 
	 * @return variable - the created variable.
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public static Variables createVariable(String varType, String varName, 
			boolean isArray, ArrayList<Variables> localVariables,
			ArrayList<Variables> variables) 
					throws DuplicateDeclarationExcaption{
		
		if (Variables.isVarExists(localVariables, variables, varName)!=null)
			throw new DuplicateDeclarationExcaption("variable already"
					+ " exist");
		
		switch (varType){
		case ("int"): {
			var = new IntVar(varName, varType);
			break;
		}
		case ("double"): {
			var = new DoubleVar(varName, varType);
			break;
		}
		case ("String"): {
			var = new StringVar(varName, varType);
			break;
		}
		case ("boolean"): {
			var = new BooleanVar(varName, varType);
			break;
		}
		case ("char"): {
			var = new CharVar(varName, varType);
			break;
		}
		}
		if (isArray)
			return new ArrayVar(var);
		else {
			return var;
		}
	}
}
