package algorithms.implemented.annealing;

import algorithms.Configuration;

import java.util.*;

public class SimulatedAnnealing<U extends SimulatedAnnealingProblem<T>, T> {

    private final Random generator = new Random();

    public T solve(SimulatedAnnealingProblem<T> problem) {

        float bestSolutionEval, currentSolutionEval,temporarySolutionEval, temperature = Configuration.STARTING_TEMPERATURE;

        int timer = Math.toIntExact(System.currentTimeMillis() / 1000);

        T bestSolution = problem.generateInitialSolution(), currentSolution = bestSolution,temporarySolution;

        while (!problem.getStopCondition()) {

            temporarySolution = problem.getBestCandidate(new HashSet<>(problem.getNeighbourhood(currentSolution)));

            currentSolutionEval = problem.eval(currentSolution);
            bestSolutionEval = problem.eval(bestSolution);
            temporarySolutionEval = problem.eval(temporarySolution);

            if (temporarySolutionEval <= currentSolutionEval) {

                currentSolution = temporarySolution;
                if (temporarySolutionEval <= bestSolutionEval)
                    bestSolution = temporarySolution;

            }
            else if (acceptancePropability(problem.eval(currentSolution), problem.eval(temporarySolution), temperature) > generator.nextFloat())
                currentSolution = temporarySolution;

            temperature *=Configuration.TEMPERATURE_REDUCTION;

            problem.iterate(currentSolution,Math.toIntExact(System.currentTimeMillis() / 1000)-timer);
        }
        return bestSolution;
    }

    private Float acceptancePropability(Float evalI, Float evalJ, Float temperature) {
        return ((Double) Math.exp((evalI - evalJ)/ (evalI * temperature))).floatValue();
    }

}
