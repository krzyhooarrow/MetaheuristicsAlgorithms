package problems;

import java.util.LinkedList;
import java.util.Set;

public interface Problem<T> {
    public T generateInitialSolution();
    public boolean getStopCondition();
    public LinkedList<T> getNeighbourhood(T solution);
    public T getBestCandidate(Set<T> list);
    public float eval(T candidate);
    public void iterate(Integer iterator);
    public T substitute(T value1,T value2);
}
