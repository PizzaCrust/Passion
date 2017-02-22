package passion.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * Represents the endpoint of a method.
 * Endpoints are usually {@link Opcodes#IRETURN} or {@link Opcodes#RETURN}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class Endpoint extends RegularNode implements InstructionNode {

    public Endpoint(MethodNode methodNode, AbstractInsnNode insnNode) {
        super(methodNode, insnNode);
    }

    static InsnList from(List<InstructionNode> instructionNodes) {
        InsnList insnList = new InsnList();
        instructionNodes.forEach((instructionNode -> insnList.add(instructionNode.asm())));
        return insnList;
    }

    @Override
    public void insertAfter(List<InstructionNode> instructionNodeList) {
        throw new RuntimeException("How do you insert a node after the return node!");
    }

}
