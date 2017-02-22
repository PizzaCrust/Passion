package passion.io.env.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import passion.io.env.EnvFile;
import passion.io.env.FileEnvironment;

public class IoFileEnvironment implements FileEnvironment {

    private final File directory;

    public IoFileEnvironment(File directory) {
        this.directory = directory;
    }

    @Override
    public List<EnvFile> listFiles() {
        List<EnvFile> files = new ArrayList<>();
        File[] ioFiles = directory.listFiles();
        if (ioFiles == null) {
            throw new RuntimeException();
        }
        for (File ioFile : ioFiles) {
            if (ioFile.isDirectory()) {
                files.add(new IoEnvDirectory(ioFile));
            } else {
                files.add(new IoEnvFile(ioFile));
            }
        }
        return files;
    }

}
