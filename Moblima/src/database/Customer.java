package database;
import java.util.*;

public class Customer extends User{
    private int age;
    private int mobileNumber;
    private String emailAddress;
    ArrayList<Movie> movieHistory;
    private int cardHolder;

   

    public Customer(String firstName, String lastName, int age, AgeGroup ageGroup, int mobileNumber, String emailAddress, Movie[] movieHistory, int cardHolder){
        super(firstName, lastName); super.setUserType(userType.CUSTOMER);this.age = age; this.ageGroup = ageGroup; this.mobileNumber = mobileNumber; this.emailAddress = emailAddress; this.cardHolder = cardHolder;
    }
    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public AgeGroup getageGroup(){
        int age = getAge();
        if (age < 18){
            return AgeGroup.CHILD;
        }
        else if (age < 55){
            return AgeGroup.ADULT;
        }
        else {
            return AgeGroup.SENIOR;
        }
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

    public void addReview(Movie movie, Review reviewList, String review, int rating){
        // if movie not in review list, create one
        for (review : reviewList){
            if (review.movie.getTitle == )
        }
    }

    public void buyTicket(){
        //
    }








    

}
