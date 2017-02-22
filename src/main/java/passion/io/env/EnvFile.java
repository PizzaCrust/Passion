package passion.io.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a file inside of {@link FileEnvironment}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface EnvFile {

    /**
     * Retrieves the name of the file.
     * @return
     */
    String getName();

    /**
     * Opens a stream to the file.
     * @return
     */
    InputStream openStream() throws IOException;

    /**
     * Retrieves if this {@link EnvFile} is a {@link Directory}.
     * @return
     */
    boolean isDirectory();

    /**
     * Represents a directory.
     *
     * @since 1.0-SNAPSHOT
     * @author PizzaCrust
     */
    interface Directory extends EnvFile {

        /**
         * Lists all the files
         * @return
         */
        EnvFile[] listFiles();

        /**
         * Retrieves a file from the file name.
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
         * Lists all the files recursively.
         * @return
         */
        default List<EnvFile> recursivelyListFiles() {
            List<EnvFile> envFiles = new ArrayList<>();
            for (EnvFile envFile : listFiles()) {
                if (envFile.isDirectory()) {
                    Directory directory = (Directory) envFile;
                    envFiles.addAll(directory.recursivelyListFiles());
                } else {
                    envFiles.add(envFile);
                }
            }
            return envFiles;
        }

    }

}
