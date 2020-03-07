package algorithms.opt_2;

import graphs.Edge;
import graphs.Graph;
import graphs.InconsistentGraphException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Prime {


    // returns MST
    public Set<Edge> calculateMinimalSpanningTree(Graph graph) throws InconsistentGraphException {

        Set<Edge> minimalSpanningTreeSubset = new HashSet<Edge>();
        Set<Integer> currentVertices = new HashSet<Integer>();


        Integer startingVertex = graph.getVertices()
                .get(new Random().nextInt(graph.getVertices().size()));


        if (graph.getVertexNeighbours(startingVertex) == null)
            throw new InconsistentGraphException();

        minimalSpanningTreeSubset.add(graph.getLeastWeightedEdge(startingVertex));
        currentVertices.add(startingVertex);

        // searching for MST in neighbourhood. Starting parameters:
        Edge currentLeastWeighted;
        Set<Edge> vertexNeighbourhood = new HashSet<Edge>();
        vertexNeighbourhood.addAll(graph.getNeighbourhoodEdges(startingVertex));

        while (currentVertices.size() != graph.getVertices().size()) {

            currentLeastWeighted = findLeastWeightedMSTEdgeInSubset(vertexNeighbourhood, currentVertices);

            if (currentLeastWeighted.getFrom().equals(-1))
                throw new InconsistentGraphException();

            minimalSpanningTreeSubset.add(currentLeastWeighted);

            if (currentVertices.contains(currentLeastWeighted.getTo())) {
                currentVertices.add(currentLeastWeighted.getFrom());
                vertexNeighbourhood.addAll(graph.getNeighbourhoodEdges(currentLeastWeighted.getFrom()));
            } else {
                currentVertices.add(currentLeastWeighted.getTo());
                vertexNeighbourhood.addAll(graph.getNeighbourhoodEdges(currentLeastWeighted.getTo()));
            }

            // avoid repetitions
            vertexNeighbourhood.removeAll(minimalSpanningTreeSubset);

        }
        return minimalSpanningTreeSubset;
    }


    private Edge findLeastWeightedMSTEdgeInSubset(Set<Edge> edges, Set<Integer> currentVertices) {

        Edge currentLeastWeighted = new Edge(-1, -1, Float.MAX_VALUE);

        for (Edge edge : edges) {
            if (edge.getWeight() < currentLeastWeighted.getWeight() && (!currentVertices.contains(edge.getFrom()) || !currentVertices.contains(edge.getTo())))
                currentLeastWeighted = edge;
        }
        return currentLeastWeighted;
    }


}
