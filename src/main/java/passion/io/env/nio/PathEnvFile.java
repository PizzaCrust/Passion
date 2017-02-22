package passion.io.env.nio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import passion.io.env.EnvFile;

public class PathEnvFile implements EnvFile {

    private final Path path;

    public PathEnvFile(Path path) {
        this.path = path;
    }

    @Override
    public String getName() {
        return path.getFileName().toString();
    }

    public Path getPath() {
        return path;
    }

    @Override
    public InputStream openStream() throws IOException {
        return Files.newInputStream(path);
    }

    @Override
    public boolean isDirectory() {
        return Files.isDirectory(path);
    }

}
