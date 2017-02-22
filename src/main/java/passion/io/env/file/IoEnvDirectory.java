package passion.io.env.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import passion.io.env.EnvFile;

public class IoEnvDirectory extends IoEnvFile implements EnvFile.Directory {
    public IoEnvDirectory(File file) {
        super(file);
        if (!file.isDirectory()) {
            throw new RuntimeException();
        }
    }

    @Override
    public EnvFile[] listFiles() {
        List<EnvFile> envFileList = new ArrayList<>();
        File[] files = getFile().listFiles();
        if (files == null) {
            throw new RuntimeException();
        }
        for (File file : files) {
            if (!file.isDirectory()) {
                envFileList.add(new IoEnvFile(file));
            } else {
                envFileList.add(new IoEnvDirectory(file));
            }
        }
        return envFileList.toArray(new EnvFile[envFileList.size()]);
    }
}
