package graphs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class UndirectedGraph extends Graph {


    public UndirectedGraph(int size) {
        super(size);
    }

    public void addEdge(int vertex1, int vertex2, float weight) {
        edges.add(new Edge(vertex1, vertex2, weight));
        edges.add(new Edge(vertex2, vertex1, weight));
    }

    public Set<Integer> getVertexNeighbours(Integer vertex) {

        Set<Integer> neighbours = new HashSet<Integer>();

        for (Edge e : edges) {
            if (e.getFrom().equals(vertex))
                neighbours.add(e.getTo());
            else if (e.getTo().equals(vertex))
                neighbours.add(e.getFrom());
        }
        return neighbours;
    }

    @Override
    public Edge getEdgeBetweenTwoVertices(int from, int to)  {
        return edges.stream().filter(edge ->
                (edge.getFrom().equals(from) && edge.getTo().equals(to)) || (edge.getTo().equals(from) && edge.getFrom().equals(to)))
                .min(Comparator.comparing(Edge::getWeight)).orElse(null);
    }

    public Edge getLeastWeightedEdge(Integer vertex) {
        return edges.stream().filter(edge -> edge.getFrom().equals(vertex) || edge.getTo().equals(vertex))
                .min(Comparator.comparing(Edge::getWeight)).orElse(null);
    }

    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex) {
        return edges.stream()
                .filter(edge -> edge.getFrom().equals(vertex) || edge.getTo().equals(vertex))
                .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Float getCycleCost(LinkedList<Integer> verticesInCycle) {
        if (!(verticesInCycle.size()>0))
            return Float.POSITIVE_INFINITY;


        float sum = 0F;

        for (int i = 0; i < verticesInCycle.size() - 1; i++) {

            int finalI = i;

            sum += edges.stream()
                    .filter(edge -> edge.getFrom().equals(verticesInCycle.get(finalI)) && edge.getTo().equals(verticesInCycle.get(finalI + 1))
                    ||  edge.getFrom().equals(verticesInCycle.get(finalI+1)) && edge.getTo().equals(verticesInCycle.get(finalI)))
                    .min(Comparator.comparing(Edge::getWeight)).orElse(new Edge(-1,-1,Float.POSITIVE_INFINITY)).getWeight();
        }
        // to make cycle connecting first with the last one

        sum += edges.stream()
                .filter(edge -> edge.getFrom().equals(verticesInCycle.get(0)) && edge.getTo().equals(verticesInCycle.get(verticesInCycle.size()-1))
                        ||  edge.getFrom().equals(verticesInCycle.get(verticesInCycle.size()-1)) && edge.getTo().equals(verticesInCycle.get(0)))
                .min(Comparator.comparing(Edge::getWeight)).orElse(new Edge(-1,-1,Float.POSITIVE_INFINITY)).getWeight();

        return sum;
    }
}
