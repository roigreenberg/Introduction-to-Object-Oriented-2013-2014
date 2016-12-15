/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to name contain equal to String
 * @author roigreenberg
 *
 */
public class FileFilter implements Filter {
	String param;
	public FileFilter(String param){
		this.param = param;
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file name equal to the given parameter String
	 */
	@Override
	public boolean isPass(File file) {
		return (file.getName().equals(param));
	}
}
