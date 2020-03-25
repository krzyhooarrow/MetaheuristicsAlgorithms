package problems;

import algorithms.Configuration;
import graphs.DirectedGraph;
import graphs.Edge;
import graphs.Graph;
import problems.TSP.TSP;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Parser {

    public int[][] routeOptProblemParser(String filepath) {
        LinkedList<LinkedList<Integer>> map = new LinkedList<>();

        int[][] integerMap;

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
            Configuration.ROUTE_OPT_MAX_TIME  = Integer.parseInt(br.readLine().split(" ")[0]);

            String nextLine;
            while ((nextLine = br.readLine()) != null) {

                map.add(Arrays.stream(nextLine.split(""))
                        .mapToInt(Integer::valueOf)
                        .boxed()
                        .collect(Collectors.toCollection(LinkedList::new)));

            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        integerMap = new int[map.getFirst().size()][map.size()];

        for (int i = 0 ; i < map.size();i++)
            for (int j = 0 ; j < map.getFirst().size();j++)
                integerMap[j][i] = map.get(i).get(j);

            return integerMap;
    }


    public TSP generateTSProblem(String filepath){

        LinkedList<LinkedList<Integer>> points = new LinkedList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
            Configuration.TSP_MAX_TIME  = Integer.parseInt(br.readLine().split(" ")[0]);
            String nextLine;
            while ((nextLine = br.readLine()) != null) {

                points.add(Arrays.stream(nextLine.split("\\s+"))
                        .mapToInt(Integer::valueOf)
                        .boxed()
                        .collect(Collectors.toCollection(LinkedList::new)));

            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        TSP tsp = new TSP(points.size());


        for (int i = 0 ; i < points.size();i++)
            for (int j = 0 ; j < points.getFirst().size();j++)
                if (i!=j)
                    tsp.getEdges().add(new Edge(i,j,points.get(i).get(j).floatValue()));

        return tsp;
    }


    public boolean setFunctionsMaxTime(String filepath) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

            String line = br.readLine();

            Configuration.GRIEWANK_MAX_TIME = Integer.parseInt(line.split(" ")[0]);
            Configuration.HAPPYCAT_MAX_TIME = Configuration.GRIEWANK_MAX_TIME;

            return Integer.parseInt(line.split(" ")[1]) == 0;


        } catch (IOException exception) {
            exception.printStackTrace();
        }
        throw new RuntimeException();
    }
}
