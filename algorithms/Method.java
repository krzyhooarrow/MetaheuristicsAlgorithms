package algorithms;

import problems.Problem;

import java.util.LinkedList;

public interface Method<T,E> {
    LinkedList<T> solve(Problem<E> problem);
}
