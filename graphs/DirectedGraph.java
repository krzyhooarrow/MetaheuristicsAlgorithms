package graphs;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectedGraph extends Graph {


    public DirectedGraph(int size) {
        super(size);
    }

    public Set<Integer> getVertexNeighbours(Integer vertex) {
        Set<Integer> neighbours = new HashSet<Integer>();

        for (int i =0 ; i < getEdges()[0].length;i++)
            if (getEdges()[vertex][i]!=0)
                neighbours.add(vertex);


        return neighbours;
    }

    @Override
    public Float getEdgeBetweenTwoVertices(int from, int to) {
        return getEdges()[from][to];
    }


    @Override
    public Float getCycleCost(LinkedList<Integer> verticesInCycle) {

        LinkedList<Integer> copy = new LinkedList<>(verticesInCycle);
        copy.add(copy.getFirst());

        float sum = 0F;

        for (int i = 0; i < verticesInCycle.size(); i++) {
            sum += getEdges()[copy.get(i)][copy.get(i+1)];
        }

        return sum;
    }
}
