/**
 * 
 */
package oop.ex7.variables;

import oop.ex7.main.Regex;

/**
 * class of instance represent variable of char type
 * @author roigreenberg
 *
 */
public class CharVar extends Variables {
	private static String value = "\\s*"+Regex.CHAR+"s*"; 
	/**
	 * the constructor
	 * @param type - variable type
	 * @param name - variable name
	 */
	public CharVar(String name, String type){
		super(name, type, value);
	}

}
