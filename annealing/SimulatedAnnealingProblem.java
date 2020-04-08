package algorithms.implemented.annealing;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public interface SimulatedAnnealingProblem<T> {

    T generateInitialSolution();
    boolean getStopCondition();
    Collection<T> getNeighbourhood(T solution);
    T getBestCandidate(Collection<T> list);
    double eval(T candidate);
    void iterate( T solution, Integer timer);
}
