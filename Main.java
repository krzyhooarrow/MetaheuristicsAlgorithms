import algorithms.dijkstra.Dijkstra;
import algorithms.opt_2.Prime;
import graphs.Edge;
import graphs.InconsistentGraphException;
import graphs.UndirectedGraph;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


//        UndirectedGraph graph = generateRandomGraph(20,100,20);

//        Prime prime = new Prime();
//
//        try {
//            System.out.println(prime.calculateMinimalSpanningTree(graph).size());
//        } catch (InconsistentGraphException e) {
//            e.printStackTrace();
//        }


        UndirectedGraph dijkstraTest = new UndirectedGraph(9);

        dijkstraTest.addEdge(0,1,4);
        dijkstraTest.addEdge(0,7,8);
        dijkstraTest.addEdge(1,7,11);
        dijkstraTest.addEdge(1,2,8);
        dijkstraTest.addEdge(2,8,2);
        dijkstraTest.addEdge(7,8,7);
        dijkstraTest.addEdge(7,6,1);
        dijkstraTest.addEdge(6,5,2);
        dijkstraTest.addEdge(2,5,4);
        dijkstraTest.addEdge(2,3,7);
        dijkstraTest.addEdge(3,5,14);
        dijkstraTest.addEdge(3,4,9);
        dijkstraTest.addEdge(5,4,10);

        System.out.println(Arrays.toString(new Dijkstra().calculateDistances(dijkstraTest, 0)));


    }




    public static UndirectedGraph generateRandomGraph(int size,int edges, int weightRange){

        UndirectedGraph graph = new UndirectedGraph(size);

        Random generator = new Random();

        for (int i =0 ; i < edges ; i ++){
            graph.getEdges().add(new Edge(generator.nextInt(size),generator.nextInt(size),generator.nextFloat()*weightRange));

        }

        return graph;
    }

}
