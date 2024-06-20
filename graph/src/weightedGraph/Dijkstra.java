package weightedGraph;

import java.util.Arrays;
import java.util.TreeMap;

public class Dijkstra {
    private WeightedGraph graph;
    private int source;
    private int[] dis;

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        dis = new int[graph.getVertex()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        // Dijkstra
        boolean[] finished = new boolean[graph.getVertex()];
        finished[source] = true;
        dis[source] = 0;
        int cur = source;

        while (cur != -1) {
            TreeMap<Integer, Integer> adjs = graph.getAdjs(cur);
            int min = -1;
            int minVal = Integer.MAX_VALUE;
            for (Integer adjNode : adjs.keySet()) {
                if (finished[adjNode])
                    continue;
                dis[adjNode] = Math.min(dis[adjNode], dis[cur] + adjs.get(adjNode));
                if (adjs.get(adjNode) < minVal) {
                    min = adjNode;
                    minVal = adjs.get(adjNode);
                }
            }
            cur = min;
            if (cur != -1)
                finished[cur] = true;
        }

    }

    public boolean isConnectedTo(int v) {
        return dis[v] != Integer.MAX_VALUE;
    }
    public int getDis(int destination) {
        return dis[destination];
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph2.txt");
        Dijkstra dijkstra = new Dijkstra(weightedGraph, 0);
        System.out.println(dijkstra.getDis(1));
        for (int di : dijkstra.dis) {
            System.out.println(di);
        }
    }


}
