import java.io.FileNotFoundException;
import java.io.IOException;

public class SystemRunner {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String inputFile1Path = "../in/inputfile1.txt";
        String inputFile2Path = "../in/inputfile2.txt";
        String outputFilePath = "../out/output.txt";
        if (args.length > 0) {
            inputFile1Path = args[0];
        }
        if (args.length > 1) {
            inputFile2Path = args[1];
        }
        if (args.length > 2) {
            outputFilePath = args[2];
        }
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem(inputFile1Path);
        flightReservationSystem.handleTransactions(inputFile2Path);
        flightReservationSystem.createOutput(outputFilePath);
    }
}
