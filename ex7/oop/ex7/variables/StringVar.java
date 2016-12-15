/**
 * 
 */
package oop.ex7.variables;

import oop.ex7.main.Regex;

/**
 * class of instance represent variable of string type
 * @author roigreenberg
 *
 */
public class StringVar extends Variables {

	private static String value = "\\s*"+Regex.STRING+"s*"; 
	/**
	 * the constructor
	 * @param type - variable type
	 * @param name - variable name
	 */
	public StringVar(String name, String type){
		super(name, type, value);
	}

}
