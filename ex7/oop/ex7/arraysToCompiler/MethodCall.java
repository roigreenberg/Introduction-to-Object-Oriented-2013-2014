/**
 * 
 */
package oop.ex7.arraysToCompiler;

import java.util.ArrayList;

import oop.ex7.methods.Methods;

/**
 * this class used for saving code of calling to method for later compling
 * checks
 * @author roigreenberg
 */
public class MethodCall {
	public String params;
	public Methods method;
	/**
	 * * the constructor - create an instance of method call
	 * @param params - the method parameters
	 * @param method - the method that being called
	 */
	public MethodCall(String params, Methods method){
		this.method = method;
		this.params = params;

	}
}
