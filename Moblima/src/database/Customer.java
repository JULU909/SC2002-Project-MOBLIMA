package database;
import java.util.*;
import enums.AgeRating;
import enums.AgeGroup;
import enums.UserType;

public class Customer extends User{
    private int age;
    private AgeGroup ageGroup;
    private int mobileNumber;
    private String emailAddress;
    ArrayList<Movie> movieHistory;
    private int cardHolder;
    

   

    public Customer(String firstName, String lastName, int age, int mobileNumber, String emailAddress, Movie[] movieHistory, int cardHolder){
        super(firstName, lastName); super.setUserType(UserType.CUSTOMER); setAge(age);; this.mobileNumber = mobileNumber; this.emailAddress = emailAddress; this.cardHolder = cardHolder;
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

    public int getCardHolder(){
        return this.cardHolder;
    } 

    public void setCardHolder(int cardHolder){
        this.cardHolder = cardHolder;
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
