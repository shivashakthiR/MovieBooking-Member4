
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingId;
    private Customer customer;
    private Movie movie;
    private String showtime;
    private List<String> selectedSeats;
    private double totalAmount;
    private boolean cancelled;

    public Booking(int bookingId, Customer customer, Movie movie,
                   String showtime, List<String> selectedSeats,
                   double totalAmount) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.movie = movie;
        this.showtime = showtime;
        this.selectedSeats = new ArrayList<>(selectedSeats);
        this.totalAmount = totalAmount;
        this.cancelled = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowtime() {
        return showtime;
    }

    public List<String> getSelectedSeats() {
        return new ArrayList<>(selectedSeats);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancelBooking() {
        cancelled = true;
    }

    public void displayBooking() {
        System.out.println("\n----- BOOKING DETAILS -----");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Showtime: " + showtime);
        System.out.println("Seats: " + selectedSeats);
        System.out.println("Total Amount: Rs. " + totalAmount);
        System.out.println("Status: " + (cancelled ? "Cancelled" : "Confirmed"));
    }
}
