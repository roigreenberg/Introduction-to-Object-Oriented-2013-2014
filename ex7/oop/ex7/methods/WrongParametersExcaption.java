/**
 * 
 */
package oop.ex7.methods;

import oop.ex7.main.SjavaException;

/**
 * @author roigreenberg
 *
 */
public class WrongParametersExcaption extends SjavaException {

	/**
	 * 
	 */
	public WrongParametersExcaption() {
		super("wrong parameters for method");
		System.err.println("wrong parameters for method");
	}

	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public WrongParametersExcaption(String exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

}
