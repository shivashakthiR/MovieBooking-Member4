
public class Seat {

    private String seatId;
    private boolean booked;

    // Constructor
    public Seat(String seatId) {
        this.seatId = seatId;
        this.booked = false;
    }

    // Get seat ID
    public String getSeatId() {
        return seatId;
    }

    // Check seat availability
    public boolean isBooked() {
        return booked;
    }

    // Book a seat
    public void bookSeat() {
        if (!booked) {
            booked = true;
            System.out.println("Seat " + seatId + " booked successfully!");
        } else {
            System.out.println("Seat is already booked!");
        }
    }

    // Release a seat
    public void releaseSeat() {
        if (booked) {
            booked = false;
            System.out.println("Seat " + seatId + " released successfully!");
        } else {
            System.out.println("Seat is already available!");
        }
    }

    // Display seat status
    public void displaySeat() {
        if (booked) {
            System.out.print("[XX] ");
        } else {
            System.out.print("[" + seatId + "] ");
        }
    }
}
