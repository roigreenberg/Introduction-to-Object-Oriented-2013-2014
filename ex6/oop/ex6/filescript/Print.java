/**
 * 
 */
package oop.ex6.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * this class handle the printing of the files
 * @author roigreenberg
 *
 */
public class Print  {
	static File[] files;
	
	/**
	 * this method print the files name.
	 * it run of every section, filtering the files than sorted according
	 * to the order then print the warning and the files
	 * @param sections - the sections created by the parser
	 * @param onlyFiles - ArrayList of the files needed to be handled
	 */
	public static void printFiles(ArrayList<Section> sections, String sourceDirPath){
		File[] files = new File(sourceDirPath).listFiles();
		ArrayList<File> onlyFiles = new ArrayList<>();
		//take only the files
		for (File file: files)
			if (file.isFile())
				onlyFiles.add(file);
		
		ArrayList<File> filteredFiles = new ArrayList<File>();
		for (Section section: sections){
			filteredFiles.clear();
			for (File file: onlyFiles){
				if (section.filter.isPass(file)){
					filteredFiles.add(file);
				}
			}
			
			Collections.sort(filteredFiles, section.order);
			for (int w: section.warnings){
				System.out.println("Warning in line "+w);
			}
			for (File file : filteredFiles){
				System.out.println(file.getName());
			}
			
		}
		
	}

}
