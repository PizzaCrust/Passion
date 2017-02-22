package passion.test;

import org.junit.Test;

import java.io.File;

import passion.io.env.FileEnvironment;
import passion.io.env.zip.ZipFileEnvironment;

import static org.junit.Assert.*;

public class FileEnvironmentTest {

    @Test
    public void fileEnv() {
        File zip = new File("test.zip");
        FileEnvironment environment = FileEnvironment.construct(zip);
        assertEquals(ZipFileEnvironment.class, environment.getClass());
        environment.recursivelyListFiles().forEach((file) -> {
            System.out.println(file.getName());
        });
        FileEnvironment currentDir = FileEnvironment.construct(new File("."));
        currentDir.recursivelyListFiles().forEach((file) -> {
            System.out.println(file.getName());
        });
    }

}
