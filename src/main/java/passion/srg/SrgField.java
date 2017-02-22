package passion.srg;

import passion.asm.NameSpecification;

/**
 * Represents a SRG field.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class SrgField extends SrgMember {

    public SrgField(NameSpecification owner, String name) {
        super(owner, name);
    }

    public SrgField(String owner, String name) {
        this(new NameSpecification(owner), name);
    }

}
