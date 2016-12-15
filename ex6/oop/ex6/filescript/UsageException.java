/**
 * 
 */
package oop.ex6.filescript;

/**
 * Execption in case there a problem with the files
 * @author roigreenberg
 *
 */
public class UsageException extends Exception {
	
	/**
	 * default constructor
	 */
	public UsageException() {
		super("Bad Usage");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public UsageException(String exception) {
		super(exception);
	}
}
