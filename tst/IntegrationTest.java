import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Integration test for airline reservation system.
 */
public class IntegrationTest {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem();
        flightReservationSystem.handleTransactions();
        flightReservationSystem.createOutput();
    }

}
