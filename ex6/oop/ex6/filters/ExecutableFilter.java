/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * filter file according to it's executability
 * @author roigreenberg
 *
 */
public class ExecutableFilter extends BooleanFilters implements Filter {
	public ExecutableFilter(String param){
		super(param);
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file is/isn't(according to parameter) executable
	 */
	@Override
	public boolean isPass(File file) {
		if (yes){
			return (file.canExecute());
		} else {
			return (!file.canExecute());
		}
	}
}
