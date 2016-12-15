/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to it's writability
 * @author roigreenberg
 *
 */
public class WritableFilter extends BooleanFilters implements Filter {
	public WritableFilter(String param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file is/isn't(according to parameter) writable
	 */
	@Override
	public boolean isPass(File file) {
		if (yes){
			return (file.canWrite());
		} else {
			return (!file.canWrite());
		}
	}
}
