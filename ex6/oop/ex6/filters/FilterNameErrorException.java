/**
 * 
 */
package oop.ex6.filters;

/**
 * Execption in case there a problem with the filter name
 * @author roigreenberg
 *
 */
public class FilterNameErrorException extends Exception {
	/**
	 * default constructor
	 */
	public FilterNameErrorException() {
		super("Not Such filter");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public FilterNameErrorException(String exception) {
		super(exception);
	}
}
