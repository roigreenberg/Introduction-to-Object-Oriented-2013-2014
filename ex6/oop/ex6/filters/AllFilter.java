/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * default filter
 * @author roigreenberg
 *
 */
public class AllFilter implements Filter {
	
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true always
	 */
	public boolean isPass(File file){
		return true;
	}
}
