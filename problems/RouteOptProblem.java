//package problems;
//
//import algorithms.implemented.tabu_search.TabuSearchProblem;
//
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//
//public class RouteOptProblem implements TabuSearchProblem<RouteOptProblem.Point> {
//
//    private Random generator = new Random();
//
//    private int[][] map;
//
//
//    public RouteOptProblem(int[][] map) {
//        this.map = map;
//
//    }
//
//    @Override
//    public Point generateInitialSolution() {
//        Point point;
//        while (map[generator.nextInt(map.length)][generator.nextInt(map.length)]!=0)
//
//        return null;
//    }
//
//    @Override
//    public boolean getStopCondition() {
//        return false;
//    }
//
//    @Override
//    public LinkedList<Point> getNeighbourhood(Point solution) {
//        return null;
//    }
//
//    @Override
//    public Point getBestCandidate(Set<Point> list) {
//        return null;
//    }
//
//    @Override
//    public float eval(Point candidate) {
//        return 0;
//    }
//
//    @Override
//    public void iterate(Integer iterator) {
//
//    }
//
//    @Override
//    public Point substitute(Point value1, Point value2) {
//        return null;
//    }
//
//    @Override
//    public boolean isBetterThan(Point better, float coefficient, Point worse) {
//        return false;
//    }
//
//
//
//    public class Point{
//        public Point(Integer x, Integer y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        private Integer x;
//        private Integer y;
//    }
//}
