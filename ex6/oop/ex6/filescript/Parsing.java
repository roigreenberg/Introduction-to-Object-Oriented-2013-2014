/**
 * 
 */
package oop.ex6.filescript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import oop.ex6.filters.*;
import oop.ex6.orders.*;

/**
 * the parser. read the command file and create sections
 * @author roigreenberg
 *
 */
public class Parsing {
	
	private ArrayList<Section> sections = new ArrayList<Section>();
	private FileReader file = null;
	private Scanner commands;
	private static final String DEAFULT_ORDER = "abs";
	private static final String FILTER = "FILTER";
	private static final String ORDER = "ORDER";
	
	/** 
	 * the constructor - open the file and scan it's lines
	 * @param commandFile - path to command file
	 * @throws IOException - in case read the file failed.
	 */
	public Parsing(String commandFile) 
			throws IOException {
		try {
			file = new FileReader(commandFile);
			commands = new Scanner(file).useDelimiter("\\s*\n\\s*");
		} catch (FileNotFoundException e) {
			throw new IOException();
		}
		
		
	}
	
	/**
	 * the method run over the commands and create section as command
	 * @return ArrayList of sections
	 * @throws SectionErrorException - in case filter heads("FILTER","ORDER" 
	 * 		are wrote wrong.
	 * @throws IOException 
	 */
	public ArrayList<Section> CreateSections() 
			throws SectionErrorException, IOException{
		int lineNo = 1;
		String command = commands.next();
		while (commands.hasNext()){
			ArrayList<Integer> warnings = new ArrayList<>();
			warnings.clear();

			// deafult filter and order
			Filter filter = new AllFilter();
			Order order = new AbsOrder();

			String filterName = null, orderName = null;

			if (!command.equals(FILTER))
				throw new SectionErrorException();

			filterName = commands.next();
			lineNo++;
			
			try{
				filter = FilterFactory.createFilter(filterName);
			} catch (Exception e){
				warnings.add(lineNo);
			}
			
			
			if (commands.hasNext()){
				command = commands.next();
				lineNo++;
			} else {
				throw new SectionErrorException();
				
			}


			if (command.equals(ORDER)){
				if (commands.hasNext()){
					orderName = commands.next();
					lineNo++;
				} else {
					orderName = DEAFULT_ORDER ;
				}
			} else {
				throw new SectionErrorException();
			
			}
			
			if (!orderName.equals(FILTER)){
				try{
					order = OrderFactory.createOrder(orderName);
				} catch (OrderErrorException e){
					order = new AbsOrder();
					warnings.add(lineNo);
				}

				if (commands.hasNext()){
					command = commands.next();
					lineNo++;
				}

			} else {
				command = orderName; 
			}
			
			
			sections.add(new Section(filter, order, warnings));
			
			
		}
		commands.close();
		file.close();
		return sections;
	}
}
