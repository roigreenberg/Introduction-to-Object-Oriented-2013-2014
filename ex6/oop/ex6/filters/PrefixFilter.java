/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to name start with given String
 * @author roigreenberg
 *
 */
public class PrefixFilter extends StringFilters implements Filter {
	public PrefixFilter(String param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file name start with the given parameter String
	 */
	@Override
	public boolean isPass(File file) {
		
		return (file.getName().startsWith(param));
	}
	

}
