package passion.test;

import org.junit.Test;

import java.io.File;

import passion.io.FileUtils;

public class FileUtilsTest {

    @Test
    public void fileUtilsTest() throws Exception {
        File currentDirectory = new File(".");
        System.out.println(currentDirectory.getAbsolutePath());
        FileUtils.recursiveSearch(currentDirectory).forEach((file) -> System.out.println(file.getName()));
    }

}
