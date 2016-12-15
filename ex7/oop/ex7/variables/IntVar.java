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
 * class of instance represent variable of int type
 * @author roigreenberg
 *
 */
public class IntVar extends Variables {
	
	private static String value = "\\s*-?"+Regex.INT+"\\s*"; 
	/**
	 * the constructor
	 * @param type - variable type
	 * @param name - variable name
	 */
	public IntVar(String name, String type){
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
		if (data.matches(Regex.OPERATOR)){
			Matcher match = Regex.pattOperator.matcher(data);
			match.find();
			super.isValueCorrect(match.group(1), variables,
					localVariables, methods);
			if (match.group(3) != null)
				super.isValueCorrect(match.group(3), variables,
						localVariables, methods);
		} else {
			super.isValueCorrect(data, variables,
					localVariables, methods);
		}
		return true;
	}

}
