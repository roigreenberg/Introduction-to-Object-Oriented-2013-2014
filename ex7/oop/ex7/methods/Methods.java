/**
 * 
 */
package oop.ex7.methods;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.main.Parser;
import oop.ex7.main.Regex;
import oop.ex7.main.SjavaException;
import oop.ex7.variables.VarFactory;
import oop.ex7.variables.Variables;

/**
 * Abstract class for all Methods
 * Contain some method to operate on the method instance
 * @author roigreenberg
 *
 */
public abstract class Methods {
	public ArrayList<Variables> paramList = new ArrayList<Variables>();
	public Variables returnType;
	protected String name;
	protected String type;

	/**
	 * the constructor - create instance of Method
	 * @param name - the method name
	 * @param type - the method returning type
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public Methods(String name, String type) throws DuplicateDeclarationExcaption{
		this.name = name;
		this.type = type;
		returnType = VarFactory.createVariable(type, "returnType", false,
				Parser.emptyVar, Parser.emptyVar);
	}
	
	/**
	 * returns true iff the given name equal to the method name
	 * @param name - name of method
	 * @return true iff the given name equal to the method name
	 */
	public boolean isEqual(String name) {

		return this.name.equals(name);
	}
	

	/**
	 * checks if the call is legal according to sjava rules
	 * @param values - parameters values
	 * @param variables - outer scope variables list
	 * @param localVariables - local variables list
	 * @param methods - the existing method list
	 * @throws SjavaException
	 */
	public void isCallLegal(String values, ArrayList<Variables> variables,
			ArrayList<Variables> localVariables ,ArrayList<Methods> methods)
					throws SjavaException {
		if (values != null) {
			Matcher matchVar = Regex.pattMultiVar.matcher(values);
			for (Variables var: paramList){
				try {
					matchVar.find();
					var.isValueCorrect(values.substring(matchVar.start(), 
						matchVar.end()), variables, localVariables, methods);
				} catch (SjavaException e) {		
					throw new WrongParametersExcaption();
				}
			}
			if (matchVar.find()){
				throw new WrongParametersExcaption();
			}
		} else if (!paramList.isEmpty())
			throw new WrongParametersExcaption();
	}
	
	/**
	 * checks if the return statement is legal
	 * @param returnValue - the return statement
	 * @param variables - outer scope variables list
	 * @param localVariables - local variables list
	 * @param methods - the existing method list
	 * @throws SjavaException
	 */
	public void isReturnLegal(String returnValue, ArrayList<Variables> variables,
			ArrayList<Variables> localVariables ,ArrayList<Methods> methods)
					throws SjavaException {
		if (returnValue != null){
			
			try {
				this.returnType.isValueCorrect(returnValue, variables,
						localVariables, methods);
			} catch (SjavaException e){
				throw new WrongReturnStatementExcaption();
			}
		} else {
			throw new WrongReturnStatementExcaption();
		}

	}
	
	/**
	 * check if there a method with the given name
	 * @param methods - the existing method list
	 * @param methodName - method name
	 * @return the method with the given name or null if not exist
	 */
	public static Methods isMethodExists (ArrayList<Methods> methods, String methodName){
		for (Methods method:methods){
			if (method.isEqual(methodName)){
				return method;
			}
		}
		return null;
	}
	
}
