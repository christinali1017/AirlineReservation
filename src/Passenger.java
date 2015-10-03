/**
 * Passenger.
 */
public class Passenger {
    /**
     * Passenger name.
     */
    private final String name;

    /**
     * Constructor.
     */
    public Passenger(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Passenger name should not be null.");
        }
        this.name = name;
    }

    /**
     * Get passenger name.
     * @return passenger name.
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return  prime + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Passenger)) {
            return false;
        }
        Passenger other = (Passenger) obj;
        return this.name.equals(other.name);
    }
}
