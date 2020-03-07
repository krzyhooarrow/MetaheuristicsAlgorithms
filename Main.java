import algorithms.opt_2.Prime;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import graphs.Edge;
import graphs.InconsistentGraphException;
import graphs.UndirectedGraph;

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        UndirectedGraph graph = generateRandomGraph(20,100,20);

        Prime prime = new Prime();

        try {
            System.out.println(prime.calculateMinimalSpanningTree(graph).size());
        } catch (InconsistentGraphException e) {
            e.printStackTrace();
        }


    }




    public static UndirectedGraph generateRandomGraph(int size,int edges, int weightRange){

        UndirectedGraph graph = new UndirectedGraph();

        Random generator = new Random();

        for (int i =0 ; i < size;i++)
            graph.getVertices().add(i);


        for (int i =0 ; i < edges ; i ++){
            graph.getEdges().add(new Edge(generator.nextInt(size),generator.nextInt(size),generator.nextFloat()*weightRange));

        }

        return graph;
    }

}
