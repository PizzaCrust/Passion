package passion.asm;

import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * Represents a instruction node.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface InstructionNode
{

    /**
     * Retrieves the underlying wrapper of the current instruction node.
     * @return
     */
    AbstractInsnNode asm();

}
