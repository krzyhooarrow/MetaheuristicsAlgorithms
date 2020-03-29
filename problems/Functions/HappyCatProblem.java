package problems.Functions;

import algorithms.Configuration;
import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class HappyCatProblem implements TabuSearchProblem<Vector4D> {


    private Random generator = new Random();
    private Integer time = 0;

    @Override
    public Vector4D generateInitialSolution() {
        return new Vector4D
        (
                Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE/2F - generator.nextInt(Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE),
                Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE/2F - generator.nextInt(Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE),
                Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE/2F - generator.nextInt(Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE),
                Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE/2F - generator.nextInt(Configuration.HAPPY_CAT_SEARCH_SPACE_SIZE)
        );
    }

    @Override
    public boolean getStopCondition() {  return time>=Configuration.HAPPYCAT_MAX_TIME;
//            currentStep >= Configuration.HAPPY_CAT_ITERATIONS;
    }

    @Override
    public LinkedList<Vector4D> getNeighbourhood(Vector4D solution) {
        LinkedList<Vector4D> vectors = new LinkedList<>();

        vectors.add(new Vector4D(solution.x1-Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x2,solution.x3,solution.x4));
        vectors.add(new Vector4D(solution.x1+Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x2,solution.x3,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2-Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x3,solution.x4));
        vectors.add(new Vector4D(solution.x1,solution.x2+Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x3,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3-Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x4));
        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3+Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3,solution.x4-Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE));
        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3,solution.x4+Configuration.HAPPY_CAT_NEIGHBOUR_DISTANCE));

        return vectors;
    }

    @Override
    public Vector4D getBestCandidate(Set<Vector4D> list) {  return list.stream().min(Comparator.comparing(this::eval)).get();  }

    @Override
    public float eval(Vector4D candidate) {
        double norm_square =
                Math.pow(candidate.x1,2)+
                Math.pow(candidate.x2,2)+
                Math.pow(candidate.x3,2)+
                Math.pow(candidate.x4,2);

        return ((Double) (Math.pow(Math.pow(norm_square-4,2),0.125)+0.25*(0.5*norm_square+candidate.x1+candidate.x2+candidate.x3+candidate.x4) +0.5)).floatValue();
    }

    @Override
    public void iterate( Vector4D solution, Integer timer) {
      this.time = timer;
    }

    @Override
    public Vector4D substitute(Vector4D value1, Vector4D value2) {
        return value1;
    }

    @Override
    public boolean isBetterThan(Vector4D better, float coefficient, Vector4D worse) {
         return eval(better)*coefficient>eval(worse);
    }


}
