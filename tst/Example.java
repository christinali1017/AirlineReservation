import java.io.FileNotFoundException;
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem("./in/inputfile1.txt");
        flightReservationSystem.handleTransactions("./in/inputfile2.txt");
        flightReservationSystem.createOutput("./out/output.txt");
    }
}
