package algorithms.implemented.dijkstra;

import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Dijkstra <U extends DijkstraProblem<T>, T>{

    // returns distances from specified source
    public Map<T,Float> calculateDistances(DijkstraProblem<T> problem, T sourceNode) {
        Map<T,Float> distances = problem
                .getNodes()
                .stream()
                .collect(Collectors.toMap(Function.identity(), node-> Float.MAX_VALUE));

        distances.replace(sourceNode,0F);

        // starting Configuration
        // generating priory queue
        PriorityQueue<VertexDistance<T>> priorityQueue = new PriorityQueue<>(Comparator.comparing(vertex -> vertex.distance));

        distances.forEach((key, value) -> priorityQueue.add(new VertexDistance<T>(key, value)));

        VertexDistance<T> current;

        // main loop
        while (!priorityQueue.isEmpty()) {

            current = priorityQueue.poll();

            for (T vertexNeighbour : problem.getNeighbours(current.vertex)) {
                // updating values in distances array
                if (distances.get(vertexNeighbour) > distances.get(current.vertex) + problem.getDistanceBetweenTwoNodes(current.vertex, vertexNeighbour)){

                    distances.replace(vertexNeighbour,
                    distances.get(current.vertex) + problem.getDistanceBetweenTwoNodes(current.vertex, vertexNeighbour));

                    // updating values in priority queue
                    VertexDistance<T> finalCurrent = current;
                    priorityQueue.stream()
                            .filter(
                                    vertexDistance -> vertexDistance.vertex.equals(vertexNeighbour))
                            .forEach(
                                    vertexDistance -> vertexDistance.distance = distances.get(finalCurrent.vertex)
                                                                +
                                            problem.getDistanceBetweenTwoNodes(finalCurrent.vertex, vertexNeighbour));
                }
            }
        }
        return distances;
    }

    private final static class VertexDistance<T>{
        private final T vertex;
        private Float distance;

        private VertexDistance(T vertex, Float distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
