package passion.test;

import org.junit.Test;

import java.nio.charset.Charset;

import passion.hash.DigestType;
import passion.hash.Hasher;

import static org.junit.Assert.*;

public class HashTest {

    @Test
    public void onHash() {
        Hasher hasher = new Hasher(DigestType.MD5, "hi".getBytes(Charset.defaultCharset()));
        System.out.println(hasher.hashHex());
        assertEquals("49f68a5c8493ec2c0bf489821c21fc3b".toUpperCase(), hasher.hashHex());
    }

}
