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
        String[] splitted = line.split("/");
        return new Pair<>(splitted[0], splitted[1]);
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
