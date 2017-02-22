package passion.asm;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;
import java.util.Optional;

/**
 * Represents a regular node.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class RegularNode implements InstructionNode {

    private final MethodNode methodNode;
    private final AbstractInsnNode insnNode;

    public RegularNode(MethodNode methodNode, AbstractInsnNode insnNode) {
        this.insnNode = insnNode;
        this.methodNode = methodNode;
    }

    @Override
    public void insertBefore(List<InstructionNode> instructionNodes) {
        methodNode.instructions.insertBefore(asm(), Endpoint.from(instructionNodes));
    }

    @Override
    public Optional<InstructionNode> getNext() {
        if (insnNode.getNext() != null) {
            return Optional.of(new RegularNode(methodNode, insnNode.getNext()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<InstructionNode> getPrevious() {
        if (insnNode.getPrevious() != null) {
            return Optional.of(new RegularNode(methodNode, insnNode.getPrevious()));
        }
        return Optional.empty();
    }

    @Override
    public void insertAfter(List<InstructionNode> instructionNodeList) {
        methodNode.instructions.insert(insnNode, Endpoint.from(instructionNodeList));
    }

    @Override
    public AbstractInsnNode asm() {
        return this.insnNode;
    }

}
