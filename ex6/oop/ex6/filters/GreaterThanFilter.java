/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to size greater than parameter
 * @author roigreenberg
 *
 */
public class GreaterThanFilter extends SizeFilters implements Filter {
	public GreaterThanFilter(double param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file size greater than parameter
	 */
	public boolean isPass(File file){
		return (file.length() > param);
	}

}
