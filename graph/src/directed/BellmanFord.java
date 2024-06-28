package directed;


import java.util.Arrays;
import java.util.TreeMap;

public class BellmanFord {
    private WeightedGraph G;
    private int s; // source

    private int[] dis;

    private boolean hasNegativeCycle = false;

    public BellmanFord(WeightedGraph G, int s) {
        this.G = G;
        this.s = s;
        dis = new int[G.getVertex()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        // n - 1 relaxation
        for (int pass = 0; pass < G.getVertex() - 1; pass++) {
            for (int v = 0; v < G.getVertex(); v++) {
                TreeMap<Integer, Integer> adjs = G.getAdjs(v);
                for (int w : adjs.keySet()) {
                    if (dis[v] != Integer.MAX_VALUE && (dis[v] + adjs.get(w) < dis[w])) {
                        dis[w] = dis[v] + adjs.get(w);
                    }
                }
            }
        }

        // check if there is negative cycles
        for (int v = 0; v < G.getVertex(); v++) {
            TreeMap<Integer, Integer> adjs = G.getAdjs(v);
            for (int w : adjs.keySet()) {
                if (dis[v] != Integer.MAX_VALUE && (dis[v] + adjs.get(w) < dis[w])) {
                    hasNegativeCycle = true;
                }
            }
        }
    }

    public boolean hasNegCycle() {
        return hasNegativeCycle;
    }

    // if s and v is connected
    public boolean isConnectedTo(int v) {
        return dis[v] != Integer.MAX_VALUE;
    }

    // distance from v to s
    public int disTo(int v) {
        if (hasNegativeCycle)
            return -1;
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/directed/wg2.txt", true);
        BellmanFord bellmanFord = new BellmanFord(weightedGraph, 0);
        System.out.println(bellmanFord.disTo(1));
        System.out.println(bellmanFord.disTo(0));
        System.out.println(bellmanFord.disTo(2));
        System.out.println(bellmanFord.hasNegativeCycle);
    }

}
