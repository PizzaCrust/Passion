package passion.test;

import org.junit.Test;

import sun.misc.IOUtils;

import java.io.File;

import passion.asm.Invocation;
import passion.asm.JvmClass;

public class AsmMethodTest {

    static class ExampleClass {
        public static void meow() {
            System.out.println("ok");
        }
    }

    static class OtherClass {
        public static void ok() {
            System.out.println("meow");
        }
    }

    @Test
    public void asmTest() throws Exception {
        JvmClass jvmClass = new JvmClass(IOUtils.readFully(getClass().getClassLoader()
                .getResourceAsStream("passion/test/AsmMethodTest$ExampleClass.class"), -1,
                false));
        jvmClass.getMethods().forEach((bytecodeMethod -> {
            if (bytecodeMethod.asm().name.equals("meow") && bytecodeMethod.getDescriptor()
                    .getArguments().length == 0) {
                System.out.println("Found meow");
                bytecodeMethod.getInvocations().forEach((invocation -> System.out.println("Invoke: " + invocation.getOwner().jvm() + ": " +
                        invocation.getName() + ": " + invocation.getDescriptor()
                        .getReturnType().jvm())));
                Invocation invoke = Invocation.invokeStatic(bytecodeMethod, OtherClass.class
                        .getName(), "ok", "()V");
                bytecodeMethod.injectAllEndpoints(invoke, false);
            }
        }));
        File output = new File("output.class");
        System.out.println("output = " + output.getAbsolutePath());
        jvmClass.toFile(output);
    }

}
