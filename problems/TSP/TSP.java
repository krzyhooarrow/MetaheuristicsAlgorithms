package problems.TSP;

import algorithms.Configuration;
import algorithms.implemented.permutation.Permutations;
import algorithms.implemented.annealing.SimulatedAnnealingProblem;
import algorithms.implemented.particle_swarm.ParticleSwarmOptimizationProblem;
import graphs.DirectedGraph;
import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.*;

public class TSP extends DirectedGraph implements TabuSearchProblem<LinkedList<Integer>>, SimulatedAnnealingProblem<LinkedList<Integer>>
        , ParticleSwarmOptimizationProblem<LinkedList<Integer>> {


    public Integer time = 0;

    public TSP(int size) {
        super(size);
    }


    Queue<LinkedList<Integer>> initialSolutionsList = new PriorityQueue<>(Comparator.comparing(this::eval));

    @Override
    public LinkedList<Integer> generateInitialSolution() {

//         nearest neighbour algorithm as initial solution
//
//         for each vertex from V we create greedy hamiltonian cycle using NN algorithm starting from v
        if (initialSolutionsList.isEmpty())
            getVertices()
                    .forEach(vertex -> initialSolutionsList.add(generateGreedyCycle(vertex)));

        return initialSolutionsList.size() > 1 ? initialSolutionsList.poll() : initialSolutionsList.peek();

    }


    private LinkedList<Integer> generateGreedyCycle(Integer vertex){

        LinkedList<Integer> vertices = new LinkedList<>(getVertices());
        LinkedList<Integer> cycle = new LinkedList<>();

        cycle.add(vertex);
        vertices.remove(vertex);

        while (!vertices.isEmpty()){

        cycle.add(closestNeighbour(cycle.getLast(),vertices));

        vertices.remove(cycle.getLast());

        }


        return cycle;
    }

    private Integer closestNeighbour(Integer vertex,List<Integer> vertices){
        return vertices.stream().min(Comparator.comparing(value-> getEdges()[vertex][value])).get();
    }


    @Override
    public boolean getStopCondition() {
        return time >= Configuration.TSP_MAX_TIME;
    }

    @Override
    public float eval(LinkedList<Integer> candidate) {
        return getCycleCost(candidate);
    }

    @Override
    public void iterate( LinkedList<Integer> solution, Integer timer) {
      this.time = timer;
    }

    @Override
    public LinkedList<Integer> substitute(LinkedList<Integer> value1, LinkedList<Integer> value2) {
        LinkedList<Integer> permutation = new LinkedList<>();

        value1.forEach(vertex -> {
            if (!(value1.indexOf(vertex) == value2.indexOf(vertex)))
                permutation.add(vertex);
        });
        permutation.sort(Comparator.comparing(Integer::intValue));
        return permutation;
    }

    @Override
    public boolean isBetterThan(LinkedList<Integer> better, float coefficient, LinkedList<Integer> worse) {
        return eval(better)*coefficient>eval(worse);
    }

    @Override
    public LinkedList<LinkedList<Integer>> getNeighbourhood(LinkedList<Integer> solution) {

        LinkedList<LinkedList<Integer>> neighbourhood = new LinkedList<>();

        new Permutations<Integer>()
            .combine(Configuration.PERMUTATIONS_SIZE, solution)
            .forEach(
                permutation ->
                {
                    LinkedList<Integer> neighbour = new LinkedList<>(solution);
                    Collections.swap(neighbour, neighbour.indexOf(permutation.get(0)), neighbour.indexOf(permutation.get(1)));
                    neighbourhood.addAll(Collections.singleton(neighbour));
                }
        );  // 2 - opt



        return neighbourhood;
    }

    @Override
    public LinkedList<Integer> getBestCandidate(Set<LinkedList<Integer>> list) {
        return list.stream().min(Comparator.comparing(this::getCycleCost)).get();
    }



    @Override
    public Set<LinkedList<Integer>> initializePopulation() {

        if (initialSolutionsList.isEmpty())
            getVertices()
                    .forEach(vertex -> initialSolutionsList.add(generateGreedyCycle(vertex)));

        return new HashSet<>(initialSolutionsList);
    }


    @Override
    public LinkedList<Integer> randomVelocity(LinkedList<Integer> particle) {
        return null;
    }

    @Override
    public LinkedList<Integer> updateVelocity(LinkedList<Integer> velocity, LinkedList<Integer> globalBest, LinkedList<Integer> bestSolution) {
        return null;
    }

    @Override
    public LinkedList<Integer> updatePosition(LinkedList<Integer> currentPosition, LinkedList<Integer> velocity) {
        return null;
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
    @Override
    public LinkedList<LinkedList<Integer>> getRanks(LinkedList<Integer> solution) {
        return null;
    }

}
