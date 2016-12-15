/**
 * 
 */
package oop.ex7.methods;

import oop.ex7.main.DuplicateDeclarationExcaption;
import oop.ex7.main.Parser;
import oop.ex7.variables.Variables;


/**
 * This class is the factory that creates all types of methods.
 * called in the parser to make sure we're adding the right type of method.
 * @author roigreenberg
 */
public class MethodFactory {
	private static Methods method;
	

	/**
	 * this method create method instance according to the given parameters
	 * @param methodType - the method returning type
	 * @param methodName - the method name
	 * @param isArray - true iff the method returning an array
	 * @return method - the created method.
	 * @throws DuplicateDeclarationExcaption  - in case method already exist
	 */
	public static Methods createMethod(String methodType, String methodName, 
			boolean isArray) throws DuplicateDeclarationExcaption{
		
		if (Methods.isMethodExists(Parser.methods, methodName)!=null)
			throw new DuplicateDeclarationExcaption("Method already"
					+ " exist");
		
		switch (methodType){
		case ("int"): {
			method = new IntMethod(methodType ,methodName);
			break;
		}
		case ("double"): {
			method = new DoubleMethod(methodType ,methodName);
			break;
		}
		case ("String"): {
			method = new StringMethod(methodType ,methodName);
			break;
		}
		case ("boolean"): {
			method = new BooleanMethod(methodType ,methodName);
			break;
		}
		case ("char"): {
			method = new CharMethod(methodType ,methodName);
			break;
		}
		case ("void"): {
		method = new VoidMethod(methodType, methodName);
		break;
	}
		}
		if (isArray){
			return new ArrayMethod(methodType, methodName);
		} else {
			return method;
		}
	}
}
