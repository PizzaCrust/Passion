package passion.reflect;

import sun.misc.IOUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import passion.asm.JvmClass;
import passion.io.env.EnvFile;
import passion.io.env.FileEnvironment;

/**
 * Represents a classpath.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class Classpath {

    private final ClassLoader classLoader;

    Classpath(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public static Classpath from(ClassLoader classLoader) {
        return new Classpath(classLoader);
    }

    /**
     * Loads a JVM class into the classpath.
     * This does not replace classes, if you do so! It will cause a {@link LinkageError}.
     * @param jvmClass
     * @throws Exception
     */
    public void loadClass(JvmClass jvmClass) throws Exception {
        Method defineClassMethod = classLoader.getClass().getDeclaredMethod("defineClass", String
                .class, byte[].class, int.class, int.class);
        String name = jvmClass.getName().reflect();
        defineClassMethod.setAccessible(true);
        defineClassMethod.invoke(classLoader, name, jvmClass.toBytes(), 0, jvmClass.toBytes()
                .length);
    }

    /**
     * Indexes the classes inside of the classloader.
     * @return
     * @throws IOException
     */
    public List<JvmClass> index() throws IOException {
        Enumeration<URL> urlResources = classLoader.getResources("");
        List<JvmClass> classes = new ArrayList<>();
        while (urlResources.hasMoreElements()) {
            URL currentElement = urlResources.nextElement();
            if (currentElement != null) {
                File file = new File(currentElement.getPath());
                if (file.getName().endsWith(".class")) {
                    // individual class
                    byte[] bytes = IOUtils.readFully(currentElement.openStream(), -1, false);
                    JvmClass jvmClass = new JvmClass(bytes);
                    classes.add(jvmClass);
                } else if (file.isDirectory() || file.getName().endsWith(".zip") || file.getName
                        ().endsWith(".jar")) {
                    FileEnvironment environment = FileEnvironment.construct(file);
                    for (EnvFile envFile : environment.recursivelyListFiles((envFile) -> envFile.getName().endsWith("" +
                            ".class"))) {
                        byte[] bytes = IOUtils.readFully(envFile.openStream(), -1, false);
                        JvmClass jvmClass = new JvmClass(bytes);
                        classes.add(jvmClass);
                    }
                }
            }
        }
        return classes;
    }

}
