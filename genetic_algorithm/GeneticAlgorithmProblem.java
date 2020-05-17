package algorithms.implemented.genetic_algorithm;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public interface GeneticAlgorithmProblem<T> {

    List<T> initializePopulation(int size);
    boolean getStopCondition();
    double eval(T candidate);
    void iterate(T solution, Integer timer);
    List<T> selectParents(List<T> population);
    T crossover(T parent1, T parent2);
    T mutate(T child);
}
