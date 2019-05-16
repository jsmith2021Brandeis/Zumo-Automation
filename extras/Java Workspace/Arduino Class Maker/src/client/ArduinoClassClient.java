package client;
import java.util.Scanner;

import classGenerators.ArduinoClassCpp;
import classGenerators.ArduinoClassH;
import enums.ArduinoClassField;

/**Name: Jacob Smith 
 * Date 5/14/2049 Personal Study, uses ArduinoClasses to autaomtically generate header, class, and keyword files
 * Bugs: array position hardcoded*/
public class ArduinoClassClient {
	public static void main (String[]args) {
				//This example generates a class represented as a string
				//The user can decide how these string will be inputted
				//These fields are the minimum required to generate an arduino class
				
				//use any of examples
				simplestExample();
				//scannerExample();
				
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings with no input or output
	 * */
	public static void simplestExample(){
		//input String needed to create files as hardcoded
		
		//Titles are one word
		String className="Timer";
		//This information is useful in the header comment
		String author="Jacob Smith";
		String organization="Brandeis Robotics Club";
				
			
		String headerComments="A timer class to allow the user to create loops and maintain program control";
			
		String supportedBoards="ARDUINO_AVR_UNO ESP8266_WEMOSD1R1";
		//variables will always be private, I ain't in the business of allowing bad programming
		//format of variables is dataType varName
		String variables="";
		variables+="long|initTime|the beginning time of the interval"+"\n";
		variables+="Apple|test|a test varible for the parser";
				
		//Methods are an array of strings, each string is format
		//returnType methodName comments
		//method bodies are the code that goes in the methods
		String publicMethods="";
			
		publicMethods+="initTime=millis();\n\n";
		publicMethods+="long|resetTime|resets the Initial Time|\n";
		publicMethods+="initTime=millis();\nreturn getTime();\n\n";
		publicMethods+="long|getTime|returns the current time|\n";
		publicMethods+="return millis()-initTime;\n\n";
		publicMethods+="long|getAndResetTime|returns the current time and the initial time|\n";
		publicMethods+="long curTime=getTime();\nresetTime();\nreturn curTime;\n\n";
			
					
		String  privateMethods=null;
	
		//call helper method to display the generated files
		ArduinoClassClient.createFiles(className, author, organization, headerComments, supportedBoards, variables, privateMethods, publicMethods);
			
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings using Scanner as input
	 * */
	public static void scannerExample() {
		//initalize a new scanner to read keyboard input
		Scanner reader=new Scanner(System.in);
		//generate an array of field names
		ArduinoClassField []fields=ArduinoClassField.values();
		//create an array of the same length to hold the user's answers
		String []userAnswers=new String[fields.length];
		//temporary variable for code readability
		ArduinoClassField field;
		//iterate through all the fields, displaying prompt, example formatting, and reading user input
		for(int i=0;i<fields.length;i++){
			field=fields[i];
			//display prompt
			System.out.println(field.prompt+"\n"+field.format);
			//read user input
			userAnswers[i]=reader.nextLine();
		}
		
		try{
			//call helper method to display the generated files, array positions hardcoded
			ArduinoClassClient.createFiles(userAnswers[0], userAnswers[1], userAnswers[2], userAnswers[3], userAnswers[4], userAnswers[5], userAnswers[6], userAnswers[7]);
		}catch (Exception e){
			System.out.println("Sorry, there was a formatting error in your input, couldn't make class");
			System.out.println("Would you like to view the error? Y/N");
			String answer=reader.next().toLowerCase();
			//if user doens't enter y or n, display error prompt
			while(!(answer.equals("y") | answer.equals("n"))){
				System.out.print("Please enter Y/N:");
				answer=reader.next().toLowerCase();
			}
			if(answer.equals("y")){
				e.printStackTrace();
				sleepNoError(500);
			}
			
		}
		System.out.println("Thank you, closing down now");
		reader.close();
	
	}
	
	/**
	 * Helper method to tell computer to sleep without needing throws declaration
	 */
	private static void sleepNoError(int sleepTime){
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			System.out.println("Error with sleep statement");
		}
		
	}
	
	/**
	 * Helper method to create the header, body, and keyword files
	 * */
	private static void createFiles(String className, String author, String organization,String headerComments, String supportedBoards, String variables, String privateMethods, String publicMethods){
		ArduinoClassCpp template=new ArduinoClassCpp(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
		System.out.println(template.toString());
		ArduinoClassH template2=new ArduinoClassH(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
		System.out.println(template2.toString());
		System.out.println(template2.getKeywords());
	}
}