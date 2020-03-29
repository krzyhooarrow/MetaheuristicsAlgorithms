package algorithms.implemented.particle_swarm;

public class Particle<U> {

    U currentPosition;
    U velocity;
    U bestSolution;

    public Particle(U currentPosition, U velocity, U bestSolution) {
        this.currentPosition = currentPosition;
        this.velocity = velocity;
        this.bestSolution = bestSolution;
    }

    public U getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(U currentPosition) {
        this.currentPosition = currentPosition;
    }

    public U getVelocity() {
        return velocity;
    }

    public U getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(U bestSolution) {
        this.bestSolution = bestSolution;
    }

    public void setVelocity(U velocity) {
        this.velocity = velocity;
    }
}