
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Movie> movies = new ArrayList<>();
    private static final SeatManager seatManager = new SeatManager();
    private static final BookingManager bookingManager =
            new BookingManager(seatManager);

    private static int nextCustomerId = 1;

    public static void main(String[] args) {

        addMovies();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("   MOVIE TICKET BOOKING SYSTEM");
            System.out.println("==================================");
            System.out.println("1. View Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. View Booking History");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Search Booking by ID");
            System.out.println("6. View Booking Summary");
            System.out.println("7. View Available Seats");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    viewMovies();
                    break;

                case 2:
                    bookTickets();
                    break;

                case 3:
                    bookingManager.viewBookings();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    searchBooking();
                    break;

                case 6:
                    bookingManager.displayBookingSummary();
                    break;

                case 7:
                    viewAvailableSeats();
                    break;

                case 8:
                    System.out.println("Thank you for using our system!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 8);
    }

    // Add sample movies
    private static void addMovies() {

        movies.add(new Movie(101, "Avengers", "Action", 180, 200));
        movies.add(new Movie(102, "Inception", "Sci-Fi", 148, 180));
        movies.add(new Movie(103, "Interstellar", "Drama", 169, 220));
    }

    // Display available movies
    private static void viewMovies() {

        System.out.println("\n----- AVAILABLE MOVIES -----");

        for (int i = 0; i < movies.size(); i++) {
            System.out.println("\nMovie " + (i + 1));
            movies.get(i).displayMovie();
        }
    }

    // Book movie tickets
    private static void bookTickets() {

        viewMovies();

        System.out.print("\nSelect movie number: ");
        int movieChoice = readInt();

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        Movie movie = movies.get(movieChoice - 1);

        System.out.println("\n----- AVAILABLE SHOWTIMES -----");
        System.out.println("1. 10:00 AM");
        System.out.println("2. 2:00 PM");
        System.out.println("3. 6:00 PM");

        System.out.print("Select showtime: ");
        int timeChoice = readInt();

        String showtime;

        switch (timeChoice) {

            case 1:
                showtime = "10:00 AM";
                break;

            case 2:
                showtime = "2:00 PM";
                break;

            case 3:
                showtime = "6:00 PM";
                break;

            default:
                System.out.println("Invalid showtime.");
                return;
        }

        String showKey = movie.getMovieId() + "_" + showtime;

        seatManager.displaySeats(showKey);

        System.out.println("\nEnter seat IDs separated by commas.");
        System.out.println("Example: R1S1,R1S2");
        System.out.print("Your selected seats: ");

        String seatInput = sc.nextLine();

        List<String> selectedSeats = new ArrayList<>();

        for (String seat : seatInput.split(",")) {
            if (!seat.trim().isEmpty()) {
                selectedSeats.add(seat.trim().toUpperCase());
            }
        }

        if (selectedSeats.isEmpty()) {
            System.out.println("Please select at least one seat.");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        System.out.print("Enter 10-digit phone number: ");
        String phone = sc.nextLine().trim();

        if (!phone.matches("[0-9]{10}")) {
            System.out.println("Invalid phone number.");
            return;
        }

        Customer customer = new Customer(
                nextCustomerId++,
                name,
                phone
        );

        bookingManager.createBooking(
                customer,
                movie,
                showtime,
                selectedSeats
        );
    }

    // View available seats without booking
    private static void viewAvailableSeats() {

        viewMovies();

        System.out.print("\nSelect movie number: ");
        int movieChoice = readInt();

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        Movie movie = movies.get(movieChoice - 1);

        System.out.println("\n----- AVAILABLE SHOWTIMES -----");
        System.out.println("1. 10:00 AM");
        System.out.println("2. 2:00 PM");
        System.out.println("3. 6:00 PM");

        System.out.print("Select showtime: ");
        int timeChoice = readInt();

        String showtime;

        switch (timeChoice) {

            case 1:
                showtime = "10:00 AM";
                break;

            case 2:
                showtime = "2:00 PM";
                break;

            case 3:
                showtime = "6:00 PM";
                break;

            default:
                System.out.println("Invalid showtime.");
                return;
        }

        String showKey = movie.getMovieId() + "_" + showtime;

        System.out.println("\nAvailable seats for "
                + movie.getMovieName() + " at " + showtime);

        seatManager.displaySeats(showKey);
    }

    // Cancel booking
    private static void cancelBooking() {

        System.out.print("\nEnter Booking ID to cancel: ");

        int bookingId = readInt();

        if (bookingId <= 0) {
            System.out.println("Invalid booking ID.");
            return;
        }

        bookingManager.cancelBooking(bookingId);
    }

    // Search booking by ID
    private static void searchBooking() {

        System.out.print("\nEnter Booking ID to search: ");

        int bookingId = readInt();

        if (bookingId <= 0) {
            System.out.println("Invalid booking ID.");
            return;
        }

        bookingManager.searchBooking(bookingId);
    }

    // Read integer input safely
    private static int readInt() {

        try {
            return Integer.parseInt(sc.nextLine().trim());

        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
