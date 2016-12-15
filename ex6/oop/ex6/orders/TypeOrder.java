/**
 * 
 */
package oop.ex6.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * order file according to file's type
 * @author roigreenberg
 *
 */
public class TypeOrder implements Order, Comparator<File> {
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see oop.ex6.orders.Order#compare(java.lang.Object, java.lang.Object)
	 * the compration is according to the type of the file
	 * in case of even, the compration will be like absOrder
	 * @return result - number indicate the order between the files
	 */
	@Override
	public int compare(File file1, File file2){

		int index1 = file1.getName().lastIndexOf(".")+1;
		int index2 = file2.getName().lastIndexOf(".")+1;
		int result = String.valueOf(file1.getName().substring(index1))
				.compareTo(file2.getName().substring(index2));

		if (result != 0)
			return result;
		else
			return  new AbsOrder().compare(file1, file2);
	}
	
}
