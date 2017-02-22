package passion.test;

import org.junit.Test;

import passion.asm.MethodDescriptor;
import passion.asm.NameSpecification;
import passion.srg.SrgMappings;
import passion.srg.SrgMethod;

public class SrgRemappingTest {

    @Test
    public void srgTest() {
        SrgMappings srgMappings = new SrgMappings();
        srgMappings.putClass("java.lang.String", "java.base.String");
        srgMappings.getMethods().put(createObfMethod(), createRemappedMethod());
        srgMappings.remapMethodDescriptors();
        srgMappings.lines().forEach(System.out::println);
    }

    private SrgMethod createObfMethod() {
        NameSpecification nameSpecification = new NameSpecification("obfuscatedClass");
        return new SrgMethod(nameSpecification, "obf", new MethodDescriptor(new
                NameSpecification("void"), new NameSpecification("java.lang.String")));
    }

    private SrgMethod createRemappedMethod() {
        NameSpecification remappedClass = new NameSpecification("remappedClass");
        return new SrgMethod(remappedClass, "remapped", new MethodDescriptor(new
                NameSpecification("void"), new NameSpecification("java.lang.String")));
    }

}
