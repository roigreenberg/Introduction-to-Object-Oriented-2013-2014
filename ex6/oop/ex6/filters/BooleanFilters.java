/**
 * 
 */
package  oop.ex6.filters;

import java.io.File;

/**
 * abstract class for filters with YES/NO parameters
 * @author roigreenberg
 *
 */
public abstract class BooleanFilters implements Filter {
	boolean yes = false;
	static final String YES = "YES";
	public BooleanFilters (String param){
		if (param.equals(YES))
			yes = true;
	}
	/** (non-Javadoc)
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 */
	@Override
	public boolean isPass(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
