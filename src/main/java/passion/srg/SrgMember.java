package passion.srg;

import javafx.util.Pair;
import passion.asm.NameSpecification;

/**
 * Represents a member in a SRG.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public abstract class SrgMember {

    private final NameSpecification owner;
    private final String name;

    public SrgMember(NameSpecification owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public static Pair<String, String> parseMember(String line) {
        String owner = line.substring(0, line.lastIndexOf("/"));
        String name = line.substring(line.lastIndexOf("/") + 1);
        return new Pair<>(owner, name);
    }

    public NameSpecification getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return owner.jvm() + "/" + name;
    }

}
