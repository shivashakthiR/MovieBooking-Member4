
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private List<Booking> bookings;
    private SeatManager seatManager;
    private int nextBookingId;

    public BookingManager(SeatManager seatManager) {
        this.bookings = new ArrayList<>();
        this.seatManager = seatManager;
        this.nextBookingId = 1001;
    }

    // Calculate total ticket price
    public double calculateBill(Movie movie, List<String> seats) {
        return movie.getTicketPrice() * seats.size();
    }

    // Create a new booking
    public Booking createBooking(Customer customer, Movie movie,
                                 String showtime, List<String> seats) {

        String showKey = movie.getMovieId() + "_" + showtime;

        if (!seatManager.areAvailable(showKey, seats)) {
            System.out.println("Booking failed! Selected seats are unavailable.");
            return null;
        }

        double totalAmount = calculateBill(movie, seats);

        seatManager.reserveSeats(showKey, seats);

        Booking booking = new Booking(
                nextBookingId++,
                customer,
                movie,
                showtime,
                seats,
                totalAmount
        );

        bookings.add(booking);

        System.out.println("\nBooking successful!");
        System.out.println("Your Booking ID: " + booking.getBookingId());
        System.out.println("Total Amount: Rs. " + totalAmount);

        return booking;
    }

    // View complete booking history
    public void viewBookings() {

        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings found.");
            return;
        }

        System.out.println("\n===== BOOKING HISTORY =====");

        for (Booking booking : bookings) {
            booking.displayBooking();
        }
    }

    // Search booking using Booking ID
    public void searchBooking(int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {
                System.out.println("\n===== BOOKING FOUND =====");
                booking.displayBooking();
                return;
            }
        }

        System.out.println("No booking found with ID: " + bookingId);
    }

    // Cancel booking and release seats
    public void cancelBooking(int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId
                    && !booking.isCancelled()) {

                String showKey = booking.getMovie().getMovieId()
                        + "_" + booking.getShowtime();

                seatManager.releaseSeats(
                        showKey,
                        booking.getSelectedSeats()
                );

                booking.cancelBooking();

                System.out.println("\nBooking cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking ID not found or already cancelled.");
    }
}
