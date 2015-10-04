import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for Flight Reservation System.
 */
public class FlightReservationSystemTest {
    private FlightReservationSystem flightReservationSystem;

    private File testFlightInfoFile;

    @Before
    public void setup() throws IOException {
        testFlightInfoFile = File.createTempFile("TestFlightInfo", ".txt");
    }

    @After
    public void afterTest() {
        testFlightInfoFile.deleteOnExit();
    }

    @Test
    public void testInitiateFlights() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFlightInfoFile))) {
            bw.write("K792,26,130,CHI,DFW\n");
            bw.write("A792,56,140,CHI,DFW\n");
            bw.write("A124,54,150,LAS,LAX");
        }
        flightReservationSystem = new FlightReservationSystem(testFlightInfoFile.getAbsolutePath());
        Map<OriginDestinationPair, TreeSet<Flight>> flightsMap = flightReservationSystem.flightsMap;
        assertTrue(flightsMap.containsKey(new OriginDestinationPair("LAS", "LAX")));
        OriginDestinationPair originDestinationPair = new OriginDestinationPair("CHI", "DFW");
        TreeSet<Flight> flights = flightsMap.get(originDestinationPair);
        assertNotNull(flights);
        assertTrue("K792".equals(flights.first().getFlightNumber()));
        assertTrue("A792".equals(flights.last().getFlightNumber()));
    }

    @Test
    public void testProcessBookPassenger() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFlightInfoFile))) {
            bw.write("K792,26,130,CHI,DFW\n");
            bw.write("A792,56,140,CHI,DFW");
        }
        flightReservationSystem = new FlightReservationSystem(testFlightInfoFile.getAbsolutePath());
        String[] transactionInfoArr = {"BookPassenger", "GeorgeWashington", "CHI", "DFW"};
        flightReservationSystem.processBookPassenger(transactionInfoArr);
        Flight flight = flightReservationSystem.flightNumberToFlightMap.get("K792");
        assertNotNull(flight);
        assertTrue(flight.getAvailableSeats() == 26 - 1);
        ReservationItem reservationItem = flight.getReservationByPassenger(new Passenger("GeorgeWashington"));
        assertTrue(reservationItem.getPrice() == 130);
    }

    @Test
    public void testProcessChangePrice() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFlightInfoFile))) {
            bw.write("K792,26,130,CHI,DFW\n");
            bw.write("A792,56,140,CHI,DFW");
        }
        flightReservationSystem = new FlightReservationSystem(testFlightInfoFile.getAbsolutePath());
        OriginDestinationPair originDestinationPair = new OriginDestinationPair("CHI", "DFW");
        Map<OriginDestinationPair, TreeSet<Flight>> flightsMapBeforeChange = flightReservationSystem.flightsMap;
        TreeSet<Flight> flightsBeforeChange = flightsMapBeforeChange.get(originDestinationPair);
        assertTrue("K792".equals(flightsBeforeChange.first().getFlightNumber()));
        assertTrue("A792".equals(flightsBeforeChange.last().getFlightNumber()));

        String[] transactionInfoArr = {"ChangePrice", "A792", "120"};
        flightReservationSystem.processChangePrice(transactionInfoArr);
        Map<OriginDestinationPair, TreeSet<Flight>> flightsMap = flightReservationSystem.flightsMap;
        TreeSet<Flight> flights = flightsMap.get(originDestinationPair);
        assertTrue("K792".equals(flights.last().getFlightNumber()) == true);
        assertTrue("A792".equals(flights.first().getFlightNumber()) == true);
    }

    @Test
    public void testProcessCancelPassenger() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFlightInfoFile))) {
            bw.write("A124,54,150,LAS,LAX\n");
        }
        flightReservationSystem = new FlightReservationSystem(testFlightInfoFile.getAbsolutePath());
        String[] transactionInfoArr = {"BookPassenger", "GeorgeWashington", "LAS", "LAX"};
        flightReservationSystem.processBookPassenger(transactionInfoArr);
        Flight flight = flightReservationSystem.flightNumberToFlightMap.get("A124");
        assertNotNull(flight);
        System.out.println(flight.getAvailableSeats());
        assertTrue(flight.getAvailableSeats() == 54 - 1);
 
        String[] cancelTransactionInfoArr = {"CancelPassenger", "GeorgeWashington", "LAS", "LAX"};
        flightReservationSystem.processCancelPassenger(cancelTransactionInfoArr);
        assertTrue(flight.getAvailableSeats() == 54);
        assertNull(flight.getReservationByPassenger(new Passenger("GeorgeWashington")));
    }
}
