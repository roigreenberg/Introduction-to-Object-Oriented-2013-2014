/**
 * 
 */
package oop.ex6.filescript;

/**
 * Execption in case there a problem with the section heads
 * @author roigreenberg
 *
 */
public class SectionErrorException extends Exception {	
	/**
	 * default constructor
	 */
	public SectionErrorException() {
		super("Bad Section heads");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public SectionErrorException(String exception) {
		super(exception);
	}
}
