/**
 * 
 */
package oop.ex6.filescript;

import java.util.ArrayList;

import oop.ex6.filters.*;
import oop.ex6.orders.*;

/**
 * the Section class
 * create section with filter, order and warnings lines
 * @author roigreeberg
 *
 */
public class Section {
	
	Filter filter;
	Order order;
	ArrayList<Integer> warnings;
	/**
	 * the constructor
	 * @param filter - the filter for this section
	 * @param order - the order for this section
	 * @param warnings - the lines with warnings.
	 */
	public Section(Filter filter, Order order, ArrayList<Integer> warnings){
		this.filter = filter;
		this.order = order;
		this.warnings = warnings;

	}

}
