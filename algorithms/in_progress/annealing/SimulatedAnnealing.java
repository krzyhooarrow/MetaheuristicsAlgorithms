package algorithms.in_progress.annealing;

import algorithms.Configuration;

import java.util.*;

public class SimulatedAnnealing<U extends SimulatedAnnealingProblem<T>, T> {

    private final Random generator = new Random();

    public T solve(SimulatedAnnealingProblem<T> problem) {

        float bestSolutionEval, currentSolutionEval,temporarySolutionEval, temperature = Configuration.STARTING_TEMPERATURE;

        int iterations = 0;

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

            temperature = modifyTemperature(iterations);

            problem.iterate(iterations);
            iterations++;
        }
        return bestSolution;
    }

    private float modifyTemperature(Integer temperature) {
        return temperature*0.99F;
    }

    private Float acceptancePropability(Float evalI, Float evalJ, Float temperature) {
        return ((Double) Math.exp((evalI - evalJ) / temperature)).floatValue();
    }

}
