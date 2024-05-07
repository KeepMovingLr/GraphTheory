package bfs;

import java.util.*;

public class BipartitionDetection {
    private Graph G;
    boolean[] visited;

    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph g) {
        G = g;
        visited = new boolean[G.getVertex()];
        colors = new int[G.getVertex()];
        Arrays.fill(colors, -1);

        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i])
                if (!bfs(i)) {
                    isBipartite = false;
                    break;
                }
        }
    }

    // time complexity is O(v+e)
    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    colors[adj] = (colors[vertex] + 1) % 2;
                } else {
                    if (colors[adj] == colors[vertex])
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph.txt");
        BipartitionDetection graphBFS = new BipartitionDetection(graph);
        System.out.println("BFS isPartite " + graphBFS.isBipartite);
    }
}
