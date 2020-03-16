package algorithms.not_implemented.opt_2;


import graphs.Edge;
import graphs.Graph;
import graphs.InconsistentGraphException;

import java.util.HashSet;
import java.util.Set;

// 2 - optimal algorithm for metric TSP
public class Opt_2 {

    public Set<Integer> getHamiltonianCycle(Graph graph) throws InconsistentGraphException {

        Set<Edge> minimalSpanningTree = new Prime().calculateMinimalSpanningTree(graph);

        return swapVertices(minimalSpanningTree);
    }


    private Set<Integer> swapVertices(Set<Edge> MST){

        Set<Integer> vertices = new HashSet<Integer>();

        for (Edge edge: MST) {

        }


        return vertices;
    }



}
