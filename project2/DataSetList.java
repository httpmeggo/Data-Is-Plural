package project2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import project2.DataSet;

//this class stores DataSet objects in an ArrayList 
public class DataSetList extends ArrayList<DataSet>{

	//declare variables for this class
	private DataSet newDescription;
	private String title;
	private ArrayList<URL> links;	
	
	//default constructor
	public DataSetList() {

	}
	
	//GETTERS AND SETTERS
	public DataSet getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(DataSet newDescription) {
		this.newDescription = newDescription;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<URL> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<URL> links) {
		this.links = links;
	}
	
	
	
	//METHODS
	
	//getByTitle method takes a keyword and if the keyword is null or blank
	//an error is thrown stating that the keyword is invalid
	public DataSetList getByTitle(String keyword) throws IllegalArgumentException {
		if(!(keyword != null) || keyword.isBlank()) {
			throw new IllegalArgumentException("An invalid keyword has been entered.");	
		}
		
		//create empty object to store data
		DataSetList emptyObj = new DataSetList();
		
		//use a for-each loop to iterate through DataSet 
		//store all items with keyword in emptyObj
		for(DataSet item: this) {
			if(item.getTitle().toLowerCase().contains(keyword.toLowerCase())){
				emptyObj.add(item);
			}
		}
		//if the size of the list that was stored is 0 return null
		//this indicates that there were no titles with that keyword
		if(emptyObj.size() == 0)
			return null;
		//sort titles using Collections.sort()
		Collections.sort(emptyObj);
		
		//return DataSetList emptyObj 
		return emptyObj;
		
	}	
	
	//getByDescription method takes a keyword and if the keyword is null or blank
	//an error is thrown stating that the keyword is invalid
	public DataSetList getByDescription(String keyword) throws IllegalArgumentException{
		if(!(keyword != null) || keyword.isBlank()) {
			throw new IllegalArgumentException("An invalid keyword has been entered.");	
		}
		//create empty object to store data
		DataSetList emptyObj = new DataSetList();
		
		//use a for-each loop to iterate through DataSet 
		//store all items with keyword in emptyObj 
		for(DataSet item: this) {
			if(item.getDescription().toLowerCase().contains(keyword.toLowerCase())){
				emptyObj.add(item);
			}
		}
		//if the size of the list that was stored is 0 return null
		//this indicates that there were no descriptions with that keyword
		if(emptyObj.size() == 0)
			return null;
		//sort descriptions using Collections.sort()
		Collections.sort(emptyObj);
		
		//return DataSetList emptyObj
		return emptyObj;
		
	}
	
	//getByURL method takes a keyword and if the keyword is null or blank
	//an error is thrown stating that the keyword is invalid
	public DataSetList getByURL(String keyword) throws IllegalArgumentException {
		if(!(keyword != null) || keyword.isBlank()) {
			throw new IllegalArgumentException("An invalid keyword has been entered.");
		}
		//create empty object to store data
		DataSetList emptyObj = new DataSetList();
		
		//use a for-each loop to iterate through DataSet
		//store all items with keyword in emptyObj
		for(DataSet item: this) {
			if(item.getLinks().toString().toLowerCase().contains(keyword.toLowerCase())) {
				emptyObj.add(item);
			}
		}
		//if the size of the list that was stored is 0 return null
		//this indicates that there were no URLs with that keyword
		if(emptyObj.size() == 0)
			return null;
		//sort URLs using Collections.sort()
		Collections.sort(emptyObj);
		
		//return DataSetList emptyObj
		return emptyObj;
		
	}
		
}
