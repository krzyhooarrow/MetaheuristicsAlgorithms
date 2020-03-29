package graphs;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class Graph {

    //    final LinkedList<Edge> edges = new LinkedList<Edge>();
    final Set<Integer> vertices = new HashSet<Integer>();

    private final Float[][] edges;

    public Graph(int size) {
        for (int i = 0; i < size; i++)
            vertices.add(i);
        edges = new Float[size][size];
        for (Float[] row : edges) {
            Arrays.fill(row, 0F);
        }

    }

    public void addEdge(int from, int to, float weight) {
        if (from != to)
            edges[from][to] = weight;
        else
            try {
                throw new CycleException();
            } catch (CycleException e) {
                e.printStackTrace();
            }
    }


    public Set<Integer> getVertexNeighbours(Integer vertex) {
        throw new NotImplementedException();
    }

    public Float[][] getEdges() {

        return Arrays.stream(edges).map(Float[]::clone).toArray(Float[][]::new);
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public Float getLeastWeightedEdge(Integer vertex) {
        throw new NotImplementedException();
    }

    public Float getEdgeBetweenTwoVertices(int from, int to) {
        return edges[from][to];
    }

    // requires full cycle in List ex. 1-2-3-4-5-6-1
    public Float getCycleCost(LinkedList<Integer> verticesInCycle) throws InconsistentGraphException {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex) {
        throw new NotImplementedException();
    }
}
