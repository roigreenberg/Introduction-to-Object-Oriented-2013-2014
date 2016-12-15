/**
 * 
 */
package oop.ex7.arraysToCompiler;

import oop.ex7.methods.Methods;

/**
 * this class used for saving list of parameters of new method for later 
 * compling checks
 * @author roigreenberg
 */
public class MethodParams {
	public String params;
	public Methods method;

	/**
	 * the constructor - create an instance of method parameters
	 * @param params - the method parameters
	 * @param method - the method that being created
	 */
	public MethodParams(String params, Methods method){
		this.method = method;
		this.params = params;

	}
}

