package problems.RouteOpt;



public class Step {
    public static void NORTH(RouteOptProblem.Point point){
        point.y++;
    }
    public static void SOUTH(RouteOptProblem.Point point){
        point.y--;
    }
    public static void EAST(RouteOptProblem.Point point){
        point.x++;
    }
    public static void WEST(RouteOptProblem.Point point){
        point.x--;
    }
}
