public class Customer {
    private int age;
    private AgeGroup ageGroup;
    private int mobileNumber;
    private String emailAddress;
    private Movie[5] movieHistory;
    private int cardHolder;

    public Customer(int age, AgeGroup ageGroup, int mobileNumber, String emailAddress, Movie[] movieHistory, int cardHolder){
        this.age = age; this.ageGroup = ageGroup; this.mobileNumber = mobileNumber; this.emailAddress = emailAddress; this.movieHistory = movieHistory; this.cardHolder = cardHolder;
    }
    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public AgeGroup getageGroup(){
        return this.ageGroup;
    }

    public void setageGroup(){
        int age = getAge();
        if (age < 18){
            this.ageGroup = AgeGroup.CHILD;
        }
        else if (age < 55){
            this.ageGroup = AgeGroup.ADULT;
        }
        else {
            this.ageGroup = AgeGroup.SENIOR;
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
    public Movie[] getMovieHistory(){
        return this.movieHistory; 
    }

    public void addReview(){
        //
    }

    public void buyTicket(){
        //
    }








    

}
