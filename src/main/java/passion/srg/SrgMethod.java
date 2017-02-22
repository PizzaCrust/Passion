package passion.srg;

import passion.asm.MethodDescriptor;
import passion.asm.NameSpecification;

/**
 * Represents a SRG method.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class SrgMethod extends SrgMember {

    private MethodDescriptor methodDescriptor;

    public SrgMethod(NameSpecification owner, String name, String desc) {
        super(owner, name);
        this.methodDescriptor = new MethodDescriptor(name, desc);
    }

    public SrgMethod(NameSpecification owner, String name, MethodDescriptor descriptor) {
        super(owner, name);
        this.methodDescriptor = descriptor;
    }

    public SrgMethod(String owner, String name, String desc) {
        this(new NameSpecification(owner), name, desc);
    }

    public MethodDescriptor getMethodDescriptor() {
        return methodDescriptor;
    }

    public void setMethodDescriptor(MethodDescriptor methodDescriptor) {
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getMethodDescriptor().getDescriptor();
    }

}