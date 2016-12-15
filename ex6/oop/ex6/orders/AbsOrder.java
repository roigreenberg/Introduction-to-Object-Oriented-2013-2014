/**
 * 
 */
package oop.ex6.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * order file according to file's path
 * @author roigreenberg
 *
 */
public class AbsOrder implements Order, Comparator<File> {

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see oop.ex6.orders.Order#compare(java.lang.Object, java.lang.Object)
	 * the compration is according to the path of the file
	 * @return result - number indicate the order between the files
	 */
	@Override
	public int compare(File file1, File file2) {
			return String.valueOf(file1.getPath())
					.compareTo(file2.getPath());
	}

}
