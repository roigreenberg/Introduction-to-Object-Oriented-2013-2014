/**
 * 
 */
package oop.ex7.variables;

import oop.ex7.main.SjavaException;

/**
 * @author roigreenberg
 *
 */
public class WrongValueTypeExcaption extends SjavaException {

	/**
	 * default constructor
	 */
	public WrongValueTypeExcaption() {
		super("wrong value for variable");
		System.err.println("wrong value for variable");
	}

	/**
	 * constructor
	 * @param exception - messege for the exception
	 */
	public WrongValueTypeExcaption(String exception) {
		super(exception);
		System.err.println(exception);
	}

}
