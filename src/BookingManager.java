
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

    public double calculateBill(Movie movie, List<String> seats) {
        return movie.getTicketPrice() * seats.size();
    }

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

        return booking;
    }

    public void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking booking : bookings) {
            booking.displayBooking();
        }
    }

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

                System.out.println("Booking cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking ID not found or already cancelled.");
    }
}
