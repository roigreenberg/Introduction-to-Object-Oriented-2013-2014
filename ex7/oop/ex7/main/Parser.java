/**
 * 
 */
package oop.ex7.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import oop.ex7.arraysToCompiler.*;
import oop.ex7.methods.MethodFactory;
import oop.ex7.methods.Methods;
import oop.ex7.variables.*;


/**
 * The parser of the program.
 * Firstly, get the commands from the SJavac, which is the main program,
 * converts them to lines and start reading them with the Reader function 
 * Some of the code checks is happend here and other save to the 'Compiler'
 * for later(described also in the README)
 * @author roigreenberg
 *
 */

public class Parser {
	private FileReader file = null;
	private Scanner commands;
	public ArrayList<String> lines = new ArrayList<>();
	public ArrayList<Blocks> blocks;
	private ArrayList<Variables> localVariables, variables = new  ArrayList<>();
	public static ArrayList<Methods> methods = new ArrayList<>();
	private ArrayList<MethodCall> methodsCall = new ArrayList<>();
	private ArrayList<MethodParams> methodsParams = new ArrayList<>();
	private ArrayList<VariableAsign> varsAsign = new ArrayList<>();
	private ArrayList<MethodReturn> methodsReturn = new ArrayList<>();
	private String type;
	private boolean isMethod = false;
	private Methods blockMethod;
	public static ArrayList<Variables> emptyVar = new ArrayList<>();
	/**
	 * the "main" constructor - call onse at the begining
	 * Read the file and convert it to array of strings
	 * Also initialized all the Array will use in the class.
	 * @param filePath - the path of the Sjava file
	 * @throws IOException - in case of wrong file
	 */
	public Parser(String filePath) throws IOException {
		methods = new ArrayList<>();
		blocks = new ArrayList<Blocks>();
		
		localVariables = new ArrayList<>();
		Sjavac.compilers = new ArrayList<>();
		try {
			file = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			throw new IOException();
		}
		Scanner scanner = new Scanner (file);
		commands = scanner.useDelimiter("\\s*\n\\s*");
		while (commands.hasNext()) {
			
			lines.add(commands.next());
		}
		commands.close();
		scanner.close();
	}
	/**
	 * the "blocks" constructor - call for any inner block
	 * in case of method block, add the method parameters to localVariables
	 * Also initialezed new Array for inner blocks
	 * @param variables - the outer block variables
	 * @param localVariables - the inner block variables
	 * @param method - the block method. null in case of if/while block
	 */
	public Parser(ArrayList<Variables> localVariables,
			ArrayList<Variables> variables, Methods method) {
		
		this.variables = variables;
		this.blockMethod = method;
		isMethod = (blockMethod != null) ;
		this.localVariables = localVariables;
		if (isMethod){
			this.localVariables.addAll(blockMethod.paramList);
		}
		blocks = new ArrayList<Blocks>();
	}
	
	/**
	 * Knows how to read correctly the given codelines.
	 * It knows to distinguish between every variable, every array
	 * and every method.
	 * Uses the programs inside the Variables and Methods packages 
	 * to determine where every line belong and if it is correct.
	 * Also distinguishes between every block and blocks inside blocks
	 * THe parser is working mostly like "switch-case" but since it use
	 * boolean(.matches()) we did it with "if-else" structure.
	 * if none of the if get true, that mean there a wrong line.
	 * After the check create an instanse of 'compiler' for later use then run
	 * on internal block recursivly 
	 * 
	 * Note: we know the parser maybe too long and we tried to seperet it 
	 * (that also way we did the 'Compiler' and 'Regex' classes and move some
	 * methods to 'Variables' and 'Methods' (like .isCorrect()) but since there
	 * a lot of variables any time more sepereting were become vary ugly (as 
	 * can be seen at the 'compiler' creation which need 7(!) parameters)
	 * 
	 * @param codelines - the given code lines
	 * @throws SjavaException 
	 * 
	 */
	public void Reader(ArrayList<String> codelines) 
			throws SjavaException{
		Iterator<String> linesIter = codelines.iterator();
		String line;

		while (linesIter.hasNext()){
			line = linesIter.next();

			if (line.matches(Regex.METHOD) && !(isMethod)){
				Matcher match = Regex.pattMethod.matcher(line);
				match.find();
				if (match.group(2) != null)
					type = match.group(2);
				else
					type = match.group(1);
					
				Methods method = MethodFactory.createMethod(type, 
						match.group(4), !(match.group(3) == null));
				
				this.block(linesIter, line, method);
				
				methods.add(method);

				Matcher matchVar = Regex.pattMultiParam.matcher(match.group(5));
				Variables var;
				while (matchVar.find()){
					try {
						var = VarFactory.createVariable(matchVar.group(2), 
								matchVar.group(4), !(matchVar.group(3) == null),
								method.paramList, emptyVar);
						var.initVar();
						method.paramList.add(var);
					} catch (DuplicateDeclarationExcaption e) {
						throw new DuplicateDeclarationExcaption("can't duplicate"
								+ " parameters name");
					}
				}

			} else if (line.matches(Regex.METHOD_CALL)){
				Matcher match = Regex.pattMethodCall.matcher(line);
				match.find();
				Methods method = Methods.isMethodExists(methods, match.group(1));
				if (method == null)
					throw new NoSuchElementExcaption("no such method");
				MethodCall call = new MethodCall(match.group(3), method);
				methodsCall.add(call);
			
			} else if (line.matches(Regex.IF+"|"+Regex.WHILE)){
				
				Matcher match = Regex.pattBoolean.matcher(line);
				match.find();

				this.block(linesIter, line, blockMethod);
				Matcher matchCondition = Regex.pattBoolean.matcher(line);
				matchCondition.find();
				Variables condition = new BooleanVar("condition", "boolean");
				
				VariableAsign conditionValue = 
						new VariableAsign(matchCondition.group(1), condition);
				varsAsign.add(conditionValue);
	
			} else if (line.matches(Regex.INIT_VAR)){
				
				Matcher match = Regex.pattInitVar.matcher(line);
				match.find();
				Variables var;
				try {
					var = VarFactory.createVariable(match.group(1),
							match.group(2),false, localVariables, emptyVar);
					if (line.contains("=")){
						VariableAsign varAsign = 
								new VariableAsign(match.group(4), var);
						
						varsAsign .add(varAsign);

					}
					localVariables.add(var);
				} catch (DuplicateDeclarationExcaption e) {
					throw new DuplicateDeclarationExcaption("Variable already exist");
				}
			
			} else if (line.matches(Regex.ASIGN_VAR)){
				Matcher match = Regex.pattAsignVar.matcher(line);
				match.find();
				Variables var = Variables.isVarExists(localVariables, variables,
						match.group(1));
				if (var == null) 
					throw new NoSuchElementExcaption("no such variable");
				if (var.isArray)
					throw new SjavaException("can't assign an array");
				VariableAsign varAsign = new VariableAsign(match.group(2), var);
				
				varsAsign.add(varAsign);
		
			} else if (line.matches(Regex.INIT_ARR)){
				Matcher match = Regex.pattInitArr.matcher(line);
				match.find();
				Variables var;
				try {
					var = VarFactory.createVariable(match.group(1),
							match.group(2),true, localVariables, variables);
					if (line.contains("=")){
						String values = match.group(4);
						if (values != null) {
							Matcher matchVar = Regex.pattMultiVar.matcher(values);
							while (matchVar.find()){
								VariableAsign varAsign =
									new VariableAsign(matchVar.group(1), var);
								varsAsign.add(varAsign);
							}
						}
					}
					localVariables.add(var);
				} catch (SjavaException e) {
					throw new SjavaException();
				}
			
			} else if (line.matches(Regex.ASIGN_ARR)){				
				Matcher match = Regex.pattAsignArr.matcher(line);
				match.find();
				Variables var = Variables.isVarExists(localVariables, variables,
						match.group(1));
				if (match.group(2).matches("-\\s*\\d+\\s*"))
					throw new SjavaException("negetive index");
				VariableAsign indexAsign = new VariableAsign(match.group(2), 
						new IntVar("index", "int"));
				VariableAsign varAsign = new VariableAsign(match.group(3), var);
				varsAsign.add(indexAsign);
				varsAsign.add(varAsign);

			} else if (isMethod	&& line.matches(Regex.RETURN)){
					Matcher match = Regex.pattReturn.matcher(line);
					match.find();
					MethodReturn methodReturn = new MethodReturn(match.group(2),
							blockMethod);
					methodsReturn.add(methodReturn);
			
			} else if (!line.matches(Regex.IGNORE)){
		
				throw new SjavaException();
			} 

		}
	
		Compiler compiler = new Compiler(variables,
				localVariables,
				methodsParams, 
				methodsCall,
				varsAsign,
				methodsReturn,
				isMethod);

		Sjavac.compilers.add(compiler);

		localVariables.addAll(variables);
		
		for (Blocks block: blocks){

			Parser b = new Parser(new ArrayList<Variables>(),localVariables,
					block.method);
			b.Reader(block.block);
		}

	}
	 /**
	 * Method for creating instance of blocks.
	 * save the code line of inner block
	 * checks if it fits the correct rules for "{" and "}"
	 * also, if each block is opened and closes properly,
	 * not too much "{" and "}"s and if they are placed
	 * correctly.
	 * @param linesIter - the lines iterator
	 * @param line - the current line
	 * @param method - the block method. null in case of if and while.
	 * @throws SjavaException
	 */
	public void block(Iterator<String> linesIter, String line, Methods method)
			throws SjavaException{

		Blocks block = new Blocks(method);

		int counter = 1;
		if (line.matches(Regex.IF+"|"+Regex.WHILE)){
			block.condition = line;
		}
		while (counter != 0){
			try {
			line = linesIter.next();
			}catch (NoSuchElementException e) {
				throw new SjavaException();
			}
			block.block.add(line);
			if (line.matches(Regex.IF+"|"+Regex.WHILE)){

				counter += 1 ;
			}
			if (line.matches("\\s*\\}\\s*"))
				counter -= 1;
		}
		
		blocks.add(block);
	}
	
}