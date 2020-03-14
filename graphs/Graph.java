package graphs;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class Graph {

    final LinkedList<Edge> edges = new LinkedList<Edge>();
    final Set<Integer> vertices = new HashSet<Integer>();



    public Graph(int size) {
      for (int i =0 ; i < size ; i ++)
          vertices.add(i);
    }

    public void addEdge(int from, int to, float weight) {
        if (from!=to)
        edges.add(new Edge(from, to, weight));
        else
            try {
            throw new CycleException();
        } catch (CycleException e) {
            e.printStackTrace();
        }
    }


    public Set<Integer> getVertexNeighbours(Integer vertex){
      throw new NotImplementedException();
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public Edge getLeastWeightedEdge(Integer vertex)  {throw new NotImplementedException();}

    public Edge getEdgeBetweenTwoVertices(int from, int to) {throw new NotImplementedException();  }

    // requires full cycle in List ex. 1-2-3-4-5-6-1
    public Float getCycleCost(LinkedList<Integer> verticesInCycle) throws InconsistentGraphException {throw new NotImplementedException();}

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex){throw new NotImplementedException();}
}
