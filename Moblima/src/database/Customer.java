package database;
import java.util.*;
import enums.AgeGroup;
import enums.UserType;

public class Customer extends User{
    /*
     * Creates a Customer with a given name and username, age, mobile number, email address and history of movies watched
     * @param age Age of this customer
     * @param ageGroup Age group of this customer
     * @param mobileNumber Mobile number of this customer
     * @param emailAddress Email address of this customer
     * @param movieHistory List of movies previously watched by this customer
     */
    
    private int age;
    private AgeGroup ageGroup;
    private int mobileNumber;
    private String emailAddress;
    ArrayList<Movie> movieHistory;

    public Customer(String firstName, String lastName, int age, int mobileNumber, String emailAddress){
        super(firstName, lastName); super.setUserType(UserType.CUSTOMER); setAge(age);; this.mobileNumber = mobileNumber; this.emailAddress = emailAddress;
    }

    public int getAge(){
        return this.age;
    }

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

    public AgeGroup getAgeGroup(){
        return this.ageGroup;        
    }

    public void setAgeGroup(AgeGroup ageGroup){
        this.ageGroup = ageGroup;
        
    }
    public int getmobileNumber(){
        return this.mobileNumber;
    }

    public void setmobileNumber(int mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAdress(){
        return this.emailAddress;
    }

    public void setEmailAdress(String emailAdress){
        this.emailAddress = emailAdress;
    }

    // implement movie history if possible
    public ArrayList<Movie> getMovieHistory(){
        // copy list so original is not modified
       return this.movieHistory;
    }

    public void addMovieHistory(Movie movie){
        movieHistory.add(movie);
    }
}
