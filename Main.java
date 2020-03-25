
import algorithms.Configuration;
import algorithms.implemented.permutation.Permutations;
import algorithms.implemented.tabu_search.TabuSearch;
import graphs.Edge;
import problems.Functions.GriewankProblem;
import problems.Functions.HappyCatProblem;
import problems.Lista_1;
import problems.Parser;
import problems.RouteOpt.RouteOptProblem;
import problems.TSP.TSP;
import problems.Functions.Vector4D;

import java.util.*;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        switch (args[0]) {
            case "1":
                new Lista_1().Zadanie1(args[1]);
                break;
            case "2":
                new Lista_1().Zadanie2(args[1]);
                break;
            case "3":
                new Lista_1().Zadanie3(args[1]);
                break;
        }

    }

}
