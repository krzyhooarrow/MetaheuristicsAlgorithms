package problems;

import algorithms.Method;
import algorithms.MethodType;
import algorithms.tabu_search.TabuSearch;
import graphs.DirectedGraph;
import graphs.InconsistentGraphException;
import graphs.UndirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class TSP extends UndirectedGraph implements Problem<LinkedList<Integer>> {


    private Integer currentStep = 0;

    public TSP(int size) {
        super(size);
    }


    @Override
    public LinkedList<Integer> generateInitialSolution() {

        LinkedList<Integer> shuffledVertices = new LinkedList<>(getVertices());
        Collections.shuffle(shuffledVertices);

        return shuffledVertices;
    }

    @Override
    public boolean getStopCondition() {
        return currentStep >= Conditions.MAX_ITERATIONS;
    }

    @Override
    public float eval(LinkedList<Integer> candidate) {
            return getCycleCost(candidate);
    }

    @Override
    public void iterate(Integer iterator) {
        this.currentStep = iterator;
    }

    @Override
    public LinkedList<Integer> substitute(LinkedList<Integer> value1,LinkedList<Integer> value2) {
        LinkedList<Integer> cycle = new LinkedList<>();

        value1.forEach(vertex -> {
            if (!(value1.indexOf(vertex)==value2.indexOf(vertex)))
                cycle.add(vertex);
        });

        return cycle;
    }

    @Override
    public LinkedList<LinkedList<Integer>> getNeighbourhood(LinkedList<Integer> solution) {

        LinkedList<LinkedList<Integer>> neighbourhood = new LinkedList<>();
        List<List<Integer>> permutations =  new Permutations<Integer>().combine(Conditions.PERMUTATIONS_SIZE,solution);



        permutations.forEach(permutation ->
                {
                    LinkedList<Integer> neighbour = new LinkedList<>(solution);
                    Collections.swap(neighbour,neighbour.indexOf(permutation.get(0)),neighbour.indexOf(permutation.get(1)));
                    neighbourhood.addAll(Collections.singleton(neighbour));
        }
        );


        return neighbourhood;
    }

    @Override
    public LinkedList<Integer> getBestCandidate(Set<LinkedList<Integer>> list) {
        return list.stream().min(Comparator.comparing(this::getCycleCost)).get();
    }




}
