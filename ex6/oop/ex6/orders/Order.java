/**
 * 
 */
package oop.ex6.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * interface for all the orders
 * extends Comparator<> in order to be comparator for sorting an array
 * @author roigreenberg
 *
 */
public interface Order extends Comparator<File>{
	/**
	 * compare between 2 file
	 *@see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(File file1, File file2);
}
