/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * abstarct class for filters with String parameter
 * @author roigreenberg
 *
 */
public abstract class StringFilters implements Filter {
	String param;
	public StringFilters(String param){
		this.param = param;
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 */
	@Override
	public boolean isPass(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
