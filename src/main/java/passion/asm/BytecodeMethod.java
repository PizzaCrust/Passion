package passion.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a method in bytecode.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class BytecodeMethod implements EndpointService {

    private final MethodNode methodNode;

    public BytecodeMethod(MethodNode methodNode) {
        this.methodNode = methodNode;
    }

    public MethodNode asm() {
        return methodNode;
    }

    public List<InstructionNode> instructions() {
        List<InstructionNode> instructionNodes = new ArrayList<>();
        methodNode.instructions.iterator().forEachRemaining((instructionNode) -> instructionNodes.add(new RegularNode(methodNode, instructionNode)));
        return instructionNodes;
    }

    public List<Invocation> getInvocations() {
        List<Invocation> invocations = new ArrayList<>();
        instructions().forEach((instructionNode -> {
            if (instructionNode.asm() instanceof MethodInsnNode) {
                invocations.add(new Invocation(methodNode, (MethodInsnNode) instructionNode.asm()));
            }
        }));
        return invocations;
    }

    @Override
    public List<Endpoint> getAllEndpoints() {
        List<Endpoint> endpoints = new ArrayList<>();
        instructions().forEach((instructionNode -> {
            if (instructionNode.asm().getOpcode() == Opcodes.RETURN || instructionNode.asm()
                    .getOpcode() == Opcodes.IRETURN) {
                endpoints.add(new Endpoint(this.methodNode, instructionNode.asm()));
            }
        }));
        return endpoints;
    }

}