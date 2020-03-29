package problems;

import algorithms.implemented.tabu_search.TabuSearch;
import algorithms.implemented.particle_swarm.ParticleSwarm;
import problems.Functions.GriewankProblem;
import problems.Functions.HappyCatProblem;
import problems.Functions.Vector4D;
import problems.RouteOpt.RouteOptProblem;
import problems.TSP.TSP;


import java.util.LinkedList;

public class Lista_1 {

    public void Zadanie1(String filepath) {

        if (new Parser().setFunctionsMaxTime(filepath)) {

            TabuSearch<HappyCatProblem, Vector4D> tabuSearch = new TabuSearch<>();
            Vector4D bestSolution = tabuSearch.solve(new HappyCatProblem());
            System.out.println(bestSolution.x1 + " " + bestSolution.x2 + " " + bestSolution.x3 + " " + bestSolution.x4 + " " + new HappyCatProblem().eval(bestSolution));



        } else {

            TabuSearch<GriewankProblem, Vector4D> tabuSearch2 = new TabuSearch<>();
            Vector4D bestSolution2 = tabuSearch2.solve(new GriewankProblem());

            System.out.println(bestSolution2.x1 + " " + bestSolution2.x2 + " " + bestSolution2.x3 + " " + bestSolution2.x4 + " " + new GriewankProblem().eval(bestSolution2));


        }
    }


    public void Zadanie2(String filepath) {


        TSP tsp1 = new Parser().generateTSProblem(filepath);


//        TabuSearch<TSP, LinkedList<Integer>> tabuSearch2 = new TabuSearch<>();
//        LinkedList<Integer> bestSolution2 = tabuSearch2.solve(tsp1);
//        System.out.println(tsp1.eval(bestSolution2));
//        System.err.println(bestSolution2);


//        System.out.println("annealing");
//        SimulatedAnnealing<TSP, LinkedList<Integer>> tabuSearch2 = new SimulatedAnnealing<>();
//        LinkedList<Integer> bestSolution2 = tabuSearch2.solve(tsp1);
//        System.out.println(tsp1.eval(bestSolution2));
//        System.err.println(bestSolution2);


        System.out.println("Swarm Opt");
        ParticleSwarm<TSP, LinkedList<Integer>> ps = new ParticleSwarm<>();
        LinkedList<Integer> best = ps.solve(tsp1);
        System.out.println(tsp1.eval(best));
        System.err.println(best);

    }


    public void Zadanie3(String filepath) {

        RouteOptProblem routeOptProblem = new RouteOptProblem(new Parser().routeOptProblemParser(filepath));
        TabuSearch<RouteOptProblem, RouteOptProblem.Point> tabuSearch = new TabuSearch<>();
        RouteOptProblem.Point bestSolution = tabuSearch.solve(routeOptProblem);

        if (routeOptProblem.getNeighbourhood(bestSolution).stream().anyMatch(solution -> solution.value == 8)) {
//            System.out.println("Exit found in neighbourhood of point: " + bestSolution);

            StringBuilder sequence = new StringBuilder();

            Integer startX = bestSolution.x, endX = routeOptProblem.startingX, startY = bestSolution.y, endY = routeOptProblem.startingY;


                if (endX - startX > 0)
                    for (int i = 0; i < endX - startX; i++) sequence.append("L");

                if (endX - startX < 0)
                    for (int i = 0; i < startX - endX; i++) sequence.append("R");

                if (endY - startY > 0)
                    for (int i = 0; i < endY - startY; i++) sequence.append("U");

                if (endY - startY < 0)
                    for (int i = 0; i < startY - endY; i++) sequence.append("D");

            System.out.println("Łączna liczba kroków: " + sequence.length());
            System.err.println(sequence);
        } else System.out.println("Exit not found");

    }


}
