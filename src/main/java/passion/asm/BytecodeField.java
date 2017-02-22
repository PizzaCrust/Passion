package passion.asm;

import org.objectweb.asm.tree.FieldNode;

/**
 * Represents a bytecode field.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class BytecodeField {

    private final FieldNode fieldNode;

    public BytecodeField(FieldNode fieldNode) {
        this.fieldNode = fieldNode;
    }

    public NameSpecification getType() {
        return NameSpecification.fromTypeDescriptor(fieldNode.desc);
    }

    public FieldNode asm() {
        return fieldNode;
    }

}
