/**
 * Origin destination pair.
 *
 * <p>Pair contains:</p>
 * <ul>
 * <li>Flight origin</li>
 * <li>Flight destination</li>
 * </ul>
 */

public class OriginDestinationPair {
    /**
     * Flight origin.
     */
    private String origin;

    /**
     * Flight destination.
     */
    private String destination;

    /**
     * Constructor.
     * Create OriginDestinationPair instance with flight origin and destination.
     */
    public OriginDestinationPair(final String origin, final String destination) {
        if (origin == null || destination == null) {
            throw new IllegalArgumentException("Flight origin and destination should not be null");
        }
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Valid airport/city code.
     * Validate rule: Origin & Destination are 3 letter airport/city codes
     * @param code airport/city code.
     * @return true if the airport code fulfill the above validate rule.
     */
    private boolean validAirportCode (final String code) {
        if (code == null || code.length() != 3) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OriginDestinationPair)) {
            return false;
        }
        OriginDestinationPair pair = (OriginDestinationPair) obj;
        if (destination == null) {
            if (pair.destination != null)
                return false;
        } else if (!destination.equals(pair.destination))
            return false;
        if (origin == null) {
            if (pair.origin != null)
                return false;
        } else if (!origin.equals(pair.origin))
            return false;
        return true;
    }
}
