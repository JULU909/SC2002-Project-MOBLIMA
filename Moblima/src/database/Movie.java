import java.util.ArrayList;

public class Movie {
	
	private String title;
	private String synopsis;
    private String director;
    private String[] cast;
    private MovieType type;
    private AgeRating ageRating;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ShowTime[] showTimes = new ArrayList<ShowTime>();
    private int totalSales=0;

	Movie(String title, String synopsis, String director, String[] cast, MovieType type, AgeRating ageRating)
	{
		this.title = title; this.synopsis = synopsis; this.director = director; this.cast = cast; this.type = type; this.ageRating=ageRating;
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

    public String[] getCast() {
		return this.cast;
    }

    public void setCast(String[] cast) {
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
    
    // showtime, sales, agerating
    // idk if needed referencing
    public ShowTime[] getShowtimes() {
		return this.showTimes;
    }

    public void setShowTimes(ShowTime[] showTime) {
		this.showTime = showTime;
	}

    
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}

    // FIX THIS
    public Double getRating() {
		int noOfReviews = reviews.size();
		
		if (noOfReviews <= 1)
			return null;
		
		Double sum = 0;
		for (ReviewRating reviewRating: reviewRatings)
			sum += reviewRating.getRating();
		
		double overallRating = sum / noOfReviews;
		return overallRating;
	}

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder casts = new StringBuilder();
        double rating = getRating();
        stringBuilder.append("Title:      ").append(getTitle()).append("\n");
        stringBuilder.append("Age Rating: ").append(getAgeRating().name()).append("\n");
        stringBuilder.append("Director:   ").append(getDirector()).append("\n");
        stringBuilder.append("Synopsis:   ").append("\"").append(synopsis).append("\"").append("\n");

        stringBuilder.append("Cast:       ");
        for (String s : getCast()) casts.append(s).append(", ");
        stringBuilder.append("\n");
        stringBuilder.append("Rating:     ");
        if (rating == 0.0) stringBuilder.append("No rating").append("\n");
        else stringBuilder.append(getRating(this)).append("/5.0").append("\n");
        stringBuilder.append("Status:   ").append(movieStatus.toString()).append("\n");

        return stringBuilder.toString();
    }



}