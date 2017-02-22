package passion.io.env.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import passion.io.env.EnvFile;

public class PathEnvDirectory extends PathEnvFile implements EnvFile.Directory {
    public PathEnvDirectory(Path path) {
        super(path);
    }

    @Override
    public EnvFile[] listFiles() {
        List<EnvFile> envFileList = new ArrayList<>();
        try {
            Files.list(getPath()).forEach((path -> envFileList.add(new PathEnvFile(path))));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return envFileList.toArray(new EnvFile[envFileList.size()]);
    }

}
