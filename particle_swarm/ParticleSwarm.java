package algorithms.implemented.particle_swarm;

import java.util.*;
import java.util.stream.Collectors;

public class ParticleSwarm<U extends ParticleSwarmOptimizationProblem<T>, T> {

    private final int swarmSize = 100;

    public T solve(ParticleSwarmOptimizationProblem<T> problem) {

        int timer = Math.toIntExact(System.currentTimeMillis() / 1000);

        Particle<T> currentParticle;

        // should create set of starting solutions
        Queue<Particle<T>> swarm = problem.initializePopulation(swarmSize)
                .stream()
                .map(particle -> new Particle<>(particle, problem.randomVelocity(particle), particle))
                .collect(Collectors.toCollection(LinkedList::new));

        T bestSolution = swarm
                .stream()
                .min(Comparator.comparing(particle -> problem.eval(particle.currentPosition)))
                .get().currentPosition;


        while (!problem.getStopCondition()) {

            for (int i = 0; i < swarm.size(); i++) {

                currentParticle = swarm.poll();

                problem.updateVelocity(currentParticle, bestSolution);
                problem.updatePosition(currentParticle);

                if (problem.eval(currentParticle.bestSolution) >= problem.eval(currentParticle.currentPosition)) {
                    currentParticle.bestSolution = currentParticle.currentPosition;

                    if (problem.eval(currentParticle.bestSolution) <= problem.eval(bestSolution)) {
                        bestSolution = currentParticle.currentPosition;
                    }
                }

                swarm.add(currentParticle);
            }
            problem.iterate(bestSolution, Math.toIntExact(System.currentTimeMillis() / 1000) - timer);

        }
        return bestSolution;
    }
}
