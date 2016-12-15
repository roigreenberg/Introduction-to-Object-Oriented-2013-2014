/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to it's hidden state
 * @author roigreenberg
 *
 */
public class HiddenFilter extends BooleanFilters implements Filter {
	public HiddenFilter(String param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file is/isn't(according to parameter) hidden
	 */
	@Override
	public boolean isPass(File file) {
		if (yes){
			return (file.isHidden());
		} else {
			return (!file.isHidden());
		}
	}
}
