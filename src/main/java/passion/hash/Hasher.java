package passion.hash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * A utility class that quickly generates hashes.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class Hasher {

    private final DigestType digestType;
    private final byte[] bytes;

    public Hasher(DigestType type, byte[] bytes) {
        this.digestType = type;
        this.bytes = bytes;
    }

    public Hasher(DigestType type, File file) throws IOException {
        this(type, Files.readAllBytes(file.toPath()));
    }

    /**
     * Hashes the specified bytes.
     * @return
     */
    public byte[] hash() {
        try {
            MessageDigest digest = MessageDigest.getInstance(digestType.toString());
            return digest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible scene!");
        }
    }

    /**
     * Creates a hash hex from a hash.
     * @return
     */
    public String hashHex() {
        return DatatypeConverter.printHexBinary(hash());
    }

}
