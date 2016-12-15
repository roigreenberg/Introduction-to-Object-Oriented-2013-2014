/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * interface for all the filter
 * @author roigreenberg
 *
 */
public interface Filter {
	
	/**
	 * derermine if a file pass the filter
	 * @param file - file to filter
	 * @return true if the file pass the filter
	 */
	public boolean isPass(File file);
}
