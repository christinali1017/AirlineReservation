/**
 * Passenger reservation item.
 *
 * <p>Each reservation item contains:</p>
 * <ul>
 * <li>Passenger information</li>
 * <li>Flight price</li>
 * <li>Seat Number</li>
 * </ul>
 */
public class ReservationItem {
    /**
     * Passenger.
     */
    private final Passenger passenger;

    /**
     * Flight price.
     */
    private final int price;

    /**
     * Seat number.
     */
    private final int seatNumber;

    /**
     * Constructor.
     */
    public ReservationItem(final Passenger passenger, final int price, final int seatNumber) {
        this.passenger = passenger;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    /**
     * Get seat number.
     * @return seat number.
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Get price.
     * @return price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get passenger.
     * @return passenger.
     */
    public Passenger getPassenger() {
        return passenger;
    }
}
