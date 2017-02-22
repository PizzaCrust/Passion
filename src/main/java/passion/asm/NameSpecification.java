package passion.asm;

import org.objectweb.asm.Type;

/**
 * Represents a type name. This class can convert a type name into both standard
 * conventions in Java and the JVM. ASM uses the JVM convention usually, and Java
 * usually uses the Reflection convention.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class NameSpecification {

    private String className;

    public static NameSpecification fromTypeDescriptor(String desc) {
        return new NameSpecification(Type.getType(desc));
    }

    public NameSpecification(String className) {
        if (className == null)
            throw new RuntimeException("null");
        this.className = className;
        transformPrimitives();
    }

    public NameSpecification(Type type) {
        if (type == null || type.getClassName() == null)
            throw new RuntimeException("null");
        this.className = type.getClassName();
        transformPrimitives();
    }

    public String jvm() {
        return className.replace('.', '/');
    }

    public String reflect() {
        return className.replace('/', '.');
    }

    private void transformPrimitives() {
        switch (jvm()) {
            case "boolean":
                className = "Z";
                break;
            case "double":
                className = "D";
                break;
            case "float":
                className = "F";
                break;
            case "char":
                className = "C";
                break;
            case "long":
                className = "J";
                break;
            case "byte":
                className = "B";
                break;
            case "short":
                className = "S";
                break;
            default:
                break;
        }
    }

}
