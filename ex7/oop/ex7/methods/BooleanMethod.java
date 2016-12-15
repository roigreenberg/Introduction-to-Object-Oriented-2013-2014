package oop.ex7.methods; 

import oop.ex7.main.DuplicateDeclarationExcaption;


/**
 * class of instance represent method with boolean type retrun
 * @author roigreenberg
 *
 */
public class BooleanMethod extends Methods {
	
	
	/**
	 * the constructor
	 * @param type - method returning type
	 * @param name - method name
	 * @throws DuplicateDeclarationExcaption - in case variable already exist
	 */
	public BooleanMethod(String type, String name) 
			throws DuplicateDeclarationExcaption {
		super(name,type);
	}

	

}
