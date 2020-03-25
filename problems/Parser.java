package problems;

import problems.TSP.TSP;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Parser {

    public int[][] routeOptProblemParser(String filepath) {
        LinkedList<LinkedList<Integer>> map = new LinkedList<>();

        int[][] integerMap;

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
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


//    public TSP generateTSProblem(String filepath){
//
//
//
//
//    }



}
