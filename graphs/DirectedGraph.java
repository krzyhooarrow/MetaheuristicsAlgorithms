package graphs;


import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectedGraph extends Graph {


    public DirectedGraph(int size) {
        super(size);
    }

    public Set<Integer> getVertexNeighbours(Integer vertex) {
        Set<Integer> neighbours = new HashSet<Integer>();

        for (Edge e : edges) {
            if (e.getFrom().equals(vertex))
                neighbours.add(e.getTo());
        }
        return neighbours;
    }

    public Edge getLeastWeightedEdge(Integer vertex) {
        return edges.stream().filter(edge -> edge.getFrom().equals(vertex))
                .min(Comparator.comparing(Edge::getWeight)).orElse(null);
    }


    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex) {
        return edges.stream()
                .filter(edge -> edge.getFrom().equals(vertex))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Edge getEdgeBetweenTwoVertices(int from, int to) {
        return edges.stream().filter(edge ->
                (edge.getFrom().equals(from) && edge.getTo().equals(to)))
                .min(Comparator.comparing(Edge::getWeight)).orElse(null);
    }


    @Override
    public Float getCycleCost(LinkedList<Integer> verticesInCycle) {
        float sum = 0F;

        for (int i = 0; i < verticesInCycle.size() - 1; i++) {

            int finalI = i;

            sum += edges.stream()
                    .filter(edge -> edge.getFrom().equals(verticesInCycle.get(finalI)) && edge.getTo().equals(verticesInCycle.get(finalI + 1)))
                    .min(Comparator.comparing(Edge::getWeight)).orElse(new Edge(-1,-1,Float.POSITIVE_INFINITY)).getWeight();
        }

        sum += edges.stream()
                .filter(edge -> edge.getFrom().equals(verticesInCycle.get(verticesInCycle.size()-1)) && edge.getTo().equals(verticesInCycle.get(0)))
                .min(Comparator.comparing(Edge::getWeight)).orElse(new Edge(-1,-1,Float.POSITIVE_INFINITY)).getWeight();

        return sum;
    }
}
