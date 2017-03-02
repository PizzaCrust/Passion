package passion.test;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;

import passion.asm.JvmClass;
import passion.reflect.Classpath;

public class ClasspathTest {

    @Test
    public void onClasspath() throws Exception {
        final boolean[] has = {false};
        final JvmClass[] clazz = new JvmClass[1];
        Classpath.from(getClass().getClassLoader()).index().forEach((jvmClass -> {
            System.out.println(jvmClass.getName());
            if (!has[0]) {
                has[0] = true;
                clazz[0] = jvmClass;
            }
        }));
        byte[] bytes = clazz[0].toBytes();
        ClassReader classReader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);
        classNode.name = "meow/Meow";
        Classpath.from(getClass().getClassLoader()).loadClass(new JvmClass(classNode));
        Class<?> newClazz = Class.forName("meow.Meow");
        System.out.println(newClazz.getName());
    }

}
