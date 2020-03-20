package algorithms.implemented.tabu_search;


import algorithms.Configuration;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TabuSearch<U extends TabuSearchProblem<T>, T> {

    private Queue<T> tabuList = new LinkedList<>();

    public T solve(TabuSearchProblem<T> problem) {
        int iterations = 0;

        T bestSolution = problem.generateInitialSolution(), currentSolution = bestSolution , temporarySolution = bestSolution;

        Set<T> candidateList = new HashSet<>();

        while (!problem.getStopCondition()) {

            // variables for stream purpose (java requirements...)
            T fbestSolution = bestSolution;
            T fCurrentSolution = currentSolution;
            T fBestSolution = bestSolution;
            temporarySolution = currentSolution;
            // creating neighbourhood
            problem.getNeighbourhood(currentSolution).forEach(candidateFromNeighbourhood ->
                    {
                                // conditions for new candidate for solution:
                            if (tabuList.stream().noneMatch(tabuElement ->

                                // element is not in tabu list
                                tabuElement.equals(problem.substitute(candidateFromNeighbourhood, fCurrentSolution))
                                                    ||
                                 // element might be in tabu list but it's evaluation * coefficient is better than best one evaluation
                                 // optional
//                                !problem.isBetterThan(candidateFromNeighbourhood, Configuration.ALPHA_COEFFICIENT ,fBestSolution)
//                                                    ||
                                 // candidate is different than already found best
                                 candidateFromNeighbourhood.equals(fbestSolution)
                            ))

                            candidateList.add(candidateFromNeighbourhood);
                    });
            // if we already found local optimum we search in another search space
            if (candidateList.isEmpty())
            {
                currentSolution =  problem.generateInitialSolution();

                if (iterations>=Configuration.TABU_LIST_SIZE) tabuList.remove();

                continue;
            }
            else currentSolution = problem.getBestCandidate(candidateList);
            // adding current move to tabu list
            tabuList.add(problem.substitute(temporarySolution, currentSolution));
            // if new solution eval > best solution eval then best solution = new solution
            bestSolution = problem.eval(currentSolution) <= problem.eval(bestSolution) ?  currentSolution : bestSolution;
            // reducing tabu list
            if (iterations>=Configuration.TABU_LIST_SIZE) tabuList.remove();
            // clearing all and moving to next iteration
            candidateList.clear();
            // next iter
            problem.iterate(iterations,currentSolution);
            iterations++;
        }

        System.out.println("Iterations: " + iterations);

        return bestSolution;
    }
}
