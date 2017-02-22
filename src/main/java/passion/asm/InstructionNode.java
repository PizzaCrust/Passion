package passion.asm;

import org.objectweb.asm.tree.AbstractInsnNode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a instruction node.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface InstructionNode
{

    /**
     * Inserts a single instruction before the current instruction.
     * @param instructionNode
     */
    default void insertBefore(InstructionNode instructionNode) {
        insertBefore(Collections.singletonList(instructionNode));
    }

    /**
     * Inserts a list of instruction nodes before the current node.
     * @param instructionNodes
     */
    void insertBefore(List<InstructionNode> instructionNodes);

    /**
     * Inserts a single instruction after the current instruction.
     * @param instructionNode
     */
    default void insertAfter(InstructionNode instructionNode) {
        insertAfter(Collections.singletonList(instructionNode));
    }

    /**
     * Retrieves the previous instruction node.
     * @return
     */
    Optional<InstructionNode> getNext();

    /**
     * Retrieves the previous instruction node.
     * @return
     */
    Optional<InstructionNode> getPrevious();

    /**
     * Inserts a list of instruction nodes after the current node.
     * @param instructionNodeList
     */
    void insertAfter(List<InstructionNode> instructionNodeList);

    /**
     * Retrieves the underlying wrapper of the current instruction node.
     * @return
     */
    AbstractInsnNode asm();

}
