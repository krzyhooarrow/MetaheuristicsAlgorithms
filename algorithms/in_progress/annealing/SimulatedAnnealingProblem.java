package algorithms.in_progress.annealing;

import java.util.LinkedList;
import java.util.Set;

public interface SimulatedAnnealingProblem<T> {

    T generateInitialSolution();
    boolean getStopCondition();
    LinkedList<T> getNeighbourhood(T solution);
    T getBestCandidate(Set<T> list);
    float eval(T candidate);
    void iterate(Integer iterator);
    LinkedList<T> getRanks (T solution);

}
