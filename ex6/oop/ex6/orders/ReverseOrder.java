/**
 * 
 */
package oop.ex6.orders;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

/**
 * decorator to reverse orders
 * @author roigreenberg
 *
 */
public class ReverseOrder implements Order,Comparator<File> {
	Order order;
	public ReverseOrder (Order order){
		this.order = order;
	}
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see oop.ex6.orders.Order#compare(java.lang.Object, java.lang.Object)
	 * @return the opposite of the result from the original order
	 */
	@Override
	public int compare(File file1, File file2) {
		return -1*order.compare(file1, file2);

	}

}
