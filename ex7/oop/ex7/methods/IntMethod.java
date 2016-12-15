/**
 * 
 */
package oop.ex7.methods;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.variables.VarFactory;

/**
 * class of instance represent method with int type retrun
 * @author roigreenberg
 *
 */
public class IntMethod extends Methods{
	/**
	 * the constructor
	 * @param type - method returning type
	 * @param name - method name
	 * @throws DuplicateDeclarationExcaption- in case variable already exist 
	 */
	public IntMethod(String type, String name)
			throws DuplicateDeclarationExcaption {
		super(name,type);
	}
}
