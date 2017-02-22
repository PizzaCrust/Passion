package passion.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class in the JVM.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class JvmClass {

    private final NameSpecification name;
    private final List<BytecodeField> fields = new ArrayList<>();
    private final List<BytecodeMethod> methods = new ArrayList<>();
    private final ClassNode handle;

    public ClassNode getHandle() {
        return handle;
    }

    public NameSpecification getName() {
        return name;
    }

    public List<BytecodeField> getFields() {
        return fields;
    }

    public List<BytecodeMethod> getMethods() {
        return methods;
    }

    public JvmClass(ClassNode classNode) {
        name = new NameSpecification(classNode.name);
        handle = classNode;
        classNode.fields.forEach((fieldNode -> fields.add(new BytecodeField(fieldNode))));
        classNode.methods.forEach((methodNode -> methods.add(new BytecodeMethod(methodNode))));
    }

    private static ClassNode read(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);
        return classNode;
    }

    public byte[] toBytes() {
        ClassWriter classWriter = new ClassWriter(0);
        handle.accept(classWriter);
        return classWriter.toByteArray();
    }

    public void toFile(File file) throws IOException {
        Files.write(file.toPath(), toBytes(), StandardOpenOption.CREATE, StandardOpenOption
                .TRUNCATE_EXISTING);
    }

    public JvmClass(byte[] bytes) {
        this(read(bytes));
    }

}
