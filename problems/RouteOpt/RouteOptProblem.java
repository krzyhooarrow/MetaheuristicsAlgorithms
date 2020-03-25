package problems.RouteOpt;

import algorithms.Configuration;
import algorithms.implemented.tabu_search.TabuSearchProblem;

import java.util.*;

public class RouteOptProblem implements TabuSearchProblem<RouteOptProblem.Point> {

    private Random generator = new Random();
    private Integer currentStep = 0;

    private int[][] map;
    private int N;  // map width
    private int M;  // map height;

    private int startingX;
    private int startingY;

    private boolean alreadyFoundWallLeft = false;
    private boolean alreadyFoundWallLeft_last = false;
    private boolean alreadyFoundWallTop = false;
    private boolean alreadyFoundWallTop_last = false;
    private boolean alreadyFoundWallDown = false;
    private boolean alreadyFoundWallRight = false;
    private Point solution;

    private Integer time = 0;

    public RouteOptProblem(int[][] map) {
        this.map = map;
        this.N = map.length;
        this.M = map[0].length;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 5) {
                    startingX = i;
                    startingY = j;
                }
        solution = new Point(startingX, startingY, 5);


    }

    @Override
    public Point generateInitialSolution() {
        return new Point(startingX, startingY, 5);
    }

    @Override
    public boolean getStopCondition() {
        return  time >= Configuration.ROUTE_OPT_MAX_TIME
//                currentStep >= Configuration.ROUTE_OPT_MAX_ITERATIONS
                ||
                solution.value == 8
                ||
                (M >= N ? (solution.x ==1 && solution.y ==startingY && alreadyFoundWallDown)
                : (solution.x == startingX  && solution.y == M-2 && alreadyFoundWallLeft ))
                ;
    }

    @Override
    public LinkedList<Point> getNeighbourhood(Point solution) {
        LinkedList<Point> points = new LinkedList<>();

        points.add(new Point(solution.x + 1, solution.y, map[solution.x + 1][solution.y]));
        points.add(new Point(solution.x - 1, solution.y, map[solution.x - 1][solution.y]));
        points.add(new Point(solution.x, solution.y + 1, map[solution.x][solution.y + 1]));
        points.add(new Point(solution.x, solution.y - 1, map[solution.x][solution.y - 1]));

        return points;
    }

    @Override
    public Point getBestCandidate(Set<Point> list) {

        return  list.stream().anyMatch(point -> point.value == 8) ?
                list.stream().filter(point -> point.value == 8).findFirst().get() :

                M >= N ?
                        getCandidateWithDominatingWidth(list)
                        :
                        getCandidateWithDominatingHeight(list);
    }

    private Point getCandidateWithDominatingWidth(Set<Point> list) {

        return alreadyFoundWallLeft ?
                alreadyFoundWallTop ?
                        alreadyFoundWallRight ?
                                alreadyFoundWallDown ?
                                        alreadyFoundWallLeft_last ?

                                                getUpperCandidate(list) : getLeftCandidate(list) : getDownCandidate(list) :
                                getRightCandidate(list) : getUpperCandidate(list) : getLeftCandidate(list);
    }

    private Point getCandidateWithDominatingHeight(Set<Point> list) {

        return alreadyFoundWallTop ?
                alreadyFoundWallRight ?
                        alreadyFoundWallDown ?
                                alreadyFoundWallLeft ?
                                        alreadyFoundWallTop_last ?

                                                getRightCandidate(list) : getUpperCandidate(list) : getLeftCandidate(list) : getDownCandidate(list) :
                        getRightCandidate(list) : getUpperCandidate(list);
    }


    private Point getRightCandidate(Set<Point> list) {
        if (list.stream().max(Comparator.comparing(point -> point.x)).get().value == 1) {
            alreadyFoundWallRight = true;
            return getDownCandidate(list);
        } else
            return list.stream().max(Comparator.comparing(point -> point.x)).get();
    }

    private Point getDownCandidate(Set<Point> list) {
        if (list.stream().min(Comparator.comparing(point -> point.y)).get().value == 1) {
            alreadyFoundWallDown = true;
            return getLeftCandidate(list);
        } else
            return list.stream().min(Comparator.comparing(point -> point.y)).get();
    }

    private Point getLeftCandidate(Set<Point> list) {
        if (list.stream().min(Comparator.comparing(point -> point.x)).get().value == 1 ) {
            alreadyFoundWallLeft = true;
            return getUpperCandidate(list);
        } else
            return list.stream().min(Comparator.comparing(point -> point.x)).get();
    }

    private Point getUpperCandidate(Set<Point> list) {
        if (list.stream().max(Comparator.comparing(point -> point.y)).get().value == 1) {
            alreadyFoundWallTop = true;
            return getRightCandidate(list);
        } else
            return list.stream().max(Comparator.comparing(point -> point.y)).get();
    }


    @Override
    public float eval(Point candidate) {
        return candidate.value == 8 ? 1 : 0;
    }

    @Override
    public void iterate(Integer iterator, Point solution, Integer timer) {
        this.currentStep = iterator;
        this.solution = solution;
        this.time = timer;
    }

    @Override
    public Point substitute(Point value1, Point value2) {
        return value1;
    }

    @Override
    public boolean isBetterThan(Point better, float coefficient, Point worse) {
        return false;
    }


    public class Point {
        public Point(Integer x, Integer y, Integer value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public Integer x;
        public Integer y;
        public Integer value;

        @Override
        public boolean equals(Object obj) {
            Point compared = (Point) obj;
            return this.x.equals(compared.x) && this.y.equals(compared.y);
        }

        @Override
        public String toString() {
            return "{x:" + x + ",y:" + y + ", v:" + value + '}';
        }
    }


}
