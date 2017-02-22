package passion.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents file utilities.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class FileUtils {

    /**
     * Recursively finds files, but allows to find a specific file.
     * @param directory
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public static List<File> recursiveSearch(File directory, Predicate<File> filename) throws
            FileNotFoundException {
        return recursiveSearch(directory).stream().filter(filename).collect(Collectors.toList());
    }

    /**
     * Recursively finds all files in a directory.
     * @param directory
     * @return
     * @throws FileNotFoundException
     */
    public static List<File> recursiveSearch(File directory) throws FileNotFoundException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new FileNotFoundException();
        }
        List<File> files = new ArrayList<>();
        File[] listFiles = directory.listFiles();
        if (listFiles == null)
            throw new FileNotFoundException();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                files.addAll(recursiveSearch(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }

}
