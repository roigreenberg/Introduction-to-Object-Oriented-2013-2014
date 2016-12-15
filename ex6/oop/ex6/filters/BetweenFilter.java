package oop.ex6.filters;
/**
 * 
 */


import java.io.File;

/**
 * filter file according size between parameters
 * @author roigreenberg
 *
 */
public class BetweenFilter extends SizeFilters implements Filter {
	double param2;
	/**
	 * the constructor
	 * @param param1 - lower bound
	 * @param param2 - upper bound
	 */
	public BetweenFilter(double param1, double param2){
		super(param1);
		this.param2 = param2*KILO;
	}
	/**
	 * @see oop.ex6.filters.Filter#isPass(java.io.File)
	 * @return true iff file size between or equal to lower and upper bounds
	 */
	public boolean isPass(File file){
		return ((file.length() >= param) && (file.length() <= param2));
	}
}
