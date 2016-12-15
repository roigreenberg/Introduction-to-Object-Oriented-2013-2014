/**
 * 
 */
package oop.ex7.arraysToCompiler;

import oop.ex7.methods.Methods;

/**
 * this class used for saving code of method 'return' for later 
 * compling checks
 * @author roigreenberg
 */
public class MethodReturn {
	public String returnValue;
	public Methods method;
	/**
	 * * the constructor - create an instance of method return statement
	 * @param returnValue - the value that return
	 * @param method - the method
	 */
	public MethodReturn(String returnValue, Methods method){
		this.method = method;
		this.returnValue = returnValue;

	}
}
