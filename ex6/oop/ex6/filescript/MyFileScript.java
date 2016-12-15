/**
 * 
 */
package oop.ex6.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


/**
 * the main class, the Manager
 * @author roigreenberg
 *
 */
public class MyFileScript {
	
	/**
	 * the manager, takes the directory and command file, read it and then print
	 * the files at the directory according to the command file.
	 * @param args - path of source  directory and command file
	 */
	public static void main(String[] args) {
//		Parsing Parser = null;

			try {
			String sourceDirPath = args[0];
			String commandFilePath = args[1];
			
		
			Parsing Parser = new Parsing(commandFilePath);
			ArrayList<Section> sections = Parser.CreateSections();

			Print.printFiles(sections, sourceDirPath);
			
			} catch (Exception e) {
				System.err.println("ERROR");
				
//				throw new ErrorIIException();
			} 

	}
	

}
