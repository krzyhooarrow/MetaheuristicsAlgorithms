package algorithms.in_progress.extremal_optimization;

import java.util.LinkedList;

public interface ExtremalOptimizationProblem<T> {

    T generateInitialSolution();
    boolean getStopCondition();
    float eval(T candidate);
    void iterate(Integer iterator);
    LinkedList<T> getComponents(T solution);
    float componentEval(T currentSolution,T candidate);
    T replace(T currentSolution,T weakSolution,T randomSolution);

}
