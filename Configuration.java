package algorithms;

// coefficients list
public class Configuration {

    // CONST FOR GRIEWANK
    public static final int GRIEWANK_SPACE_SIZE = 20;
    public static final float GRIEWANK_NEIGHBOUR_DISTANCE = 0.01F;
    public static final int ANTS_SIZE = 5;
    public static  int DICT_MAX_TIME = 5;

    public static int YANG_MAX_TIME = 50;
    public static Integer MATRIX_MAX_TIME ;
    public static int GRIEWANK_MAX_TIME;

    // CONST FOR HAPPYCAT
    public static final int HAPPY_CAT_SEARCH_SPACE_SIZE = 20;
    public static final float HAPPY_CAT_NEIGHBOUR_DISTANCE = 0.0000001F;
    public static int HAPPYCAT_MAX_TIME;

    // CONST FOR ROUTE_OPT
    public static int ROUTE_OPT_MAX_TIME;

    // CONST FOR SALOMON
    public static  double SALOMON_NEIGHBOUR_DISTANCE = 0.1;
    public static int SALOMON_MAX_TIME;


    // ------------------------------------------------------
    // CONST FOR HEURISTICS
    // ------------------------------------------------------
    // Tabu
    public static final Integer TABU_LIST_SIZE = 5;
    // Annealing
    public static final float STARTING_TEMPERATURE = 10F;
    public static  float TEMPERATURE_REDUCTION = 1F;


    public static final float TAU = 1.2F;


}

