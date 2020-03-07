package graphs;

public class Edge {

    private Integer from;
    private Integer to;
    private Float weight;

    public Edge(Integer from, Integer to, Float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return '['+from.toString() +','+ to.toString() +':'+ weight.toString()+']';
    }
}
