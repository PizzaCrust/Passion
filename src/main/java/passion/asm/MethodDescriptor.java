package passion.asm;

import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the method's descriptor.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class MethodDescriptor {

    private final NameSpecification returnType;
    private final NameSpecification[] arguments;

    public MethodDescriptor(NameSpecification returnType, NameSpecification... arguments) {
        if (returnType == null || arguments == null)
            throw new RuntimeException("null");
        this.returnType = returnType;
        this.arguments = arguments;
    }

    public NameSpecification getReturnType() {
        return returnType;
    }

    public NameSpecification[] getArguments() {
        return arguments;
    }

    public MethodDescriptor(String name, String descriptor) {
        if (name == null || descriptor == null)
            throw new RuntimeException("null");
        Method method = new Method(name, descriptor);
        List<NameSpecification> arguments = new ArrayList<>();
        for (Type type : method.getArgumentTypes()) {
            arguments.add(new NameSpecification(type));
        }
        this.returnType = new NameSpecification(method.getReturnType());
        this.arguments = arguments.toArray(new NameSpecification[arguments.size()]);
    }

    public String getDescriptor() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (NameSpecification argument : arguments) {
            if (argument.jvm().length() == 1) {
                stringBuilder.append(argument.jvm());
            } else {
                stringBuilder.append('L');
                stringBuilder.append(argument.jvm());
                stringBuilder.append(';');
            }
        }
        stringBuilder.append(')');
        if (returnType.jvm().length() == 1) {
            stringBuilder.append(returnType.jvm());
        } else {
            stringBuilder.append('L');
            stringBuilder.append(returnType.jvm());
            stringBuilder.append(';');
        }
        return stringBuilder.toString();
    }

}