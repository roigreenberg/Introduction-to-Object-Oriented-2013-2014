/**
 * 
 */
package oop.ex6.orders;

import oop.ex6.filters.*;


/**
 * this class create a order
 * @author roigreenberg
 *
 */
public class OrderFactory {
	static String orderName;
	/**
	 * this method create order according to the command file
	 * @param args - the command line for creating a order
	 * @return order - the order as wrote in the command or default order
	 * if a problem has found at the command line 
	 * @throws OrderErrorException -in case of problem in filter name
	 */
	public static Order createOrder(String args)
			throws OrderErrorException {
		Order order = new AbsOrder();
		String[] params = args.split("#");
		orderName = params[0];		
		switch(orderName){
		case("abs"):{
			break;
		}
		case("type"):{
			order = new TypeOrder();
			break;
		}
		case("size"):{
			order =  new SizeOrder();
			break;
		}
		default:
			throw new OrderErrorException();	
		}
		if (!args.endsWith("REVERSE")){
			return order;
		} else {
			return new ReverseOrder(order);
		}
	}
}
