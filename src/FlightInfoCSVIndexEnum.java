/**
 * Enum indicates the flight information index in inputfile1.txt.
 *
 * Based on the current inputfile1, the corresponding index is as follows:
 * <ul>
 * <li>Flight number: index 0</li>
 * <li>Number of seats in flight: index 1</li>
 * <li>Price per seat: index 2</li>
 * <li>Origin code: index 3</li>
 * <li>Destination code: index 4</li>
 * </ul>
 */
public enum FlightInfoCSVIndexEnum {
    FLIGHT_NUMBER(0),
    NUMBER_OF_SEATS(1),
    PRICE_PER_SEAT(2),
    ORIGIN(3),
    DESTINATION(4);

    /**
     * Index in CSV column.
     */
    private final int index;

    /**
     * Constructor.
     * @param index Index in CSV column.
     */
    private FlightInfoCSVIndexEnum(final int index) {
        this.index = index;
    }

    /**
     * Get Index.
     * @return index.
     */
    public int getIndex() {
        return index;
    }
}
