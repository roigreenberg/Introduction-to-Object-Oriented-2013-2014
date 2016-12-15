/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * decorator to opposite filters
 * @author roigreenberg
 *
 */
public class NotFilter implements Filter {
	Filter filter;
	public NotFilter(Filter filter){
		this.filter = filter;
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return - true if file DID NOT pass the filter
	 */
	@Override
	public boolean isPass(File file) {
		return (!filter.isPass(file));
	}

}
