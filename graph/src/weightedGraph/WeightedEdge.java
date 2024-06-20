package weightedGraph;

public class WeightedEdge {
    private int w;
    private int v;

    private int weight;

    public WeightedEdge(int w, int v, int weight) {
        this.w = w;
        this.v = v;
        this.weight = weight;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return w + " - " + v + " - " + weight;
    }
}
