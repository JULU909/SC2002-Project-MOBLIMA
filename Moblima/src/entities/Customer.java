package entities;
import java.util.*;

import enums.AgeGroup;
import enums.UserType;

/**
 * Customer class is a child Class that extends from the Parent User Class
 * @author Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */

public class Customer extends User{


    private int age;
    private AgeGroup ageGroup;
    private String mobileNumber;
    private String emailAddress;
    ArrayList<Movie> movieHistory;
    /**
     * Creates a Customer with a given username, password, age, mobile number and email address
     * @param username Username of customer
     * @param password Password of customer
     * @param age The age of the customer
     * @param mobileNumber The mobile number of the customer
     * @param emailAddress Email address of this customer
     */
    
    public Customer(String username, String password, int age, String mobileNumber, String emailAddress){
        super(username, password); super.setUserType(UserType.CUSTOMER); setAge(age); this.mobileNumber = mobileNumber; this.emailAddress = emailAddress;
    }
/**
 * Gets the age of a customer
 * @return integer age of customer
 */
    public int getAge(){
        return this.age;
    }
/**
 * Sets the age of a customer, as well as their age group
 * @param age, the numerical age of the customer
 */
    public void setAge(int age){
        this.age = age;
        if (age < 18){
            setAgeGroup(AgeGroup.CHILD);
        }
        else if (age < 55){
            setAgeGroup(AgeGroup.ADULT);
        }
        else {
            setAgeGroup(AgeGroup.SENIOR);
        }
    }
	/** 
	 * Gets the age group of a customer
	 * @return the age group of the customer
	 */
    public AgeGroup getAgeGroup(){
        return this.ageGroup;        
    }
    /**
     * Sets the age group of a customer
     * @param ageGroup , the age group of a customer
     */
    public void setAgeGroup(AgeGroup ageGroup){
        this.ageGroup = ageGroup;
        
    }
    /**
     * Gets the mobile number of a customer
     * @return The mobile number of a customer
     */
    public String getmobileNumber(){
        return this.mobileNumber;
    }
    /**
     * Sets the mobile number of a customer
     * @param mobileNumber, the mobile number of a customer
     */
    public void setmobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    /**
     * Gets the email address of a customer
     * @return The email address of a customer
     */
    public String getEmailAddress(){
        return this.emailAddress;
    }
    /**
     * Sets the email address of a customer
     * @param emailAdress, the email address of a customer
     */
    public void setEmailAddress(String emailAdress){
        this.emailAddress = emailAdress;
    }
	/**
	 * Gets the array list of movies that a customer has watched
	 * @return An array list of movies
	 */
    public ArrayList<Movie> getMovieHistory(){
       return this.movieHistory;
    }
    /**
     * Adds a movie to the array list of movies that a customer has watched
     * @param movie , the movie that a customer has watched
     */
    public void addMovieHistory(Movie movie){
        movieHistory.add(movie);
    }
}
