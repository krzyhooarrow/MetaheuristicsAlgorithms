package problems.Functions;

import algorithms.Configuration;
import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class GriewankProblem implements TabuSearchProblem<Vector4D> {

    private Integer currentStep = 0;
    private Random generator = new Random();

    private Integer time = 0;

    @Override
    public Vector4D generateInitialSolution() {
        return new Vector4D
                (
                        Configuration.GRIEWANK_SPACE_SIZE/2 - generator.nextInt(Configuration.GRIEWANK_SPACE_SIZE),
                        Configuration.GRIEWANK_SPACE_SIZE/2 - generator.nextInt(Configuration.GRIEWANK_SPACE_SIZE),
                        Configuration.GRIEWANK_SPACE_SIZE/2 - generator.nextInt(Configuration.GRIEWANK_SPACE_SIZE),
                        Configuration.GRIEWANK_SPACE_SIZE/2 - generator.nextInt(Configuration.GRIEWANK_SPACE_SIZE)
                );
    }

    @Override
    public boolean getStopCondition() {  return Configuration.GRIEWANK_MAX_TIME <= time;
//            currentStep >= Configuration.GRIEWANK_ITERATIONS;
    }

    @Override
    public LinkedList<Vector4D> getNeighbourhood(Vector4D solution) {
        LinkedList<Vector4D> vectors = new LinkedList<>();

        vectors.add(new Vector4D(solution.x1-Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x2,solution.x3,solution.x4));
        vectors.add(new Vector4D(solution.x1+Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x2,solution.x3,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2-Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x3,solution.x4));
        vectors.add(new Vector4D(solution.x1,solution.x2+Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x3,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3-Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x4));
        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3+Configuration.GRIEWANK_NEIGHBOUR_DISTANCE,solution.x4));

        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3,solution.x4-Configuration.GRIEWANK_NEIGHBOUR_DISTANCE));
        vectors.add(new Vector4D(solution.x1,solution.x2,solution.x3,solution.x4+Configuration.GRIEWANK_NEIGHBOUR_DISTANCE));

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

        return ((Double) (1+ norm_square/4000 -

                Math.cos(candidate.x1)*
                Math.cos(candidate.x2/Math.sqrt(2)) *
                Math.cos(candidate.x3/Math.sqrt(3)) *
                Math.cos(candidate.x4/Math.sqrt(4))

                )).floatValue();
    }

    @Override
    public void iterate(Integer iterator, Vector4D solution, Integer timer) {
        this.currentStep = iterator; this.time = timer;
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