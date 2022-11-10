package database;

public class Review {
    /*Customer reviewer*/;
    String reviewer;
    int rating;
    String prose;
   /* String title;*/

    public Review(String reviewer, int rating, String prose/*, String title*/){
        this.reviewer = reviewer; this.rating = rating; this.prose = prose; /*this.title = title;*/
    }
    String getReviewer(){
        return this.reviewer;
    }

    void setReviewer(String reviewer){
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
    
   /* String getTitle() {
    	return this.title;
    }
    
    void setTitle(String title) {
    	this.title = title;
    }*/
}
