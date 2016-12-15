/**
 * 
 */
package oop.ex7.main;

import oop.ex7.main.SjavaException;

/**
 * @author roigreenberg
 *
 */
public class DuplicateDeclarationExcaption extends SjavaException {

	/**
	 * default constructor
	 */
	public DuplicateDeclarationExcaption() {
		super("Element already exist");
		System.err.println("Element already exist");
	}

	/**
	 * constructor
	 * @param exception - messege for the exception
	 */
	public DuplicateDeclarationExcaption(String exception) {
		super(exception);
		System.err.println(exception);
	}

}
