package swe2013.location;


public abstract class Location {
	//private ArrayList<Review> reviews;

	public Location(){}

	public final static void checkInput(String input) throws IllegalArgumentException{
		if (!input.equals(input.replaceAll(";", "")))
		throw new IllegalArgumentException("Input contained illegal characters - possible SQL Insertion?");
	}
	
	public abstract String getName();
}
