package algorithms.implemented.genetic_algorithm;

import algorithms.implemented.annealing.SimulatedAnnealingProblem;

import java.util.*;

public class GeneticAlgorithm<U extends GeneticAlgorithmProblem<T>, T> {

    private int populationSize= 50;

    public T solve(GeneticAlgorithmProblem<T> problem) {

        int timer = Math.toIntExact(System.currentTimeMillis() / 1000);

        List<T> parents, population = problem.initializePopulation(populationSize);

        T childFirst,childSecond,currentSolution,bestSolution = population.stream().min(Comparator.comparing(problem::eval)).get();

        while (!problem.getStopCondition()){

            parents = problem.selectParents(population);

            for (int i = 0 ; i < parents.size()-1; i ++)
            {
            childFirst = problem.crossover(parents.get(i),parents.get(i+1));  childSecond = childFirst;

            population.add( problem.eval(childFirst = problem.mutate(childFirst)) <= problem.eval(childSecond = problem.mutate(childSecond)) ? childFirst : childSecond );
            }

            childFirst = problem.crossover(parents.get(parents.size()-1),parents.get(parents.size()-2)) ; childSecond = childFirst;
            population.add( problem.eval(childFirst = problem.mutate(childFirst)) <= problem.eval(childSecond = problem.mutate(childSecond)) ? childFirst : childSecond );

            parents.forEach(population::remove);

            if (problem.eval(currentSolution = population.stream().min(Comparator.comparing(problem::eval)).get()) <= problem.eval(bestSolution))
                bestSolution = currentSolution;



            if (new Random().nextDouble()<=0.01){
            population.sort(Comparator.comparing(problem::eval));
            population = population.subList(0,populationSize);
            }

            problem.iterate(bestSolution, Math.toIntExact(System.currentTimeMillis() / 1000) - timer);
        }
        return bestSolution;
    }
}