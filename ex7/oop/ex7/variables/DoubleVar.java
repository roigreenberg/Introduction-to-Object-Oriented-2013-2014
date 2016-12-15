/**
 * 
 */
package oop.ex7.variables;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.Regex;
import oop.ex7.main.SjavaException;
import oop.ex7.methods.Methods;

/**
 * class of instance represent variable of double type
 * @author roigreenberg
 *
 */
public class DoubleVar extends Variables {

	private static String value = "\\s*"+Regex.DOUBLE+"s*"; 
	/**
	 * the constructor
	 * @param type - variable type
	 * @param name - variable name
	 */
	public DoubleVar(String name, String type){
		super(name, type, value);
	}
	
	/**
	 * override the method to extend it to support operations
	 * @see oop.ex7.variables.Variables#isValueCorrect(java.lang.String,
	 *  java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public boolean isValueCorrect(String data, ArrayList<Variables> variables
			,ArrayList<Variables> localVariables, ArrayList<Methods> methods)
					throws SjavaException {
		Matcher match = Regex.pattOperator.matcher(data);
		match.find();
		super.isValueCorrect(match.group(1), variables,
				localVariables, methods);
		if (match.group(3) != null)
			super.isValueCorrect(match.group(3), variables,
					localVariables, methods);

		return true;
	}
	
	
	/**
	 * override the method to extend it to support also int type
	 * @see oop.ex7.variables.Variables#isCorrect(oop.ex7.variables.Variables)
	 */
	public boolean isCorrect(Variables var){
		return var != null && var.initialized && 
				(this.type.equals(var.type) || "int".equals(var.type));
	}
	/**
	 * override the method to extend it to support also int type
	 * @see oop.ex7.variables.Variables#isCorrect(oop.ex7.methods.Methods)
	 */
	public boolean isCorrect(Methods method) throws SjavaException{
		if (method.returnType == null)
			throw new WrongValueTypeExcaption();	
		return (this.type.equals(method.returnType.type) ||
				"int".equals(method.returnType.type));
	}


}
