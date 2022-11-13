package entities;


/**
 * This class initialises the Review attributes and contains the relevant getter and setter functions
 * @author
 * @version 1.0
 * @since 2022-11-13
 */

public class Review {
    String reviewer;
    int rating;
    String prose;

    /**
     Constructor of Review
     */

    public Review(String reviewer, int rating, String prose){
        this.reviewer = reviewer; this.rating = rating; this.prose = prose;
    }

    /**
     Relevant getter and setter functions for Reviewer, Rating, Prose
    */

    public String getReviewer(){
        return this.reviewer;
    }

    public void setReviewer(String reviewer){
        this.reviewer = reviewer;
    }
/* 
    Movie getMovie(){
        return this.movie;
    }

    void setMovie(Movie movie){
        this.movie = movie;
    }
*/
    public int getRating(){
        return this.rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public String getProse(){
        return this.prose;
    }
    
    public void setProse(String prose){
        this.prose = prose;
    }
}
