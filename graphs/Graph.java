package graphs;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public abstract class Graph {

    LinkedList<Edge> edges = new LinkedList<Edge>();
    LinkedList<Integer> vertices = new LinkedList<Integer>();


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


    public LinkedList<Integer> getVertexNeighbours(Integer vertex){
      throw new NotImplementedException();
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public LinkedList<Integer> getVertices() {
        return vertices;
    }

    public Edge getLeastWeightedEdge(Integer vertex){throw new NotImplementedException();}


    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex){throw new NotImplementedException();}
}
