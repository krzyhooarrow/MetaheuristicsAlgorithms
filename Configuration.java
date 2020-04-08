package algorithms;

// coefficients list
public class Configuration {

    // CONST FOR GRIEWANK
    public static final int GRIEWANK_SPACE_SIZE = 20;
    public static final float GRIEWANK_NEIGHBOUR_DISTANCE = 0.01F;
    public static int GRIEWANK_MAX_TIME;

    // CONST FOR HAPPYCAT
    public static final int HAPPY_CAT_SEARCH_SPACE_SIZE = 20;
    public static final float HAPPY_CAT_NEIGHBOUR_DISTANCE = 0.0000001F;
    public static int HAPPYCAT_MAX_TIME;

    // CONST FOR TSP
    public static final int PERMUTATIONS_SIZE = 2;
    public static int TSP_MAX_TIME;

    // CONST FOR ROUTE_OPT
    public static int ROUTE_OPT_MAX_TIME;

    // CONST FOR SALOMON
    public static final double SALOMON_NEIGHBOUR_DISTANCE = 0.00001;
    public static int SALOMON_MAX_TIME;


    // ------------------------------------------------------
    // CONST FOR HEURISTICS
    // ------------------------------------------------------
    // Tabu
    public static final Integer TABU_LIST_SIZE = 5;
    // Annealing
    public static final float STARTING_TEMPERATURE = 10F;
    public static final float TEMPERATURE_REDUCTION = 0.999999F;
    // Extremal Opt
    public static final float ALPHA_COEFFICIENT = 1.00005F;
    public static final float TAU = 1.2F;


}
