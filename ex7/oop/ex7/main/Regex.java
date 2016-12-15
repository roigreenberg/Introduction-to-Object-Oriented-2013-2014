/**
 * 
 */
package oop.ex7.main;

import java.util.regex.Pattern;

/**
 * this class holds All the regular exprations(regex) and pattern use in the program 
 * @author roigreenberg
 *
 */
public class Regex {
	public static final String IGNORE = "//.*|\\s*|\\}.*";
	public static final String IF = "\\s*if\\s*\\(\\s*(.+)\\s*\\)\\s*\\{\\s*";
	public static final String WHILE = "\\s*while\\s*\\(\\s*(.+)"
			+ "\\s*\\)\\s*\\{\\s*";
	public static final String METHOD_NAME = "([a-zA-Z][\\w]*)";
	public static final String TYPE = "(int|double|String|char|boolean)";
	public static final String VAR_NAME = "(_\\w+|[a-zA-Z][\\w]*)";
	public static final String METHOD_PARAM = "(\\s*"+TYPE+"(\\s*\\[\\])?\\s*"
										+VAR_NAME+")";
	public static final String METHOD = "\\s*(void|"+TYPE + "(\\[\\])?)\\s+" + 
			METHOD_NAME + "\\s*(\\(("+METHOD_PARAM+"(,"+METHOD_PARAM+
			")*)?\\))\\s*\\{\\s*";
	public static final String METHOD_CALL = "\\s*"+METHOD_NAME+"\\s*\\(\\s*"
			+ "(([^,]+(,[^,]+)*?)?\\s*\\))\\s*;\\s*";
	public static final String INIT_VAR = "\\s*" + TYPE + "\\s+" + 
			VAR_NAME + "(\\s*=\\s*" + "(.+)" + "\\s*)?;\\s*";
	public static final String ASIGN_VAR = "\\s*" + VAR_NAME + 
			"\\s*=\\s*" + "(.+)" + "\\s*;\\s*";
	public static final String ARR_VALUE = "\\s*\\{\\s*([^,]+(,[^,]+)*?)?\\s*\\}\\s*";
	public static final String INIT_ARR = "\\s*" + TYPE + "\\s*\\[\\s*\\]\\s*" + 
			VAR_NAME + "\\s*(="+ARR_VALUE+")?;\\s*";
	public static final String ASIGN_ARR = "\\s*" + VAR_NAME + 
			"\\s*\\[(.+)\\]\\s*=\\s*" + "(.+)" + "\\s*;";
	public static final String BLOCK_END = "\\}";
	public static final String RETURN = "\\s*return\\s*(\\s+(.+)\\s*)?;\\s*";
	public static final String ILEGAL = "\\s*(true|false|if|while)|"+Regex.TYPE+"\\s*";
	public static final String INT = "-?[\\d]+";
	public static final String DOUBLE = "-?([\\d]+(\\.[\\d]+)?)";
	public static final String STRING = "\\\".*\\\"";
	public static final String CHAR = "\\'.\\'";
	public static final String BOOLEAN = "(true|false)";
	public static final String VALUE = "("+DOUBLE+"|"+INT+"|"+STRING+"|"
				+CHAR+"|"+BOOLEAN+")(\\[\\])?";
	
	public static final String OPERATOR = "\\s*-?([^\\+\\-\\*/]+)\\s*"
			+ "([\\+\\-\\*/]\\s*(.+))?\\s*";
	public static Pattern pattOperator = Pattern.compile(OPERATOR);
	public static Pattern pattInitVar = Pattern.compile(INIT_VAR);
	public static Pattern pattAsignVar = Pattern.compile(ASIGN_VAR);
	public static Pattern pattInitArr = Pattern.compile(INIT_ARR);
	public static Pattern pattMethod = Pattern.compile(METHOD);
	public static Pattern pattMethodCall = Pattern.compile(METHOD_CALL);
	public static Pattern pattAsignArr = Pattern.compile(ASIGN_ARR);
	public static Pattern pattMultiParam = Pattern.compile(METHOD_PARAM);
	public static Pattern pattReturn = Pattern.compile(RETURN);
	public static Pattern pattBoolean = Pattern.compile("\\(\\s*(true|false|.+)\\s*\\)");
	public static Pattern pattMultiVar = Pattern.compile("([^,]*[^,])");
	public static Pattern pattValue = Pattern.compile("=.+;");
	public static Pattern pattArrValue = Pattern.compile(ARR_VALUE);
}
