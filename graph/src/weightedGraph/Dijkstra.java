package weightedGraph;

import java.util.Arrays;
import java.util.PriorityQueue;
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

        // Dijkstra time complexity O(E * log(E))
        boolean[] finished = new boolean[graph.getVertex()];
        // int[] a, a[0] is the vertex, a[1] is the distance from a[0] to source
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.add(new int[]{source, 0});
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            if (finished[next[0]])
                continue;
            finished[next[0]] = true;
            dis[next[0]] = next[1];
            TreeMap<Integer, Integer> adjs = graph.getAdjs(next[0]);
            for (Integer adjNode : adjs.keySet()) {
                if (finished[adjNode])
                    continue;
                pq.add(new int[]{adjNode, dis[next[0]] + adjs.get(adjNode)});
            }
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
