import algorithms.tabu_search.TabuSearch;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import graphs.Edge;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.paukov.combinatorics3.Generator;
import problems.Conditions;
import problems.Permutations;
import problems.TSP;

import java.util.*;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        TSP dijkstraTest = generateFullGraph(9,20);

//        dijkstraTest.addEdge(0,1,4);
//        dijkstraTest.addEdge(0,7,8);
//        dijkstraTest.addEdge(1,7,11);
//        dijkstraTest.addEdge(1,2,8);
//        dijkstraTest.addEdge(2,8,2);
//        dijkstraTest.addEdge(7,8,7);
//        dijkstraTest.addEdge(7,6,1);
//        dijkstraTest.addEdge(6,5,2);
//        dijkstraTest.addEdge(2,5,4);
//        dijkstraTest.addEdge(2,3,7);
//        dijkstraTest.addEdge(3,5,14);
//        dijkstraTest.addEdge(3,4,9);
//        dijkstraTest.addEdge(5,4,10);


        LinkedList<Integer> permutated =new LinkedList<>(dijkstraTest.getVertices());
        long permutations = new Permutations<Integer>().factorial(permutated.size());
        LinkedList<LinkedList<Integer>> elements = new LinkedList<>();

        LongStream.range(0, permutations).forEachOrdered(i -> {
           LinkedList<Integer> test = new LinkedList<>(new Permutations<Integer>().permutation(i, permutated));
           elements.add(test);

        });


        LinkedList<Integer> solution ;
        solution = elements.stream().min(Comparator.comparing(dijkstraTest::getCycleCost)).get();
        System.out.println(solution);
        System.out.println(dijkstraTest.getCycleCost(solution));


        System.out.println("\nTABU\n");
        TabuSearch<TSP,LinkedList<Integer>> tabuSearch = new TabuSearch<>();
        LinkedList<Integer> bestSolution = tabuSearch.solve(dijkstraTest);
        System.out.println(bestSolution);
        System.out.println(dijkstraTest.eval(bestSolution));



    }




    public static TSP generateRandomGraph(int size,int edges, int weightRange){
        TSP graph = new TSP(size);
        Random generator = new Random();
        for (int i =0 ; i < edges ; i ++){
            graph.getEdges().add(new Edge(generator.nextInt(size),generator.nextInt(size),generator.nextFloat()*weightRange));
        }
        return graph;
    }


    public static TSP generateFullGraph(int size, int weightRange){
        TSP graph = new TSP(size);
        Random generator = new Random();
        for (int i =0 ; i < size-1 ; i ++){
            for (int j = i+1 ; j< size;j++)
            graph.getEdges().add(new Edge(i,j,generator.nextFloat()*weightRange));
        }
        return graph;
    }

}
