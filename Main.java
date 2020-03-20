
import algorithms.implemented.tabu_search.TabuSearch;
import graphs.Edge;
import algorithms.permutation.Permutations;
import problems.Functions.GriewankProblem;
import problems.Functions.HappyCatProblem;
import problems.RouteOpt.RouteOptProblem;
import problems.TSP.TSP;
import problems.Functions.Vector4D;

import java.util.*;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        TSP tsp = generateFullGraph(8, 50);

//        printBruteforce(tsp);
//
//        printTabu(tsp);


//        System.out.println(new HappyCatProblem().eval(new Vector4D(-1,-1,-1,-1)));
//        System.out.println(new GriewankProblem().eval(new Vector4D(0,0,0,0)));


//        System.out.println("\nTabu Search (in selected steps)");
//        TabuSearch<HappyCatProblem,Vector4D> tabuSearch = new TabuSearch<>();
//        Vector4D bestSolution = tabuSearch.solve(new HappyCatProblem());
//        System.out.println(bestSolution);
//        System.out.println(new HappyCatProblem().eval(bestSolution));
//
//
//        System.out.println("\nTabu Search (in selected steps)");
//        TabuSearch<GriewankProblem,Vector4D> tabuSearch2 = new TabuSearch<>();
//        Vector4D bestSolution2 = tabuSearch2.solve(new GriewankProblem());
//        System.out.println(bestSolution2);
//        System.out.println(new GriewankProblem().eval(bestSolution2));


        RouteOptProblem routeOptProblem = new RouteOptProblem(generateMap(20));


//        System.out.println(Arrays.deepToString(generateMap(20)));
//
        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<RouteOptProblem, RouteOptProblem.Point> tabuSearch = new TabuSearch<>();
        RouteOptProblem.Point bestSolution = tabuSearch.solve(routeOptProblem);

        if (routeOptProblem.getNeighbourhood(bestSolution).stream().anyMatch(solution -> solution.value == 8))
            System.out.println("Exit found in neighbourhood of point: " + bestSolution);
        else System.out.println("Exit not found");


    }

    public static int[][] generateMap(int size) {
        int[][] map = new int[size][2 * size];


        for (int i = 0; i < size; i++) {
            if (i != 0 && i != size - 1)
                Arrays.fill(map[i], 0);
            else Arrays.fill(map[i], 1);

            map[i][0] = 1;
            map[i][2 * size - 1] = 1;
        }

        map[8][2] = 5;
        map[8][0] = 8;

        return map;
    }


    public static TSP generateFullGraph(int size, int weightRange) {
        TSP graph = new TSP(size);
        Random generator = new Random();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++)
                graph.getEdges().add(new Edge(i, j, generator.nextFloat() * weightRange + 10));
        }
        return graph;
    }

    public static void printBruteforce(TSP tsp) {
        LinkedList<Integer> permutated = new LinkedList<>(tsp.getVertices());
        long permutations = new Permutations<Integer>().factorial(permutated.size());
        LinkedList<LinkedList<Integer>> elements = new LinkedList<>();

        LongStream.range(0, permutations).forEachOrdered(i -> {
            LinkedList<Integer> test = new LinkedList<>(new Permutations<Integer>().permutation(i, permutated));
            elements.add(test);

        });

        System.out.println("Bruteforce (best solution)");
        LinkedList<Integer> solution;
        solution = elements.stream().min(Comparator.comparing(tsp::getCycleCost)).get();
        System.out.println(solution);
        System.out.println(tsp.getCycleCost(solution));
    }

    public static void printTabu(TSP tsp) {

        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<TSP, LinkedList<Integer>> tabuSearch = new TabuSearch<>();
        LinkedList<Integer> bestSolution = tabuSearch.solve(tsp);
        System.out.println(bestSolution);
        System.out.println(tsp.eval(bestSolution));
    }


}
