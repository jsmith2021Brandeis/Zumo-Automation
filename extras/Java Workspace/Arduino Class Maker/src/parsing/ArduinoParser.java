package parsing;

/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment:  Personal Study, provides methods relevent to parsing an Arduino Class
 *  	This is a seperate class to keep classes short and to allow client to access there server methods for input validation
 *  Date: May 15, 2019
 *  Sources: 
 *  Bugs:
 */

public class ArduinoParser {
	
	/**Insert internal tabs into a string separated by newline characters
	 * @param firstLine whether to insert a tab into the first line*/
	public static String insertTabs(String base, int numTabs,boolean firstLine){
		MiniScanner reader=new MiniScanner();
		reader.prime(base,"\n");
		String withTabs="";
		String line;
		//if there is no next string, return the base
		if(!reader.hasNext()){return base;}
		//if firstLine is meant to be tabbed, insert one
		if(firstLine){withTabs+=genTab(numTabs);}
		//add the next character, previous if statement confirme there will be a next character
		withTabs+=reader.next()+"\n";
		while(reader.hasNext()){
			line=reader.next();
			withTabs+=genTab(numTabs)+line+"\n";
		}
		return withTabs;
		
	}
	/** Generates a tab of the specified depth
	 * */
	private static String genTab(int numTabs){
		String tab="";
		for(int i=1;i<=numTabs;i++){
			tab+="    ";
		}
		return tab;
	}
}
