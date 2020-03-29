package algorithms.implemented.particle_swarm;



import java.util.Set;

public interface ParticleSwarmOptimizationProblem<T> {



    Set<T> initializePopulation();
    boolean getStopCondition();
    float eval(T candidate);
    void iterate(T solution, Integer timer);
    T randomVelocity(T particle);
    T updateVelocity(T velocity, T globalBest, T bestSolution);
    T updatePosition(T currentPosition, T velocity);
}
