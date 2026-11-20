
import java.util.*;

public class SeatManager {
    private Map<String, LinkedHashMap<String, Seat>> showSeats;

    public SeatManager() {
        showSeats = new HashMap<>();
    }

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

    public void displaySeats(String showKey) {
        initializeSeats(showKey);

        System.out.println("\n----- SEAT LAYOUT -----");

        for (Seat seat : showSeats.get(showKey).values()) {
            seat.displaySeat();
            if (seat.getSeatId().endsWith("S5")) {
                System.out.println();
            }
        }
    }

    public boolean areAvailable(String showKey, List<String> selectedSeats) {
        initializeSeats(showKey);

        LinkedHashMap<String, Seat> seats = showSeats.get(showKey);
        Set<String> uniqueSeats = new HashSet<>();

        for (String id : selectedSeats) {
            String seatId = id.toUpperCase();

            if (!uniqueSeats.add(seatId)
                    || !seats.containsKey(seatId)
                    || seats.get(seatId).isBooked()) {
                return false;
            }
        }

        return !selectedSeats.isEmpty();
    }

    public void reserveSeats(String showKey, List<String> selectedSeats) {
        if (!areAvailable(showKey, selectedSeats)) {
            System.out.println("Some selected seats are unavailable.");
            return;
        }

        for (String id : selectedSeats) {
            showSeats.get(showKey).get(id.toUpperCase()).bookSeat();
        }
    }

    public void releaseSeats(String showKey, List<String> selectedSeats) {
        initializeSeats(showKey);

        for (String id : selectedSeats) {
            Seat seat = showSeats.get(showKey).get(id.toUpperCase());

            if (seat != null) {
                seat.releaseSeat();
            }
        }
    }
}
