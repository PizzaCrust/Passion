package passion.io.env.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import passion.io.env.EnvFile;

public class IoEnvFile implements EnvFile {

    private final File file;

    public IoEnvFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public InputStream openStream() throws IOException {
        return file.toURI().toURL().openStream();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

}
