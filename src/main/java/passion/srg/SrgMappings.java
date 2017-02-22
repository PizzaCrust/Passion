package passion.srg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javafx.util.Pair;
import passion.asm.MethodDescriptor;
import passion.asm.NameSpecification;

/**
 * Represents SRG mappings.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class SrgMappings {

    private final Map<String, String> packages = new HashMap<>();
    private final Map<String, String> classes = new HashMap<>();
    private final Map<SrgField, SrgField> fields = new HashMap<>();
    private final Map<SrgMethod, SrgMethod> methods = new HashMap<>();

    public SrgMappings() {}

    public void putClass(String a1, String a2) {
        getClasses().put(new NameSpecification(a1).jvm(), new NameSpecification(a2).jvm());
    }

    public Map<String, String> getClasses() {
        return classes;
    }

    public Map<String, String> getPackages() {
        return packages;
    }

    public Map<SrgField, SrgField> getFields() {
        return fields;
    }

    public Map<SrgMethod, SrgMethod> getMethods() {
        return methods;
    }

    public List<String> lines() {
        List<String> lines = new ArrayList<>();
        forEachPackage((pack1, pack2) -> lines.add("PK: " + pack1 + " " + pack2));
        forEachClass((c1, c2) -> lines.add("CL: " + c1 + " " + c2));
        forEachField((f1, f2) -> lines.add("FD: " + f1.toString() + " " + f2.toString()));
        forEachMethod((m1, m2) -> lines.add("MD: " + m1.toString() + " " + m2.toString()));
        return lines;
    }

    public SrgMappings(File file) throws IOException {
        Files.readAllLines(file.toPath()).forEach((line) -> {
            String[] splitted = line.split(" ");
            if (line.startsWith("PK")) {
                packages.put(splitted[1], splitted[2]);
            }
            if (line.startsWith("CL")) {
                classes.put(splitted[1], splitted[2]);
            }
            if (line.startsWith("FD")) {
                Pair<String, String> pair = SrgMember.parseMember(splitted[1]);
                Pair<String, String> pair1 = SrgMember.parseMember(splitted[2]);
                fields.put(new SrgField(pair.getKey(), pair.getValue()), new SrgField
                        (pair1.getKey(), pair1.getValue()));
            }
            if (line.startsWith("MD")) {
                Pair<String, String> pair = SrgMember.parseMember(splitted[1]);
                MethodDescriptor descriptor = new MethodDescriptor(pair.getValue(), splitted[2]);
                Pair<String, String> pair1 = SrgMember.parseMember(splitted[3]);
                MethodDescriptor descriptor1 = new MethodDescriptor(pair1.getValue(), splitted[4]);
                methods.put(new SrgMethod(pair.getKey(), pair.getValue(), descriptor.getDescriptor()), new
                        SrgMethod(pair.getKey(), pair.getValue(), descriptor1.getDescriptor()));
            }
        });
    }

    public void forEachMethod(BiConsumer<SrgMethod, SrgMethod> consumer) {
        methods.forEach(consumer);
    }

    public void forEachField(BiConsumer<SrgField, SrgField> consumer) {
        fields.forEach(consumer);
    }

    public void forEachClass(BiConsumer<String, String> consumer) {
        classes.forEach(consumer);
    }

    public void forEachPackage(BiConsumer<String, String> consumer) {
        packages.forEach(consumer);
    }

    /**
     * Remaps methods from their descriptors into their remapped forms.
     */
    public void remapMethodDescriptors() {
        forEachMethod((method, method1) -> {
            List<NameSpecification> remappedParameters = new ArrayList<>();
            for (NameSpecification nameSpecification : method1.getMethodDescriptor().getArguments()) {
                if (classes.containsKey(nameSpecification.jvm())) {
                    remappedParameters.add(new NameSpecification(classes.get(nameSpecification
                            .jvm())));
                } else {
                    remappedParameters.add(nameSpecification);
                }
            }
            NameSpecification returnType = method1.getMethodDescriptor().getReturnType();
            if (classes.containsKey(returnType.jvm())) {
                returnType = new NameSpecification(classes.get(returnType.jvm()));
            }
            NameSpecification[] parameterArray = remappedParameters.toArray(new
                    NameSpecification[remappedParameters.size()]);
            MethodDescriptor newDescriptor = new MethodDescriptor(returnType, parameterArray);
            method1.setMethodDescriptor(newDescriptor);
        });
    }

}