package swe2013.user;

import swe2013.location.City;

public class TourismAssociation extends User{

	City assignedCity=null;
	
	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephoneNumber
	 * @param zipCode
	 * @param street
	 * @param city
	 * @param country
	 * @param sex
	 * @param password
	 */
	public TourismAssociation(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
		
		setAssignedCity(new City(city,country));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the assigned city
	 */
	public final City getAssignedCity() {
		return assignedCity;
	}

	/**
	 * @param assignedCity the assigned city to set
	 */
	public final void setAssignedCity(City assignedCity) {
		this.assignedCity = assignedCity;
	}	
	
	public Integer getUserClass(){
		return 3;	
	}
	
}
