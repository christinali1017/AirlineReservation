import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Unit test for flight class.
 */
public class FlightTest {
    private Flight flight;

    private static final String TEST_FLIGHT_NUMBER = "A124";

    private static final int NUMBER_OF_SEATS = 2;

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
        assertTrue(flight.getAvailableSeats() == NUMBER_OF_SEATS - 1);
        ReservationItem item = flight.getReservationByPassenger(TEST_PASSENGER);
        assertTrue(item.getPrice() == pricePerSeat);
        flight.cancelPassenger(testReservationItem);
    }

    @Test
    public void testCancelPassenger() {
        System.out.println(flight.getAvailableSeats());
        flight.bookPassenger(testReservationItem);
        assertNotNull(flight.getReservationByPassenger(TEST_PASSENGER));
        flight.cancelPassenger(testReservationItem);
        assertNull(flight.getReservationByPassenger(TEST_PASSENGER));
        System.out.println(flight.getAvailableSeats());
    }

    @Test
    public void testChangePrice() {
        flight.bookPassenger(testReservationItem);
        flight.changePrice(100);
        assertTrue(flight.getPricePerSeat() == 100);
        assertTrue(flight.getReservationByPassenger(TEST_PASSENGER).getPrice() == pricePerSeat);
        flight.cancelPassenger(testReservationItem);
    }

    @Test
    public void testIsFull() {
        flight.bookPassenger(testReservationItem);
        assertFalse(flight.isFull());
        ReservationItem anotherItem = new ReservationItem(new Passenger("John"), flight.getPricePerSeat(),
                flight.generateRandomSeatNumber());
        flight.bookPassenger(anotherItem);
        assertTrue(flight.isFull());
        flight.cancelPassenger(testReservationItem);
        assertFalse(flight.isFull());
        flight.cancelPassenger(anotherItem);
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
        assertTrue(objOne.compareTo(objTwo) < 0);
        assertTrue(objTwo.compareTo(objOne) > 0);
    }

    @Test
    public void testGetAvailableSeats() {
        flight.bookPassenger(testReservationItem);
        assertTrue(flight.getAvailableSeats() == 1);
        flight.cancelPassenger(testReservationItem);
        assertTrue(flight.getAvailableSeats() == 2);
    }

    @Test
    public void testRecoverSeat() {
        flight.bookPassenger(testReservationItem);
        assertTrue(flight.seatsPool.size() == 1);
        flight.recoverSeat(testReservationItem.getSeatNumber());
        assertTrue(flight.seatsPool.size() == 2);
        flight.cancelPassenger(testReservationItem);
    }
}
