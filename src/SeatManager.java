
import java.util.*;

public class SeatManager {

    private Map<String, LinkedHashMap<String, Seat>> showSeats;

    public SeatManager() {
        showSeats = new HashMap<>();
    }

    // Initialize 15 seats for each show
    private void initializeSeats(String showKey) {

        if (!showSeats.containsKey(showKey)) {

            LinkedHashMap<String, Seat> seats = new LinkedHashMap<>();

            for (int row = 1; row <= 3; row++) {
                for (int col = 1; col <= 5; col++) {

                    String seatId = "R" + row + "S" + col;
                    seats.put(seatId, new Seat(seatId));
                }
            }

            showSeats.put(showKey, seats);
        }
    }

    // Display seat layout and seat summary
    public void displaySeats(String showKey) {

        initializeSeats(showKey);

        System.out.println("\n----- SEAT LAYOUT -----");
        System.out.println("[XX] = Booked Seat");

        int bookedCount = 0;
        int availableCount = 0;

        for (Seat seat : showSeats.get(showKey).values()) {

            seat.displaySeat();

            if (seat.isBooked()) {
                bookedCount++;
            } else {
                availableCount++;
            }

            if (seat.getSeatId().endsWith("S5")) {
                System.out.println();
            }
        }

        System.out.println("\n----- SEAT SUMMARY -----");
        System.out.println("Total Seats: "
                + (bookedCount + availableCount));
        System.out.println("Available Seats: " + availableCount);
        System.out.println("Booked Seats: " + bookedCount);
    }

    // Validate selected seats
    public boolean areAvailable(String showKey, List<String> selectedSeats) {

        initializeSeats(showKey);

        LinkedHashMap<String, Seat> seats = showSeats.get(showKey);

        if (selectedSeats == null || selectedSeats.isEmpty()) {
            System.out.println("Please select at least one seat.");
            return false;
        }

        Set<String> uniqueSeats = new HashSet<>();

        for (String id : selectedSeats) {

            if (id == null || id.trim().isEmpty()) {
                System.out.println("Invalid empty seat ID.");
                return false;
            }

            String seatId = id.trim().toUpperCase();

            // Check duplicate seats
            if (!uniqueSeats.add(seatId)) {
                System.out.println("Duplicate seat selected: " + seatId);
                return false;
            }

            // Check whether seat exists
            if (!seats.containsKey(seatId)) {
                System.out.println("Invalid seat ID: " + seatId);
                return false;
            }

            // Check whether seat is already booked
            if (seats.get(seatId).isBooked()) {
                System.out.println("Seat already booked: " + seatId);
                return false;
            }
        }

        return true;
    }

    // Reserve selected seats
    public void reserveSeats(String showKey, List<String> selectedSeats) {

        if (!areAvailable(showKey, selectedSeats)) {
            System.out.println("Seat reservation failed.");
            return;
        }

        for (String id : selectedSeats) {

            String seatId = id.trim().toUpperCase();

            showSeats.get(showKey).get(seatId).bookSeat();
        }

        System.out.println("All selected seats reserved successfully!");
    }

    // Release seats after booking cancellation
    public void releaseSeats(String showKey, List<String> selectedSeats) {

        initializeSeats(showKey);

        for (String id : selectedSeats) {

            if (id == null) {
                continue;
            }

            String seatId = id.trim().toUpperCase();

            Seat seat = showSeats.get(showKey).get(seatId);

            if (seat != null) {
                seat.releaseSeat();
            } else {
                System.out.println("Invalid seat ID: " + seatId);
            }
        }
    }
}
