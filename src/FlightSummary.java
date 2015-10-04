/**
 * Flight summary.
 *
 * <p>Summary info contains:</p>
 * <ul>
 * <li>Flight number</li>
 * <li>Seats available</li>
 * <li>Total sold seats</li>
 * <li>TotalRevenue</li>
 * <li>Summary, in string format</li>
 * </ul>
 */
public class FlightSummary {
    /**
     * FlightNumber
     */
    private final String flightNumber;

    /**
     * Seats available.
     */
    private final int availableSeats;

    /**
     * Sold seats.
     */
    private final int soldSeats;

    /**
     * Total avenue.
     */
    private final long totalAvenue;

    /**
     * Flight summary.
     */
    private final String summary;

    /**
     * Constructor.
     * @param flightNumber flight number.
     * @param availableSeats Seats available.
     * @param soldSeats Sold seats.
     * @param totalAvenue Total avenue.
     * @param summary Flight summary.
     */
    public FlightSummary(final String flightNumber, final int availableSeats, final int soldSeats,
            final long totalAvenue, final String summary) {
        this.flightNumber = flightNumber;
        this.availableSeats = availableSeats;
        this.soldSeats = soldSeats;
        this.totalAvenue = totalAvenue;
        this.summary = summary;
    }

    /**
     * Get flight number.
     * @return flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Get available seats.
     * @return available seats.
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Get sold seats.
     * @return sold seats.
     */
    public int getSoldSeats() {
        return soldSeats;
    }

    /**
     * Get total avenue.
     * @return total avenue.
     */
    public long getTotalAvenue() {
        return totalAvenue;
    }

    /**
     * Get summary.
     * @return summary String.
     */
    public String getSummary() {
        return summary;
    }
}
