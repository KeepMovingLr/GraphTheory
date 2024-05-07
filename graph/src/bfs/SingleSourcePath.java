package bfs;

import java.util.*;

public class SingleSourcePath {
    private Graph G;
    boolean[] visited;

    int[] pre;

    private List<Integer> order = new ArrayList<>();

    public SingleSourcePath(Graph g) {
        G = g;
        visited = new boolean[G.getVertex()];

        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i])
                bfs(i);
        }
    }

    // time complexity is O(v+e)
    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            order.add(vertex);
            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    pre[adj] = vertex;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(graph);
        System.out.println("BFS order " + graphBFS.order);
    }
}
