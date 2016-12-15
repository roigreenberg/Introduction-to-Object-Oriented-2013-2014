package oop.ex7.main;

/**
 * Execption
 * @author roigreenberg
 *
 */
public class SjavaException extends Exception {
	/**
	 * default constructor
	 */
	public SjavaException() {
		super("wrong s-java code");
		System.err.println("wrong s-java code");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public SjavaException(String exception) {
		super(exception);
		System.err.println(exception);
	}
}

