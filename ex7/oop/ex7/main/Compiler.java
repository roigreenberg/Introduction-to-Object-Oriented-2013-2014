/**
 * 
 */
package oop.ex7.main;

import java.util.ArrayList;

import oop.ex7.arraysToCompiler.*;
import oop.ex7.methods.Methods;
import oop.ex7.variables.Variables;

/**
 * this class complete the code check after the parser finished.
 * @author roigreenberg
 *
 */
public class Compiler {
	ArrayList<Variables> localVariables = new  ArrayList<>();
	ArrayList<Variables> variables = new  ArrayList<>();
	ArrayList<MethodCall> methodCall = new ArrayList<>();
	ArrayList<MethodParams> methodsParams = new ArrayList<>();
	ArrayList<VariableAsign> varsAsign = new ArrayList<>();
	ArrayList<MethodReturn> methodsReturn = new ArrayList<>();

	MethodReturn methodReturn;
	public boolean isMethod = false;
	
	
	/**
	 * the constructor. save all the code line need to be checked later
	 * each array save the code need to be check or the existing variables
	 * @param variables - the outer block variables
	 * @param localVariables - the inner block variables
	 * @param methodsParams - the methods parameters
	 * @param methodCall - call for methods
	 * @param varsAsign - values to assign to variables
	 * @param methodsReturn - methods 'return''s
	 * @param isMethod - true iff this compile a method
	 */
	public Compiler (ArrayList<Variables> variables,
					 ArrayList<Variables> localVariables,
					 ArrayList<MethodParams> methodsParams,
					 ArrayList<MethodCall> methodCall,
					 ArrayList<VariableAsign> varsAsign,
					 ArrayList<MethodReturn> methodsReturn,
					 Boolean isMethod) {
		
		this.isMethod = isMethod;
		this.localVariables = localVariables;
		this.variables = variables;
		this.methodCall = methodCall;
		this.methodsParams = methodsParams;
		this.varsAsign = varsAsign;
		this.methodsReturn = methodsReturn;
	}
	
	/**
	 * method to check if method call is legal
	 * @param methodCall - contain the method and call parameters
	 * @throws SjavaException
	 */
	public void compileMethodCall(MethodCall methodCall) throws SjavaException{
		methodCall.method.isCallLegal(methodCall.params, variables, 
				localVariables,	Parser.methods);
	}
	
	/**
	 * method to check if you can assign a variable
	 * @param varAsign - contain the variable and the assignment value
	 * @throws SjavaException
	 */
	public void compileAsignVar(VariableAsign varAsign) throws SjavaException{

		if ((varAsign.var != null)){
			varAsign.var.isValueCorrect(varAsign.value, variables,localVariables,
					Parser.methods);
			
			varAsign.var.initVar();
		} else {
			throw new NoSuchElementExcaption("no such variable");
		}
	}
	

	/**
	 * method to check if 'return' statement is correct
	 * @param methodReturn - contain the method and the return value
	 * @throws SjavaException
	 */
	public void compileReturn(MethodReturn methodReturn) throws SjavaException{

			methodReturn.method.isReturnLegal(methodReturn.returnValue, variables, 
					localVariables,	Parser.methods);
	
	}

}
