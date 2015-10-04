/**
 * Origin destination pair.
 *
 * <p>Pair contains:</p>
 * <ul>
 * <li>Flight origin code</li>
 * <li>Flight destination code</li>
 * </ul>
 */

public class OriginDestinationPair {

    /**
     * Flight origin code.
     */
    private final String originCode;

    /**
     * Flight destination code.
     */
    private final String destinationCode;

    /**
     * Constructor.
     * Create OriginDestinationPair instance with flight origin and destination.
     */
    public OriginDestinationPair(final String originCode, final String destinationCode) {
        if (!(validAirportCode(originCode) && validAirportCode(destinationCode))) {
            throw new IllegalArgumentException("Flight origin and destination are not valid."
                    + " Valid Origin & Destination are 3 letter airport/city codes");
        }
        this.originCode = originCode;
        this.destinationCode = destinationCode;
    }

    /**
     * Valid airport/city code.
     * Validate rule: Origin & Destination are 3 letter airport/city codes.
     * @param code airport/city code.
     * @return true if the airport code fulfill the above validate rule.
     */
    private boolean validAirportCode (final String code) {
        if (code == null || code.length() != 3) {
            return false;
        }
        for (int i = 0; i < code.length(); i++) {
            if (!isLetter(code.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a character is letter.
     * If it is a letter, it should belongs to [A,Z] or [a,z].
     * @param c input character.
     * @return true if the input is a letter.
     */
    private boolean isLetter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + this.destinationCode.hashCode();
        hash = prime * hash + this.originCode.hashCode();
        return hash;
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
        return this.destinationCode.equals(pair.destinationCode) && this.originCode.equals(pair.originCode);
    }

    /**
     * Get origin code.
     * @return origin code.
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * Get destination code.
     * @return destination code.
     */
    public String getDestinationCode() {
        return destinationCode;
    }
}
