package graphs;

import java.util.LinkedList;

public class UndirectedGraph extends Graph  {

    public void addEdge(int vertex1, int vertex2, float weight) {
        edges.add(new Edge(vertex1,vertex1,weight));
        edges.add(new Edge(vertex2,vertex1,weight));
    }

    public LinkedList<Integer> getVertexNeighbours(Integer vertex){

        LinkedList<Integer> neighbours = new LinkedList<Integer>();

        for (Edge e: edges) {
            if (e.getFrom().equals(vertex))
                neighbours.add(e.getTo());
            else if (e.getTo().equals(vertex))
                neighbours.add(e.getFrom());
        }
        return neighbours;
    }


    public Edge getLeastWeightedEdge(Integer vertex){

        Edge leastWeightedEdge = new Edge(-1,-1, Float.MAX_VALUE);

        for (Edge e:edges ) {
            if (e.getFrom().equals(vertex) || e.getTo().equals(vertex))
                if (e.getWeight() <= leastWeightedEdge.getWeight())
                    leastWeightedEdge = e;
        }
        return leastWeightedEdge;
    }

    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex){
        LinkedList<Edge> neighbourhood = new LinkedList<Edge>();

        for (Edge e:edges) {
            if (e.getFrom().equals(vertex) || e.getTo().equals(vertex))
                neighbourhood.add(e);
        }
        return neighbourhood;
    }


}
