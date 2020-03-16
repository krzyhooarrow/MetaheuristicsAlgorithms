package algorithms.in_progress.extremal_optimization;

import algorithms.Configuration;

import java.util.*;
import java.util.stream.Collectors;


public class ExtremalOptimization<U extends ExtremalOptimizationProblem<T>, T> {

    private final float TAU = Configuration.TAU;

    public T solve(ExtremalOptimizationProblem<T> problem){

        int iterations = 0;

        T bestSolution = problem.generateInitialSolution(), currentSolution = bestSolution ,candidateSolution, weakComponent, replacementComponent;

        Map<T,Float> componentsEvaluationList;

        while (!problem.getStopCondition()){

            // evaluating components of current solution
            componentsEvaluationList = evaluateList(problem,currentSolution);

            // selecting component with worst performance
            weakComponent = findWeakestComponent(componentsEvaluationList);

            // selecting random component different from weak component
            replacementComponent = findReplacementComponent(componentsEvaluationList,weakComponent);


//            lambda = problem.fitness()

            candidateSolution = problem.replace(currentSolution,weakComponent,replacementComponent);


            if (problem.eval(candidateSolution) <= problem.eval(bestSolution)) {
                currentSolution = candidateSolution;
                bestSolution = candidateSolution;
            }

            problem.iterate(iterations);
            iterations++;
        }



        return bestSolution;
    }

    private Map<T,Float> evaluateList(ExtremalOptimizationProblem<T> problem, T currentSolution){
        return problem.getComponents(currentSolution)
                .stream()
                .collect(Collectors.toMap(
                        component -> component,
                        component -> problem.componentEval(currentSolution,component
                        )));
    }

    private T findWeakestComponent(Map<T,Float> componentsEvaluationList){
        return componentsEvaluationList.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    private T findReplacementComponent(Map<T,Float> componentsEvaluationList , T weakComponent){
        return componentsEvaluationList
                .entrySet()
                .stream()
                .filter(component -> component.getKey()!= weakComponent)
                .min(Map.Entry.comparingByValue()).get().getKey();
//                    .findAny().get().getKey();
    }



}
