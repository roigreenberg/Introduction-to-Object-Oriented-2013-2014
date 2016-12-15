/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to name end with given String
 * @author roigreenberg
 *
 */
public class SuffixFilter extends StringFilters  implements Filter {
	public SuffixFilter(String param){
		super(param);
	} 
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file name end with the given parameter String
	 */
	@Override
	public boolean isPass(File file) {
		
		return (file.getName().endsWith(param));
	}
}
