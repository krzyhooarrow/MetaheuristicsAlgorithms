package algorithms.implemented.dijkstra;

import java.util.Collection;

public interface DijkstraProblem<T> {
    Collection<T> getNodes();

    Collection<T> getNeighbours(T node);
    Float getDistanceBetweenTwoNodes(T node1,T node2);

}
