package project2;

import java.net.URL;
import java.util.ArrayList;

//this class represents a data set and all of the data set's characteristics
public class DataSet implements Comparable<DataSet>{

	//declare variables
	private String title;
	private String description;
	private ArrayList<URL> links;
	private Date date;
	private String hatTips;
	
	public DataSet() {
		description = new String();
	}
	
	//constructor for DataSet with three parameters
	//throw IllegalArgumentException
	public DataSet(String title, String description, ArrayList<URL> links) throws IllegalArgumentException {
		if(title.isEmpty() || title == null) {
			throw new IllegalArgumentException("Invalid value. Title must not be empty or null.");
		} //if the title is empty or null, an IllegalArgumentException is thrown
		setTitle(title);
		//if the title is fine then the title is set using the setTitle() method
		
		if(description.isEmpty() || description == null) {
			throw new IllegalArgumentException("Invalid value. Description must not be empty or null.");
		} //if the description is empty or null, an IllegalArgumentException is thrown
		setDescription(description);
		//if the description is fine then the description is set using the setDescription() method
		
		if(links.isEmpty() || links == null) {
			throw new IllegalArgumentException("Invalid value. Links must not be empty or null.");
		} //if the URL is empty or null, an IllegalArgumentException is thrown	
		setLinks(links);
		//if the URL is fine then the URL is set using the setURL() method
	}
	
	
	//GETTERS AND SETTERS
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public ArrayList<URL> getLinks(){
		return this.links;
	}
	public void setLinks(ArrayList<URL> newLinks) {
		this.links = newLinks;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHatTips() {
		if(this.hatTips == null || this.hatTips.isEmpty() == true) {
			String x = "";
			return x;
		}
		return this.hatTips;	
	}
	public void setHatTips(String newHatTips) {
		this.hatTips = newHatTips;
	}

	
	//overrides the equals method from the object class
	//throws a ClassCastException when the code attempts to cast an object to subclass that
	//its not an instance of
	@Override
	public boolean equals(Object o) throws ClassCastException {
		if(o == null || this.getTitle() == null) {
			return false; //if object and title are null then return false
		}
		
		else if(((DataSet) o).getDate() == this.getDate() && ((DataSet) o).getTitle() == this.getTitle()) {
			return true; //if dates and titles are equal, return true
		}
		else if(o.getClass() != this.getClass() && (o == null & this.getClass() == null)) {
			return false; //if object and title are not from the same class and both are null, return false
		}
		
		return false;
	}
	
	//overrides toString method so that data set can be printed in proper format
	@Override
	public String toString() {
		String newString = null; //create newString to print out date, title, and description
		if(this.getDate() != null && this.getTitle() != null && this.getDescription() != null
				&& this.getLinks() != null) {
			newString = " " + this.getDate() + "\n" + this.getTitle()
			+ "\n" + this.getDescription(); //if date, title, and description are not null then store the value
			//of each one in newString
		
			String[] arr = new String[this.getLinks().size()]; //create String array for the links
			String r = "";
			for(int i = 0; i < this.getLinks().size(); i++) {
				r = this.getLinks().get(i).toExternalForm();
				arr[i] = r; //use for loop to iterate through the links and store as Strings
			}
			String convert = String.join("\n", arr); //join strings on separate lines
			String returnThis = newString + "\n" + convert; //create String that prints newString, a new line, 
			//and the converted String that contains the links
			return returnThis; //return the String
		}
		
		//if there is no date, print everything the same as above except without the date
		else if(this.getDate() == null)
		newString = " " + this.getTitle()
		+ "\n" + this.getDescription();
		
			String[] arr = new String[this.getLinks().size()];
			String r = "";
			for(int i = 0; i < this.getLinks().size(); i++) {
				r = this.getLinks().get(i).toExternalForm();
				arr[i] = r;
			}
			String convert = String.join("\n", arr);
			String returnThis = newString + "\n" + convert;
			return returnThis;
		
	}
	
	//overrides compareTo(DataSet o) method
	@Override
	public int compareTo(DataSet o) {
		if(o.getDate() != null && this.getDate() != null) { //if dates are not null
			if(o.getTitle() == this.getTitle() && o.getDate() == this.getDate()) {
				return 0;  //and both titles and dates are the same, return 0
			}
			if(o.getTitle() == this.getTitle() && o.getDate() != this.getDate()) {
				return 1; //titles are the same but dates are not the same, return 1
			}
			if(o.getTitle() != this.getTitle() && o.getDate() == this.getDate()) {
				return 1; //dates are the same but titles are not the same, return 1
			}
		}
		return 0; //for anything else, return 0
	}
	
}
