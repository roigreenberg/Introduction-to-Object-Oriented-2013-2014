/**
 * 
 */
package oop.ex7.variables;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.sasl.SaslException;

import org.hamcrest.core.IsSame;

import oop.ex7.main.Regex;
import oop.ex7.main.SjavaException;
import oop.ex7.methods.Methods;

/**
 * Abstract class for all Variable
 * Contain some method to operate on the variable instance
 * @author roigreenberg
 *
 */
public abstract class Variables implements Cloneable{
	
	public String name;
	public String type;
	protected String value;
	public boolean isArray = false;
	public boolean initialized = false;
	private static final String METHOD_NAME = Regex.METHOD_NAME+"\\s*\\(\\s*(.*)\\s*\\)\\s*";
	private static final String VAR_NAME = Regex.VAR_NAME+"\\s*(\\[\\s*(.+)\\s*\\])?";
	Pattern pattVarName = Pattern.compile(VAR_NAME);
	Pattern pattMethodName = Pattern.compile(METHOD_NAME);

	/**
	 * the constructor - create instance of Variable
	 * @param name - the variable name
	 * @param type - the variable type
	 * @param value - the regex pattern of the variable value
	 */
	public Variables(String name, String type, String value){
		this.name = name;
		this.type = type;
		this.value = value;
	}
	/**
	 * changes the initialized feature of the variable to true
	 */
	public void initVar(){
		initialized = true;
	}

	
	/**
	 * check if the given data that ask to be assign to the variable is of
	 * correct type.
	 * It check if the data is of type of method call, ather variable or value
	 * and call the right method to check the correctness of the data.
	 * @param data - the value to check
	 * @param variables - outer scope variables list
	 * @param localVariables - local variables list
	 * @param methods - the existing method list
	 * @return true iff the data is correct
	 * @throws SjavaException - in case of Sjava compilation error
	 */
	public boolean isValueCorrect(String data, ArrayList<Variables> variables,
			ArrayList<Variables> localVariables ,ArrayList<Methods> methods)
					throws SjavaException {


		if (data.matches(VAR_NAME) && !data.matches(Regex.ILEGAL)){
			Matcher match = pattVarName.matcher(data);
			match.find();
			Variables existVar = this.isVarExists(localVariables, variables, match.group(1));

			if (this.isCorrect(existVar) ) {

				return true;
			} else {
				throw new WrongValueTypeExcaption();
			}

		} else if (data.matches(METHOD_NAME) && !data.matches(Regex.ILEGAL)){
			Matcher match = pattMethodName.matcher(data);
			match.find();

			Methods existMethod = Methods.isMethodExists(methods, match.group(1));
			if (existMethod != null && this.isCorrect(existMethod)) {
				existMethod.isCallLegal(match.group(2), variables,
						localVariables, methods);

				return true;
			} else {
				throw new WrongValueTypeExcaption();
			}
		} else {
			if (this.isCorrect(data)) {
				return true;
			} else {
				throw new WrongValueTypeExcaption();
			}
		}
				
	}
	
	/**
	 * check if the data is similar to the variable type
	 * @param data - string of a value to assign
	 * @return true iff the data type is correct
	 * @throws SjavaException
	 */
	public boolean isCorrect(String data) throws SjavaException{
		return (data.matches(value));
	}
	
	/**
	 * check if the given variable is initialized and from the same type of this
	 * variable
	 * @param var - the variable to assign to this one
	 * @return true iff the assignment is legal
	 */
	public boolean isCorrect(Variables var){
		return var != null && var.initialized && 
				this.type.equals(var.type) && !this.isArray;
	}
	/**
	 * check if the given method return the same type of this one
	 * @param method the method to assign to this one
	 * @return true iff the assignment is legal
	 * @throws WrongValueTypeExcaption  - in case method is void
	 */
	public boolean isCorrect(Methods method) throws SjavaException{
		if (method.returnType == null)
			throw new WrongValueTypeExcaption();		
		return this.type.equals(method.returnType.type);
	}
	
	/**
	 * returns true iff the given string is equal to the variable name
	 * @param name - the name to check
	 * @return true iff the given string is equal to the variable name
	 */
	public boolean isEqual(String name){
		return this.name.equals(name);
	}

	/**
	 * check if there a variable with the given name
	 * @param variables - outer scope variables list
	 * @param localVariables - local variables list
	 * @param varName - variable name
	 * @return the variable with the given name or null if not exist
	 */
	public static Variables isVarExists (ArrayList<Variables> localVariables,
			ArrayList<Variables> variables, String varName){
		for (Variables var:localVariables){
			if (var.isEqual(varName))
				return var;
		}
		for (Variables var:variables){
			if (var.isEqual(varName))
				return var;
		}
		return null;
	}
}
