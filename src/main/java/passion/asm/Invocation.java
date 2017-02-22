package passion.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.invoke.MethodHandle;

/**
 * Represents the action of a method invocation.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class Invocation extends RegularNode {

    private final NameSpecification owner;
    private final String name;
    private final MethodDescriptor descriptor;

    public Invocation(MethodNode methodNode, MethodInsnNode insnNode) {
        super(methodNode, insnNode);
        this.owner = new NameSpecification(insnNode.owner);
        this.name = insnNode.name;
        this.descriptor = new MethodDescriptor(insnNode.name, insnNode.desc);
        setPrime();
    }

    private void setPrime() {
        MethodInsnNode handle = getHandle();
        handle.owner = this.owner.jvm();
        handle.name = name;
        handle.desc = this.descriptor.getDescriptor();
    }

    private static MethodInsnNode createMethodInsnNode(int opcode, String owner, String name,
                                                       String desc) {
        if (owner == null || name == null || desc == null)
            throw new RuntimeException();
        return new MethodInsnNode(opcode, owner, name, desc);
    }

    public static Invocation invokeStatic(BytecodeMethod bytecodeMethod, String owner, String
            name, String desc) {
        return new Invocation(bytecodeMethod.asm(), Opcodes.INVOKESTATIC, new NameSpecification
                (owner), name, new MethodDescriptor(bytecodeMethod.asm().name, desc));
    }

    public boolean isStaticInvoke() {
        return asm().getOpcode() == Opcodes.INVOKESTATIC;
    }

    public Invocation(MethodNode methodNode, int opcode, NameSpecification owner, String name,
                      MethodDescriptor descriptor) {
        super(methodNode, createMethodInsnNode(opcode, owner.jvm(), name, descriptor
                .getDescriptor()));
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
        setPrime();
    }

    public String getName() {
        return name;
    }

    public NameSpecification getOwner() {
        return owner;
    }

    public MethodDescriptor getDescriptor() {
        return descriptor;
    }

}
