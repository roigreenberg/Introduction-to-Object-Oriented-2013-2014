/**
 * 
 */
package oop.ex6.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * order file according to file's size
 * @author roigreenberg
 *
 */
public class SizeOrder implements Order,Comparator<File> {

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see oop.ex6.orders.Order#compare(java.lang.Object, java.lang.Object)
	 * the compration is according to the size of the file
	 * in case of even, the compration will be like absOrder
	 * @return result - number indicate the order between the files
	 */
	@Override
	public int compare(File file1, File file2) {
		int result = Long.valueOf(file1.length())
				.compareTo(file2.length());
		
		if (result != 0)
			return result;
		else
			return new AbsOrder().compare(file1, file2);

}









}
