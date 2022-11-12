package entities;

/*
 * This file is useful to initialse the attributes of MovieInfo, and contains a getAll() function to return the details of a movie
 * 
 */

public class MovieInfo {
    private int index;
    private String Movie_Name;
    private String Showing_Status;
    private String Synopsis;
    private String Director;
    private String Cast;
    private String Overall_Review;
    private String Movie_Restriction;
    private String Genre;
    private String Run_Time;

    public MovieInfo(int index1,String Movie_Name1,String Showing_Status1,String Synopsis1,String Director1,String Cast1,String Overall_Review1,String Movie_Restriction1,String Genre1,String Run_Time1){
      index = index1;
      Movie_Name = Movie_Name1;
      Showing_Status = Showing_Status1;
      Synopsis = Synopsis1;
      Director = Director1;
      Cast = Cast1;
      Overall_Review = Overall_Review1;
      Genre = Genre1;
      Run_Time = Run_Time1;
    }

    public String getAll(){
        return String.format("INDEX: %s\nMovie_Name: %s\nShowing_Status: %s\nSynopsis: %s\nDirector : %s\nCAST: %s\nOverall_Review: %s\nMovie_Restriction: %s\nGENRE: %s\nRun_Time: %s\n", 
        index,Movie_Name,Showing_Status,Synopsis,Director,Cast,Overall_Review,Movie_Restriction,Genre,Run_Time);
    }

    public String getTitle(){
        return String.format("%s", Movie_Name);
    }
    
}
