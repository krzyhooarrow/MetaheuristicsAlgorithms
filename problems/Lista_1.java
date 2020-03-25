package problems;

import algorithms.implemented.tabu_search.TabuSearch;
import problems.Functions.GriewankProblem;
import problems.Functions.HappyCatProblem;
import problems.Functions.Vector4D;
import problems.RouteOpt.RouteOptProblem;

public class Lista_1 {

    public void Zadanie1(){

        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<HappyCatProblem, Vector4D> tabuSearch = new TabuSearch<>();
        Vector4D bestSolution = tabuSearch.solve(new HappyCatProblem());
        System.out.println(bestSolution);
        System.out.println(new HappyCatProblem().eval(bestSolution));


        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<GriewankProblem,Vector4D> tabuSearch2 = new TabuSearch<>();
        Vector4D bestSolution2 = tabuSearch2.solve(new GriewankProblem());
        System.out.println(bestSolution2);
        System.out.println(new GriewankProblem().eval(bestSolution2));


    }


    public void Zadanie2(String filepath){}



    public void Zadanie3(String filepath){

        RouteOptProblem routeOptProblem = new RouteOptProblem(new Parser().routeOptProblemParser(filepath));

        System.out.println("\nTabu Search (in selected steps)");
        TabuSearch<RouteOptProblem, RouteOptProblem.Point> tabuSearch = new TabuSearch<>();
        RouteOptProblem.Point bestSolution = tabuSearch.solve(routeOptProblem);

        if (routeOptProblem.getNeighbourhood(bestSolution).stream().anyMatch(solution -> solution.value == 8))
            System.out.println("Exit found in neighbourhood of point: " + bestSolution);
        else System.out.println("Exit not found");

    }


}
