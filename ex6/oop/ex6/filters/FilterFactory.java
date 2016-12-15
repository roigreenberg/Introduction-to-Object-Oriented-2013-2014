/**
 * 
 */
package oop.ex6.filters;

/**
 * this class create a filter
 * @author roigreenberg
 *
 */
public class FilterFactory {
	static String filterName, param1, param2;
	/**
	 * this method create filter according to the command file
	 * @param args - the command line for creating a filter
	 * @return filter - the filter as wrote in the command or AllFilter
	 * if a problem has found at the command line
	 * @throws FilterNameErrorException - in case of problem in filter name
	 * @throws FilterParameterErrorException - in case of problem in parameters
	 * 		at the command line
	 */
	public static Filter createFilter(String args) 
			throws FilterNameErrorException, FilterParameterErrorException{
		Filter filter = new AllFilter();
		String[] params = args.split("#");
		filterName = params[0];
		try {
			param1 = params[1];
		} catch (ArrayIndexOutOfBoundsException e){
			if (!filterName.equals("all"))
				throw new FilterParameterErrorException();
		}
		double doubleParam1;
		switch (filterName){
		case("all"): {
			break;
		}
		case("greater_than"): {
			doubleParam1 = Double.parseDouble(param1) ;
			if (doubleParam1 >= 0){
				filter = new GreaterThanFilter(doubleParam1);
				break;
			} else {
				throw new FilterParameterErrorException();	
			}			
			
		}
		case("between"): {
			doubleParam1 = Double.parseDouble(param1);
			param2 = params[2];
			
			double doubleParam2 = Double.parseDouble(param2);
			if ((doubleParam1 >= 0) && (doubleParam1<=doubleParam2)){
				filter = new BetweenFilter(doubleParam1,doubleParam2);
				break;
			} else {
				throw new FilterParameterErrorException();	
			}	
			
		}
		case("smaller_than"): {
			doubleParam1 = Double.parseDouble(param1) ;
			if (doubleParam1 >= 0){
				filter = new SmallerThanFilter(doubleParam1);
				break;
			} else {
				throw new FilterParameterErrorException();		
			}	
			
		}
		case("file"): {
			filter = new FileFilter(param1);
			break;
		}
		case("contains"): {
			filter = new ContainsFilter(param1);
			break;
		}
		case("prefix"): {
			filter = new PrefixFilter(param1);
			break;
		}
		case("suffix"): {
			filter = new SuffixFilter(param1);
			break;
		}
		case("writable"): {
			if (param1.equals("YES") || param1.equals("NO")){
				filter = new WritableFilter(param1);
				break;
			} else {
				throw new FilterParameterErrorException();
			}
		}
		case("executable"): {
			if (param1.equals("YES") || param1.equals("NO")){
				filter = new ExecutableFilter(param1);
				break;
			} else {
				throw new FilterParameterErrorException();	
			}	
		}
		case("hidden"): {
			if (param1.equals("YES") || param1.equals("NO")){
				filter = new HiddenFilter(param1);
				break;
			} else {
				throw new FilterParameterErrorException();	
			}	
		}
		default:
			throw new FilterNameErrorException();	
		}
		if (!args.endsWith("NOT")){
			return filter;
		} else {
			return new NotFilter(filter);
		}
	}
}
