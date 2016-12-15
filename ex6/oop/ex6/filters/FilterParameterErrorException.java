/**
 * 
 */
package oop.ex6.filters;

/**
 * Execption in case there a problem with the filter parametres
 * @author roigreenberg
 *
 */
public class FilterParameterErrorException extends Exception {
	/**
	 * default constructor
	 */
	public FilterParameterErrorException() {
		super("wrong parameters");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public FilterParameterErrorException(String exception) {
		super(exception);
	}
}
