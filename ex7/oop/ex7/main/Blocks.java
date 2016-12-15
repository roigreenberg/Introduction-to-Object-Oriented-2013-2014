/**
 * 
 */
package oop.ex7.main;

import java.util.ArrayList;

import oop.ex7.methods.Methods;

/**
 * A class for blocks: if,whiles and methods.
 * save the code line inside the block for later use.
 * @author roigreenberg
 *
 */
public class Blocks {
	public Methods method;
	public String condition;
	public ArrayList<String> block;
	/**
	 * the constuctor
	 * @param method - the block method. null in case of if and while.
	 * @throws SjavaException 
	 */
	public Blocks(Methods method) throws SjavaException{
		this.method = method;
		block = new ArrayList<String>();
	}
	
	/**
	 * add code line to block array for later use.
	 * @param line - internal code line
	 */
	public void add(String line){
		block.add(line);
	}
}
