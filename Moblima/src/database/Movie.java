package database;

import java.util.ArrayList;
import system.*;
import enums.MovieType;
import enums.AgeRating;
import enums.MovieStatus;
import system.Showtime;

public class Movie {
  private int index;
	private String title;
	private String synopsis;
  private String showingStatus;
    private String director;
    private ArrayList<String> cast = new ArrayList<String>();
    private MovieType type;
    private AgeRating ageRating;
    private MovieStatus status;
    // private int noOfReviews;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
    private int totalSales=0;
    private double averageRating;
    private String genre;
    private String runTime;

	public Movie(int index, String title, String showingStatus, String synopsis, String director, ArrayList<String> cast, double averageRating, AgeRating ageRating,ArrayList<Review> reviews, String genre, String runTime)
	{
		this.index = index; this.title = title; this.showingStatus = showingStatus; this.synopsis = synopsis; this.director = director; this.cast = cast; this.averageRating = averageRating;
    this.ageRating = ageRating; this.status = MovieStatus.COMING_SOON; this.genre = genre; this.runTime=runTime;
	}
	
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
		this.title = title;
	}

    public String getSynopsis() {
		return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

    public String getDirector() {
		return this.director;
    }

    public void setDirector(String director) {
		this.director = director;
	}

    public ArrayList<String> getCast() {
		return this.cast;
    }

    public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

    public MovieType getType(){
        return this.type;
    }

    public void setType(MovieType type){
        this.type = type;
    }

    public AgeRating getAgeRating() {
		return this.ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
		this.ageRating = ageRating;
	}
    
    public MovieStatus getMovieStatus() {
    	return this.status;
    }
    
    public void setMovieStatus(MovieStatus status) {
    	this.status = status;
    }
/* 
  public int getNoOfReviews() {
		return this.noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}
*/
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}
  
  public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

  public boolean addReview(Review review) {
		/* if (this.reviews == null) {
       IF CODE DOESNT WORK TRY UNCOMMENTING THIS
			ArrayList<Review> r = new ArrayList<Review>();
			this.setReviews(r);
      
		} */

    /*   FOR DUPLICATE REVIEWS, MAYBE PUT IN THE DRIVER CODE
    for (Review r : reviews) { // duplicate review
      if (r.getReviewer() == review.getReviewer()) return false;
    }
    */
		reviews.add(review);
    double r = (averageRating * reviews.size() + review.getRating()) / (reviews.size()+1);
		setAverageRating(r);
    //noOfReviews++;
    return true;
	}
    
    // showtime, sales, agerating
    // idk if needed referencing
    public ArrayList<Showtime> getShowtimes() {
		return this.showtimes;
    }

    public void setShowtimes(ArrayList<Showtime> showtime) {
		this.showtimes = showtime;
	}

  public void addShowtime(Showtime showtime) {
    /* 
    if(slots ==null)
      {
          slots = new ArrayList<Slot>();
      }
    */
      this.showtimes.add(showtime);
  }

  public void removeShowtime(Showtime showtime) {
    for(int i = 0; i < this.showtimes.size(); i++)
        if (this.showtimes.get(i) == showtime) {
            this.showtimes.remove(i);
            return;
        }
}

public double getAverageRating() {
  return this.averageRating;
  }

  public void setAverageRating(double averageRating) {
  this.averageRating = averageRating;
} 

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder casts = new StringBuilder();
        double rating = getAverageRating();
        stringBuilder.append("Title:      ").append(getTitle()).append("\n");
        stringBuilder.append("Age Rating: ").append(getAgeRating().name()).append("\n");
        stringBuilder.append("Director:   ").append(getDirector()).append("\n");
        stringBuilder.append("Synopsis:   ").append("\"").append(synopsis).append("\"").append("\n");

        stringBuilder.append("Cast:       ");
        for (String s : getCast()) casts.append(s).append(", ");
        stringBuilder.append("\n");
        stringBuilder.append("Rating:     ");
        if (rating == 0.0) stringBuilder.append("No rating").append("\n");
        else stringBuilder.append(getAverageRating()).append("/5.0").append("\n");
        // stringBuilder.append("Status:   ").append(movieStatus.toString()).append("\n");

        return stringBuilder.toString();
    }



}