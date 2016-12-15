/**
 * 
 */
package oop.ex7.main;

import oop.ex7.main.SjavaException;

/**
 * @author roigreenberg
 *
 */
public class NoSuchElementExcaption extends SjavaException {

	/**
	 * default constructor
	 */
	public NoSuchElementExcaption() {
		super("No such element");
		System.err.println("No such element");
	}

	/**
	 * constructor
	 * @param exception - messege for the exception
	 */
	public NoSuchElementExcaption(String exception) {
		super(exception);
		System.err.println(exception);
	}

}
