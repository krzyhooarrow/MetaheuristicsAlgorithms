package algorithms.implemented.particle_swarm;



import java.util.Set;

public interface ParticleSwarmOptimizationProblem<T> {



    Set<T> initializePopulation(int size);
    boolean getStopCondition();
    float eval(T candidate);
    void iterate(T solution, Integer timer);
    T randomVelocity(T particle);
    void updateVelocity(Particle<T> current, T bestSolution);
    void updatePosition(Particle<T> current);
}
