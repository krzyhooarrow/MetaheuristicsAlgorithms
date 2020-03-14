package algorithms.dijkstra;

import graphs.Graph;
import graphs.InconsistentGraphException;

import java.util.*;

public class Dijkstra {

    // returns distances from specified source
    public Float[] calculateDistances(Graph graph, Integer source) {

        // starting conditions
        Float[] distances = new Float[graph.getVertices().size()];
        Arrays.fill(distances, Float.MAX_VALUE);
        distances[source] = 0F;

        // generating priory queue
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>(Comparator.comparing(vertex -> vertex.distance));

        for (int i = 0; i < distances.length; i++) priorityQueue.add(new VertexDistance(i, distances[i]));

        VertexDistance current;

        // main loop
        while (!priorityQueue.isEmpty()) {

            current = priorityQueue.poll();

            for (Integer vertexNeighbour : graph.getVertexNeighbours(current.vertex)) {
                // updating values in distances array
                if (distances[vertexNeighbour] > distances[current.vertex] + graph.getEdgeBetweenTwoVertices(current.vertex, vertexNeighbour).getWeight()){

                    distances[vertexNeighbour] = distances[current.vertex] + graph.getEdgeBetweenTwoVertices(current.vertex, vertexNeighbour).getWeight();

                    // updating values in priority queue
                    VertexDistance finalCurrent = current;
                    priorityQueue.stream()
                            .filter(vertexDistance -> vertexDistance.vertex.equals(vertexNeighbour))
                            .forEach(vertexDistance -> vertexDistance.distance = distances[finalCurrent.vertex] +
                             graph.getEdgeBetweenTwoVertices(finalCurrent.vertex, vertexNeighbour).getWeight());
                }
            }
        }
        return distances;
    }


    private final static class VertexDistance{
        private Integer vertex;
        private Float distance;

        private VertexDistance(Integer vertex, Float distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
