package bfs;

import java.util.*;

public class GraphBFS {
    private Graph G;
    boolean[] visited;

    private List<Integer> order = new ArrayList<>();

    public GraphBFS(Graph g) {
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
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            order.add(vertex);
            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println("BFS order " + graphBFS.order);
    }
}
