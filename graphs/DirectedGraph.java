package graphs;


import java.util.LinkedList;

public class DirectedGraph extends Graph {

    public LinkedList<Integer> getVertexNeighbours(Integer vertex){

        LinkedList<Integer> neighbours = new LinkedList<Integer>();

        for (Edge e: edges) {
            if (e.getFrom().equals(vertex))
                neighbours.add(e.getTo());
        }
        return neighbours;
    }

    public Edge getLeastWeightedEdge(Integer vertex){

        Edge leastWeightedEdge = new Edge(-1,-1, Float.MAX_VALUE);

        for (Edge e:edges ) {
            if (e.getFrom().equals(vertex))
                if (e.getWeight() <= leastWeightedEdge.getWeight())
                    leastWeightedEdge = e;
        }
        return leastWeightedEdge;
    }


    public LinkedList<Edge> getNeighbourhoodEdges(Integer vertex){
        LinkedList<Edge> neighbourhood = new LinkedList<Edge>();

        for (Edge e:edges) {
            if (e.getFrom().equals(vertex))
                neighbourhood.add(e);
        }
        return neighbourhood;
    }


}
