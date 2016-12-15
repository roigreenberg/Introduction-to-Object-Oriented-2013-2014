/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to name contain given String
 * @author roigreenberg
 *
 */
public class ContainsFilter extends StringFilters implements Filter {
	public ContainsFilter(String param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file name contain the given parameter String
	 */
	@Override
	public boolean isPass(File file) {
		return (file.getName().contains(param));
	}
}
