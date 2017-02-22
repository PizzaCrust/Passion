package passion.io.env;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import passion.io.env.file.IoFileEnvironment;
import passion.io.env.zip.ZipFileEnvironment;

/**
 * Represents a file environment.
 * This can be a JAR, ZIP, or a directory, as long as it implements this interface.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface FileEnvironment {

    /**
     * The files in the directory. This should be an immutable list.
     * @return
     */
    List<EnvFile> listFiles();

    /**
     * Retrieves a file from the environment.
     * @param name
     * @return
     */
    default Optional<EnvFile> getFile(String name) {
        for (EnvFile envFile : listFiles()) {
            if (envFile.getName().equals(name)) {
                return Optional.of(envFile);
            }
        }
        return Optional.empty();
    }

    /**
     * Selects a implementation of {@link FileEnvironment} for the specified file.
     * @param file
     * @return
     */
    static FileEnvironment construct(File file) {
        if (file.isDirectory()) {
            return new IoFileEnvironment(file);
        }
        if (file.getName().endsWith(".jar") || file.getName().endsWith(".zip")) {
            try {
                return new ZipFileEnvironment(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        throw new UnsupportedOperationException("Invalid input");
    }

    /**
     * Lists all the files selected by the selector.
     * @param selector
     * @return
     */
    default List<EnvFile> listFiles(Predicate<EnvFile> selector) {
        List<EnvFile> envFiles = listFiles();
        List<EnvFile> selected = new ArrayList<>();
        envFiles.forEach((envFile -> {
            if (selector.test(envFile)) {
                selected.add(envFile);
            }
        }));
        return selected;
    }

    /**
     * Recursively lists files in the environment.
     * @return
     */
    default List<EnvFile> recursivelyListFiles() {
        List<EnvFile> files = new ArrayList<>();
        listFiles().forEach((file -> {
            if (file.isDirectory()) {
                files.addAll(((EnvFile.Directory) file).recursivelyListFiles());
            } else {
                files.add(file);
            }
        }));
        return files;
    }

}
