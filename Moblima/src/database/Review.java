package database;

public class Review {
    Customer reviewer;
    int rating;
    String prose;

    public Review(Customer reviewer, int rating, String prose){
        this.reviewer = reviewer; this.rating = rating; this.prose = prose;
    }
    Customer getReviewer(){
        return this.reviewer;
    }

    void setReviewer(Customer reviewer){
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
    int getRating(){
        return this.rating;
    }

    void setRating(int rating){
        this.rating = rating;
    }

    String getProse(){
        return this.prose;
    }
    
    void setProse(String prose){
        this.prose = prose;
    }
}
