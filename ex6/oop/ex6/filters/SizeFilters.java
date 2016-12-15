/**
 * 
 */
package oop.ex6.filters;

import java.io.File;

/**
 * abstract class for filter with numeric parameters
 * @author roigreenberg
 *
 */
public abstract class SizeFilters implements Filter {
	double param;
	public static final int KILO = 1024;
	public SizeFilters(double param) {
		this.param = param*KILO;
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 */
	@Override
	public boolean isPass(File file) {
		// TODO Auto-generated method stub
		return true;
	}

}
