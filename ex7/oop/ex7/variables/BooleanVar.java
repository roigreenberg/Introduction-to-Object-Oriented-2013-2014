/**
 * 
 */
package oop.ex7.variables;

import java.util.ArrayList;

import oop.ex7.main.Regex;
import oop.ex7.methods.Methods;

/**
 * class of instance represent variable of boolean type
 * @author roigreenberg
 *
 */
public class BooleanVar extends Variables {
	private static String value = "\\s*"+Regex.BOOLEAN+"s*"; 
	/**
	 * the constructor
	 * @param type - variable type
	 * @param name - variable name
	 */
	public BooleanVar(String name, String type){
		super(name, type, value);
	}
}
