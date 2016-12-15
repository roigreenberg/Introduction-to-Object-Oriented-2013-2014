/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to size smaller than parameter
 * @author roigreenberg
 *
 */
public class SmallerThanFilter extends SizeFilters implements Filter {
	public SmallerThanFilter(double param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file size smaller than parameter
	 */
	public boolean isPass(File file){
		
		return (file.length() < param);
	}
}
