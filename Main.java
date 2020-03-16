
import algorithms.implemented.tabu_search.TabuSearch;
import graphs.Edge;
import algorithms.permutation.Permutations;
import problems.TSP;

import java.util.*;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        TSP tsp = generateFullGraph(8,50);

        printBruteforce(tsp);

        printTabu(tsp);

    }



    public static TSP generateFullGraph(int size, int weightRange){
        TSP graph = new TSP(size);
        Random generator = new Random();
        for (int i =0 ; i < size-1 ; i ++){
            for (int j = i+1 ; j< size;j++)
            graph.getEdges().add(new Edge(i,j,generator.nextFloat()*weightRange+10));
        }
        return graph;
    }

    public static void printBruteforce(TSP tsp){
        LinkedList<Integer> permutated =new LinkedList<>(tsp.getVertices());
        long permutations = new Permutations<Integer>().factorial(permutated.size());
        LinkedList<LinkedList<Integer>> elements = new LinkedList<>();

        LongStream.range(0, permutations).forEachOrdered(i -> {
            LinkedList<Integer> test = new LinkedList<>(new Permutations<Integer>().permutation(i, permutated));
            elements.add(test);

        });

        System.out.println("Bruteforce (best solution)");
        LinkedList<Integer> solution ;
        solution = elements.stream().min(Comparator.comparing(tsp::getCycleCost)).get();
        System.out.println(solution);
        System.out.println(tsp.getCycleCost(solution));
    }

    public static void printTabu(TSP tsp){

        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<TSP,LinkedList<Integer>> tabuSearch = new TabuSearch<>();
        LinkedList<Integer> bestSolution = tabuSearch.solve(tsp);
        System.out.println(bestSolution);
        System.out.println(tsp.eval(bestSolution));
    }



}
