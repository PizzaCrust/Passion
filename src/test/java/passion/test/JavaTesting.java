package passion.test;

import org.junit.Test;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class JavaTesting {

    @Test
    public void onTest() throws Exception {
        File testZip = new File("test.zip");
        JarFile jarFile = new JarFile(testZip);
        jarFile.stream().forEach((jarEntry -> {
            System.out.println(jarEntry.getName());
            if (jarEntry.getName().lastIndexOf('/') != -1) {
                System.out.println( "parent -> " + jarEntry.getName().substring(0, jarEntry
                        .getName()
                        .lastIndexOf('/')));
            }
        }));
        System.out.println("Filesystems");
        FileSystem fileSystem = FileSystems.newFileSystem(testZip.toPath(), null);
        Path meow = fileSystem.getPath("meow/");
        Files.list(meow).forEach((path) -> System.out.println(path.getFileName()));
    }

}
