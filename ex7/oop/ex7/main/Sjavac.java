/**
 * 
 */
package oop.ex7.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.monitor.CounterMonitorMBean;

import oop.ex7.arraysToCompiler.MethodCall;
import oop.ex7.arraysToCompiler.MethodReturn;
import oop.ex7.arraysToCompiler.VariableAsign;

/**
 * The main program. The manager
 * call the parser firstly, for reading the file and initilized it.
 * then call the compiler to finish the compiling check.
 * @author roigreenbeg
 *
 */
public class Sjavac {

	public static ArrayList<Compiler> compilers = new ArrayList<>();
	/**
	 * The manager.
	 * call the parser firstly, for reading the file and initilized it.
     * then call the compiler to finish the compiling check.
     * Print "2" in case of wrong file, "1" in case of comiliation error
     * or "0" if the code is correct.
	 * @param args - the path to the Sjava file
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			Parser p = new Parser(args[0]);
			try {
				p.Reader(p.lines);
				for (Compiler compiler: compilers){

					for (VariableAsign var: compiler.varsAsign)
						compiler.compileAsignVar(var);
					
					for (MethodCall method: compiler.methodCall)
						compiler.compileMethodCall(method);
					
					for (MethodReturn method: compiler.methodsReturn){
						compiler.compileReturn(method);
					}
						
				}
				System.out.println("0");
			} catch (SjavaException e) {
				System.out.println("1");
			}
		} catch (IOException e) {
			System.out.println("2");
		}  
	}
}
