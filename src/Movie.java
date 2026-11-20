
public class Movie {

    private int movieId;
    private String movieName;
    private String genre;
    private int duration;
    private double ticketPrice;

    public Movie(int movieId, String movieName,
                 String genre, int duration,
                 double ticketPrice) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void displayMovie() {

        System.out.println("Movie ID: " + movieId);
        System.out.println("Movie Name: " + movieName);
        System.out.println("Genre: " + genre);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Ticket Price: Rs. " + ticketPrice);
    }
}
