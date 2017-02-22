package passion.hash;

/**
 * Represents types of digests that can be processed in the library.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public enum DigestType {
    MD5("MD5");

    private final String string;

    DigestType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

}
