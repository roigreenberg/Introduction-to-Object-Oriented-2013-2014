/**
 * 
 */
package oop.ex7.methods;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.variables.VarFactory;

/**
 * class of instance represent method with double type retrun
 * @author roigreenberg
 *
 */
public class DoubleMethod extends Methods{
	/**
	 * the constructor
	 * @param type - method returning type
	 * @param name - method name
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public DoubleMethod(String type, String name) 
			throws DuplicateDeclarationExcaption {
		super(name,type);
	}

}
