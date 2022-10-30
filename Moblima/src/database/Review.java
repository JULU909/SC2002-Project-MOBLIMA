public class Review {
    Customer reviewer;
    Movie movie;
    int rating;
    String prose;

    Customer getReviewer(){
        return this.reviewer;
    }

    void setReviewer(Customer reviewer){
        this.reviewer = reviewer;
    }

    Movie getMovie(){
        return this.movie;
    }

    void setMovie(Movie movie){
        this.movie = movie;
    }

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
