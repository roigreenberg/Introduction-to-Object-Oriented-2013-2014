/**
 * 
 */
package oop.ex6.orders;

/**
 * Execption in case there a problem with the order name
 * @author roigreenberg
 *
 */
public class OrderErrorException extends Exception {
	/**
	 * default constructor
	 */
	public OrderErrorException() {
		super("Not Such Order");
	}
	/**
	 * constructor
	 * @param exception  - messege for the exception
	 */
	public OrderErrorException(String exception) {
		super(exception);
	}
}
