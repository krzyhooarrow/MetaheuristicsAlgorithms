package algorithms.implemented.annealing;

import java.util.LinkedList;
import java.util.Set;

public interface SimulatedAnnealingProblem<T> {

    T generateInitialSolution();
    boolean getStopCondition();
    LinkedList<T> getNeighbourhood(T solution);
    T getBestCandidate(Set<T> list);
    float eval(T candidate);
    void iterate( T solution, Integer timer);
    LinkedList<T> getRanks (T solution);

}
