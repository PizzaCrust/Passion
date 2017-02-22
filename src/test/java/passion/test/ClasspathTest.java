package passion.test;

import org.junit.Test;

import java.io.IOException;

import passion.reflect.Classpath;

public class ClasspathTest {

    @Test
    public void onClasspath() throws IOException {
        Classpath.from(getClass().getClassLoader()).index().forEach((jvmClass -> {
            System.out.println(jvmClass.getName());
        }));
    }

}
