import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for flight class.
 */
public class FlightTest {
    private Flight flight;

    private static final String TEST_FLIGHT_NUMBER = "A124";

    private static final int NUMBER_OF_SEATS = 54;

    private static int pricePerSeat = 150;

    private static final String ORIGIN_CODE = "LAS";

    private static final String DESTINATION_CODE = "LAX";

    private static final Passenger TEST_PASSENGER =  new Passenger("kennethHarris");

    private static ReservationItem testReservationItem;

    @Before
    public void initialize() {
        flight = new Flight.FlightBuilder()
                           .withFlightNumber(TEST_FLIGHT_NUMBER)
                           .withNumberOfSeats(NUMBER_OF_SEATS)
                           .withPricePerSeat(pricePerSeat)
                           .withOriginCode(ORIGIN_CODE)
                           .withDestinationCode(DESTINATION_CODE)
                           .build();
        testReservationItem = new ReservationItem(
                                    new Passenger("kennethHarris"),
                                    flight.getPricePerSeat(),
                                    flight.generateRandomSeatNumber());
    }

    @Test
    public void testBookPassenger() {
        flight.bookPassenger(testReservationItem);
        assert(flight.getAvailableSeats() == NUMBER_OF_SEATS - 1);
        ReservationItem item = flight.getReservationByPassenger(TEST_PASSENGER);
        assert(item.getPrice() == pricePerSeat);
    }

    @Test
    public void testCancelPassenger() {
        flight.cancelPassenger(testReservationItem);
        assert(flight.getReservationByPassenger(TEST_PASSENGER) == null);
    }

    @Test
    public void testChangePrice() {
        flight.bookPassenger(testReservationItem);
        flight.changePrice(100);
        assert(flight.getPricePerSeat() == 100);
        assert(flight.getReservationByPassenger(TEST_PASSENGER).getPrice() == pricePerSeat);
    }

    @Test
    public void testIsFull() {
        Flight testFlight = new Flight.FlightBuilder()
                                      .withFlightNumber(TEST_FLIGHT_NUMBER)
                                      .withNumberOfSeats(1)
                                      .withPricePerSeat(pricePerSeat)
                                      .withOriginCode(ORIGIN_CODE)
                                      .withDestinationCode(DESTINATION_CODE)
                                      .build();
        testFlight.bookPassenger(testReservationItem);
        assert(testFlight.isFull());
        testFlight.cancelPassenger(testReservationItem);
        assert(testFlight.isFull() == false);
    }

    @Test
    public void testCompareTo() {
        Flight objOne = new Flight.FlightBuilder()
                                  .withFlightNumber(TEST_FLIGHT_NUMBER)
                                  .withNumberOfSeats(1)
                                  .withPricePerSeat(100)
                                  .withOriginCode(ORIGIN_CODE)
                                  .withDestinationCode(DESTINATION_CODE)
                                  .build();
        Flight objTwo = new Flight.FlightBuilder()
                                  .withFlightNumber(TEST_FLIGHT_NUMBER)
                                  .withNumberOfSeats(1)
                                  .withPricePerSeat(200)
                                  .withOriginCode(ORIGIN_CODE)
                                  .withDestinationCode(DESTINATION_CODE)
                                  .build();
        assert(objOne.compareTo(objTwo) == -1);
        assert(objTwo.compareTo(objOne) == 1);
    }

    @Test
    public void testGetAvailableSeats() {
        Flight testFlight = new Flight.FlightBuilder()
                                      .withFlightNumber(TEST_FLIGHT_NUMBER)
                                      .withNumberOfSeats(1)
                                      .withPricePerSeat(pricePerSeat)
                                      .withOriginCode(ORIGIN_CODE)
                                      .withDestinationCode(DESTINATION_CODE)
                                      .build();
        testFlight.bookPassenger(testReservationItem);
        assert(testFlight.getAvailableSeats() == 0);
        testFlight.cancelPassenger(testReservationItem);
        assert(testFlight.getAvailableSeats() == 1);
    }

    @Test
    public void testRecoverSeat() {
        Flight testFlight = new Flight.FlightBuilder()
                                      .withFlightNumber(TEST_FLIGHT_NUMBER)
                                      .withNumberOfSeats(1)
                                      .withPricePerSeat(pricePerSeat)
                                      .withOriginCode(ORIGIN_CODE)
                                      .withDestinationCode(DESTINATION_CODE)
                                      .build();
        testFlight.bookPassenger(testReservationItem);
        assert(testFlight.getAvailableSeats() == 0);
        testFlight.recoverSeat(1);
        assert(testFlight.getAvailableSeats() == 1);
        assert(testFlight.generateRandomSeatNumber() == 1);
    }
}
