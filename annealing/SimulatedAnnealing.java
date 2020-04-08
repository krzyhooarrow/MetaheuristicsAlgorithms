package algorithms.implemented.annealing;

import algorithms.Configuration;

import java.util.*;

public class SimulatedAnnealing<U extends SimulatedAnnealingProblem<T>, T> {

    private final Random generator = new Random();

    public T solve(SimulatedAnnealingProblem<T> problem) {

        double temporarySolutionEval, temperature = Configuration.STARTING_TEMPERATURE;

        int timer = Math.toIntExact(System.currentTimeMillis() / 1000);

        T bestSolution = problem.generateInitialSolution(), currentSolution = bestSolution,temporarySolution;

        while (!problem.getStopCondition()) {

            temporarySolution = problem.getBestCandidate(problem.getNeighbourhood(currentSolution));

            temporarySolutionEval = problem.eval(temporarySolution);

            if (temporarySolutionEval <= problem.eval(currentSolution)) {
                currentSolution = temporarySolution;
                if (temporarySolutionEval <= problem.eval(bestSolution))
                    bestSolution = temporarySolution;
            }
            else if (acceptancePropability(problem.eval(currentSolution), problem.eval(temporarySolution), temperature) > generator.nextFloat())
                currentSolution = temporarySolution;

            temperature *=Configuration.TEMPERATURE_REDUCTION;

            problem.iterate(currentSolution,Math.toIntExact(System.currentTimeMillis() / 1000)-timer);
        }
        return bestSolution;
    }

    private Double acceptancePropability(double evalI, double evalJ, double temperature) {
        return  Math.exp((evalI - evalJ)/ (evalI * temperature));
    }

}
