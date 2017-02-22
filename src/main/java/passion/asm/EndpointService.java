package passion.asm;

import java.util.Collections;
import java.util.List;

/**
 * Represents a service the represents the implementing class as a class that
 * can produce endpoints and organize them.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface EndpointService {

    /**
     * Retrieves all the endpoints in the service.
     * @return
     */
    List<Endpoint> getAllEndpoints();

    /**
     * This method automatically injects the specified list of instructions into all endpoints.
     * @param instructionNodes
     */
    default void injectAllEndpoints(List<InstructionNode> instructionNodes, boolean after) {
        getAllEndpoints().forEach((endpoint -> {
            if (after) {
                endpoint.insertAfter(instructionNodes);
            } else {
                endpoint.insertBefore(instructionNodes);
            }
        }));
    }

    /**
     * This method automatically injects ONE instruction into all endpoints.
     * @param instructionNode
     */
    default void injectAllEndpoints(InstructionNode instructionNode, boolean after) {
        injectAllEndpoints(Collections.singletonList(instructionNode), after);
    }



}
