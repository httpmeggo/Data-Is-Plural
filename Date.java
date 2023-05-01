package project2;

//this class represents a calendar date and stores the year, month, and day of the date
public class Date implements Comparable<Date> {

	//declare variables for this class
	int year;
	int month;
	int day;
	String dateString;

	// validates date and sets date
	public Date(int year, int month, int day) throws IllegalArgumentException {
		
		// check for valid year
		boolean goodDate = false;
		if (year < 1) {
			goodDate = false;
			throw new IllegalArgumentException("Invalid year. Year should be" + "an integer greater than 0.");
		}

		// check for valid month
		else if (month > 12 || month < 1) {
			goodDate = false;
			throw new IllegalArgumentException("Invalid month. Month" + " should be in the range 1-12.");
		}

		// check for valid day (1-31)
		else if ((!(month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) && (day > 31 || day < 1)) {
			goodDate = false;
			throw new IllegalArgumentException("Invalid day. Day should range from 1-31" + "for this month.");
		}

		// check for valid day (1-30)
		else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30 || day < 1)) {
			goodDate = false;
			throw new IllegalArgumentException("Invalid day. Day should range from 1-30" + "for this month.");
		}

		// check for not leap year and day for February
		// if year % 4 != 0 then common year
		// if year % 100 = 0 but year % 400 != 0 then common year
		else if ((((year % 4 != 0) && month == 2) || (year % 100 == 0 && year % 400 != 0 && month == 2))
				&& (day < 1 || day > 28)) {
			// then common year is true
			goodDate = false;
			throw new IllegalArgumentException(
					"Invalid day. Day should range from 1-28" + "for this month + year combination.");

		}
		// leap year
		else if ((year % 100 != 0 && month == 2 && (day < 1 || day > 29))
				|| (year % 4 != 0 && year % 100 != 0 && year % 400 != 0) && month == 2 && (day < 1 || day > 29)) {
			goodDate = false;
			throw new IllegalArgumentException(
					"Invalid day. Day should range from 1-29" + "for this month + year combination.");
		}
		else { //if all of the previous if/else-if statements are not true then the date is valid
			goodDate = true;
			
		}

		if (goodDate == true) {
			this.year = year;
			this.month = month;
			this.day = day;
			setDateString(dateString);
		}
	}

	//GETTERS AND SETTERS
	public String getDateString() {
		return this.dateString;
	}

	public void setDateString(String newDateString) {
		this.dateString = newDateString;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	//override the equals method from the object class
	//and throw IllegalArgumentException when necessary
	@Override
	public boolean equals(Object o) throws IllegalArgumentException {

		if (this.getYear() == ((Date) o).getYear() && this.getMonth() == ((Date) o).getMonth()
				&& this.getDay() == ((Date) o).getDay()) { //if the dates are equal then return true
			return true;
		} 
		else //if they're not equal, return false
			return false;
	}

	@Override
	public String toString() { //this two string puts the dates in valid format
		if (String.valueOf(this.getDay()).length() == 1 && String.valueOf(this.getMonth()).length() == 2) {
			dateString = String.valueOf(this.getYear()) + "-" + String.valueOf(this.getMonth()) + "-0"
					+ String.valueOf(this.getDay()); //add a 0 before the day if the day only has one digit
		} else if (String.valueOf(this.getMonth()).length() == 1 && String.valueOf(this.getDay()).length() == 2) {
			dateString = String.valueOf(this.getYear()) + "-0" + String.valueOf(this.getMonth()) + "-"
					+ String.valueOf(this.getDay()); //add a 0 before the month if the month only has one digit
		} else if (String.valueOf(this.getMonth()).length() == 1 && String.valueOf(this.getDay()).length() == 1) {
			dateString = String.valueOf(this.getYear()) + "-0" + String.valueOf(this.getMonth()) + "-0"
					+ String.valueOf(this.getDay()); //add a 0 before the day and month if they both only have one digit
		} else {
			dateString = String.valueOf(this.getYear()) + '-' + String.valueOf(this.getMonth()) + '-'
					+ String.valueOf(this.getDay()); //dont add a 0 if all of the values have the right amount of digits
		}
		return dateString;

	}

	@Override
	public int compareTo(Date o) throws IllegalArgumentException { //compare both dates
		//if dates are the same then return 0, if not then return 1
		if(o.getYear() == this.getYear() && o.getMonth() == this.getMonth() && o.getDay() == this.getDay()) {
			return 0;
		}
		else
			return 1;
		
	}

}
