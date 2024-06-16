package bridge;

public class Edge {
    private int w;
    private int v;

    public Edge(int w, int v) {
        this.w = w;
        this.v = v;
    }

    @Override
    public String toString() {
        return w + " - " + v;
    }
}
