package cutPoint;

import java.util.*;

/**
 *
 */
public class FindCutPoint {

    private Graph G;

    private boolean[] visited;

    /**
     * store the visit order of each vertex
     */
    private int[] order;
    /**
     * store the lowest order of the vertex can reach
     */
    private int[] low;
    private int cnt;

    private Set<Integer> res;


    // time complexity O(V+E)
    public FindCutPoint(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        order = new int[G.getVertex()];
        low = new int[G.getVertex()];
        cnt = 0;
        res = new HashSet<>();

        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) dfs(i, i);
        }
    }

    private void dfs(int v, int parent) {
        // visit v operation
        visited[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;

        int child = 0;

        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer w : adjEdges) {
            if (!visited[w]) {
                child++;
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (v != parent && low[w] >= order[v]) {
                    res.add(v);
                }
                if (v == parent && child > 1) {
                    res.add(v);
                }
            } else {
                if (w != parent) {
                    low[v] = Math.min(low[v], low[w]);
                }
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        FindCutPoint findCutPoint = new FindCutPoint(graph);
        System.out.println(findCutPoint.res);
    }

}
