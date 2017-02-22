package passion.io.env.zip;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import passion.io.env.EnvFile;
import passion.io.env.FileEnvironment;
import passion.io.env.nio.PathEnvDirectory;
import passion.io.env.nio.PathEnvFile;

public class ZipFileEnvironment implements FileEnvironment {

    private final Path path;
    private final FileSystem system;

    public ZipFileEnvironment(Path zipPath) throws IOException {
        path = zipPath;
        system = FileSystems.newFileSystem(path, null);
    }

    private Path getRootPath() {
        for (Path path : system.getRootDirectories()) {
            return path;
        }
        throw new RuntimeException();
    }

    @Override
    public List<EnvFile> listFiles() {
        List<EnvFile> envFiles = new ArrayList<>();
        try {
            Files.list(getRootPath()).forEach((children) -> envFiles.add(new PathEnvFile(children)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        List<EnvFile> toBeRemoved = new ArrayList<>();
        List<EnvFile> toBeAdded = new ArrayList<>();
        envFiles.forEach((envFile -> {
            if (envFile.isDirectory()) {
                toBeRemoved.add(envFile);
                toBeAdded.add(new PathEnvDirectory(((PathEnvFile) envFile).getPath()));
            }
        }));
        envFiles.removeAll(toBeRemoved);
        envFiles.addAll(toBeAdded);
        return envFiles;
    }

}
