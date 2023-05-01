package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataIsPlural {

	//The code in the main method of this class has been taken from the class 
	//ColorConverter.java by Professor Klukowska, and manipulated in a way to suit the needs of this program.
	public static void main(String[] args) throws Exception {
		if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//check that the file typed into the command line argument is an existing file
		File myFile = new File(args[0]); 
		if (!myFile.exists()){
			System.err.println("Error: the file " + myFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!myFile.canRead()){
			System.err.println("Error: the file " + myFile.getAbsolutePath() +
						" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//open the file for reading and catch error if the file cannot be opened
		//print error to System.err
		CSV myCSV = null;
		
		try {
			myCSV = new CSV(new Scanner(myFile, "UTF-8"));
		}
		catch(FileNotFoundException ex) {
			System.err.println("Error: the file "+myFile.getAbsolutePath()+
					" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//read CSV file content and store data from CSV file in a DataSetList object named "list"
		DataSetList list = new DataSetList();
		ArrayList<String> thisRow = null;
		String edition = null; //will carry date
		String headline = null; //will carry title
		String text = null; //will carry description
		Scanner parseLinks = null;
		ArrayList<URL> links = new ArrayList<URL>(); //will carry links
		
		//the program needs to read in the data by row rather than reading
		//in each individual element separately
		//so use while loop to iterate through each row and store the data
		myCSV.getNextRow();
		int i = 0;
		while(i < myCSV.getNumOfRows()) {	
			thisRow = myCSV.getNextRow();
			links = new ArrayList<URL>();
			i++;
			try {
				edition = thisRow.get(0); //gets the first element in the row (which is the date)
			}
			catch (IllegalArgumentException ex) {
				continue;
			}
			try {
				headline = thisRow.get(2); //gets the third element in the row (which is the title)
				//the second element is skipped because it just represents the position which we already account
				//for by writing .get(#) where # represents the position
			}
			catch (NoSuchElementException | IllegalArgumentException ex) {
				continue;
				//if the element is empty then we throw an exception and skip it
				//also catch illegal arguments
			}
			try {
				text = thisRow.get(3); //gets fourth element in row (the description)
			}
			catch (NoSuchElementException | IllegalArgumentException ex) {
				continue;
				//if the element is empty then we throw an exception and skip it
				//also catch illegal arguments
			}
			try {
				list = new DataSetList();
				parseLinks = new Scanner(thisRow.get(4));
				parseLinks.useDelimiter("\n");
				//parse through fifth element in row to get all of the links and store them
				while(parseLinks.hasNextLine() == true) {
					links.add(new URL(parseLinks.next()));
				}
			}
			catch (NoSuchElementException | MalformedURLException | IllegalArgumentException ex) {
				continue; //catch all possible exceptions that may occur
			}
		}
		
		
		//user input time!!
		//create scanner to scan user input
		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";

		//prompt user for input
		System.out.println("Enter query or 'quit' to stop: ");		

		do {
			//read user input
			userValue = userInput.nextLine();
	
			//if user input it null or empty then state invalid and prompt again
			if (userValue == null || userValue.isEmpty() == true) {
				System.out.println("This is not a valid query. Try again.");
				userValue = userInput.nextLine();
			}
			
			//if user input begins with "title" search through headlines for titles with userValue
			if(userValue.startsWith("title")) {
				if(list.getByTitle(userValue) == null) {
					System.out.println("No matches found. Try again.");
					userValue = userInput.nextLine();
				}
				list.getByTitle(userValue);
			}
			
			//if user input begins with "description" search through text for titles with userValue
			if(userValue.startsWith("description")) {
				list.getByDescription(userValue);
				if(list.getByDescription(userValue) == null) {
					System.out.println("No matches found. Try again.");
					userValue = userInput.nextLine();
				}
				list.getByDescription(userValue);
			}
			
			//if user input begins with "URL" search through links for titles with userValue
			if(userValue.startsWith("url")) {
				list.getByURL(userValue);
				if(list.getByURL(userValue) == null) {
					System.out.println("No matches found. Try again.");
					userValue = userInput.nextLine();
				}
				list.getByURL(userValue);
			}
			System.out.println("-----"); //print line for formatting purposes
		}
		
		while (userValue.isBlank() == false && !(userValue.contains("quit"))); //instructions on when to keep program running
		userInput.close();
	}
}
