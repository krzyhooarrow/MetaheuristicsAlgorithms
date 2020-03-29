package algorithms.implemented.particle_swarm;

import java.util.*;
import java.util.stream.Collectors;

public class ParticleSwarm<U extends ParticleSwarmOptimizationProblem<T>, T> {

    public T solve(ParticleSwarmOptimizationProblem<T> problem) {

        // should create set of starting solutions
        List<Particle<T>> swarm = problem.initializePopulation()
                .stream()
                .map(particle -> new Particle<>(particle, problem.randomVelocity(particle), particle))
                .collect(Collectors.toCollection(LinkedList::new));


        T bestSolution = swarm
                .stream()
                .min(Comparator.comparing(particle -> problem.eval(particle.currentPosition)))
                .get().currentPosition;


        while (!problem.getStopCondition()) {

            T globalBest = bestSolution;

            for (Particle<T> particle : swarm) {

                particle.velocity = problem.updateVelocity(particle.velocity, globalBest, particle.getBestSolution());

                particle.currentPosition = problem.updatePosition(particle.currentPosition, particle.velocity);

                if (problem.eval(particle.bestSolution) > problem.eval(particle.currentPosition)) {
                    particle.bestSolution = particle.currentPosition;

                    if (problem.eval(particle.currentPosition) < problem.eval(globalBest))
                        globalBest = particle.currentPosition;
                }
            }
        }
        return bestSolution;
    }
}