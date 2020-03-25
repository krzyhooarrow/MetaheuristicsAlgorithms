package problems.TSP;

import algorithms.Configuration;
import algorithms.implemented.permutation.Permutations;
import graphs.DirectedGraph;
import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.*;

public class TSP extends DirectedGraph implements TabuSearchProblem<LinkedList<Integer>> {

    private Integer currentStep = 0;

    public Integer time = 0;

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
        return time >= Configuration.TSP_MAX_TIME;
//                currentStep >= Configuration.TSP_MAX_ITERATIONS;
    }

    @Override
    public float eval(LinkedList<Integer> candidate) {
        return getCycleCost(candidate);
    }

    @Override
    public void iterate(Integer iterator, LinkedList<Integer> solution, Integer timer) {
        this.currentStep = iterator; this.time = timer;
    }

    @Override
    public LinkedList<Integer> substitute(LinkedList<Integer> value1, LinkedList<Integer> value2) {
        LinkedList<Integer> cycle = new LinkedList<>();

        value1.forEach(vertex -> {
            if (!(value1.indexOf(vertex) == value2.indexOf(vertex)))
                cycle.add(vertex);
        });
        cycle.sort(Comparator.comparing(Integer::intValue));
        return cycle;
    }

    @Override
    public boolean isBetterThan(LinkedList<Integer> better, float coefficient, LinkedList<Integer> worse) {
        return eval(better)*coefficient>eval(worse);
    }

    @Override
    public LinkedList<LinkedList<Integer>> getNeighbourhood(LinkedList<Integer> solution) {

        LinkedList<LinkedList<Integer>> neighbourhood = new LinkedList<>();
        List<List<Integer>> permutations = new Permutations<Integer>().combine(Configuration.PERMUTATIONS_SIZE, solution);

        permutations.forEach(
                permutation ->
                {
                    LinkedList<Integer> neighbour = new LinkedList<>(solution);
                    Collections.swap(neighbour, neighbour.indexOf(permutation.get(0)), neighbour.indexOf(permutation.get(1)));
                    neighbourhood.addAll(Collections.singleton(neighbour));
                }
        );

        return neighbourhood;
    }

    @Override
    public LinkedList<Integer> getBestCandidate(Set<LinkedList<Integer>> list) {
        return list.stream().min(Comparator.comparing(this::getCycleCost)).get();
    }


//    @Override
//    public LinkedList<LinkedList<Integer>> getComponents(LinkedList<Integer> solution) {
//
//        LinkedList<LinkedList<Integer>> neighbourhood = new LinkedList<>();
//
//        solution.forEach(item -> neighbourhood.add(new LinkedList<>(Collections.singleton(item))));
//
//        return neighbourhood;
//
//    }
//
//    @Override
//    public float componentEval(LinkedList<Integer> currentSolution, LinkedList<Integer> candidate) {
//
//        List<Edge> edges = new LinkedList<>();
//
//        candidate.forEach(elementInCycle -> {
//            edges.addAll(getEdges()
//                    .stream()
//                    .filter(edge -> candidate.contains(edge.getFrom()) || candidate.contains(edge.getTo()))
//                    .collect(Collectors.toList()));
//
//        });
//        edges.sort(Comparator.comparing(Edge::getWeight));
//
//        int fitness = edges
//                .stream()
//                .filter(
//                        edge ->  neighbour(currentSolution,candidate.get(0)).contains(edge.getFrom())  ||
//                        neighbour(currentSolution,candidate.get(0)).contains(edge.getTo())
//                        )
//                .mapToInt(edges::indexOf)
//                .sum();
//
//     return 3F/(fitness+2);
//    }

//    public LinkedList<Integer> neighbour(LinkedList<Integer> currentSolution, Integer candidate) {
//
//        return currentSolution.stream()
//                .filter(element -> {
//                    if ((currentSolution.indexOf(candidate) == 0)) {
//                        return currentSolution.indexOf(element) == 1 || currentSolution.indexOf(element) == currentSolution.size() - 1;
//                    } else if ((currentSolution.indexOf(candidate) == currentSolution.size() - 1)) {
//                        return currentSolution.indexOf(element) == 0 || currentSolution.indexOf(element) == currentSolution.size() - 2;
//                    } else {
//                        return currentSolution.indexOf(element) == currentSolution.indexOf(candidate) - 1
//                                || currentSolution.indexOf(element) == currentSolution.indexOf(candidate) + 1;
//                    }
//                }
//        ).collect(Collectors.toCollection(LinkedList::new));
//    }
//
//
//    @Override
//    public LinkedList<Integer> replace(LinkedList<Integer> currentSolution, LinkedList<Integer> weakSolution, LinkedList<Integer> randomSolution) {
//        LinkedList<Integer> replaced = new LinkedList<>(currentSolution);
//
//        for (int i =0 ; i < weakSolution.size();i++)
//            Collections.swap(replaced,replaced.indexOf(weakSolution.get(i)),replaced.indexOf(randomSolution.get(i)));
//
//        return replaced;
//    }
//
//
//    @Override
//    public LinkedList<LinkedList<Integer>> getRanks(LinkedList<Integer> solution) {
//        return null;
//    }

}
