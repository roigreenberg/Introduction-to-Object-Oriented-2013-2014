/**
 * 
 */
package oop.ex7.methods;

import oop.ex7.main.SjavaException;

/**
 * @author roigreenberg
 *
 */
public class WrongReturnStatementExcaption extends SjavaException {

	/**
	 * default constructor
	 */
	public WrongReturnStatementExcaption() {
		super("wrong return statment for method");
		System.err.println("wrong return statment for method");
	}

	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public WrongReturnStatementExcaption(String exception) {
		super(exception);
		System.err.println(exception);
	}

}
