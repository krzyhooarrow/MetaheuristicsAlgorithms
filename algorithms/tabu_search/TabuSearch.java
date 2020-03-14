package algorithms.tabu_search;


import algorithms.Method;
import problems.Problem;
import problems.TSP;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TabuSearch<U extends Problem<T>, T> {

    private LinkedList<T> tabuList = new LinkedList<>();

    public T solve(Problem<T> problem) {
        int iterations = 0;

        T bestSolution = problem.generateInitialSolution();
        T currentSolution = bestSolution;
        Set<T> candidateList = new HashSet<>();



        while (!problem.getStopCondition()) {

            // neighbourhood ma teraz permutacje
            for (T candidate : problem.getNeighbourhood(currentSolution)) {

                if (!tabuList.contains(candidate))
                    candidateList.add(candidate);

            }

            // foreach element in tabu list -> current solution swap with thoose elements
            currentSolution = problem.getBestCandidate(candidateList);

            tabuList.add(problem.substitute(bestSolution,currentSolution));

            if (problem.eval(currentSolution) < problem.eval(bestSolution)) {
                bestSolution = currentSolution;
            } else break;

            candidateList.clear();
            problem.iterate(iterations);
            iterations++;

        }


        return bestSolution;
    }


    private class TabuElement<E> {
        private E element;
        private Integer restriction_time;
    }

}
