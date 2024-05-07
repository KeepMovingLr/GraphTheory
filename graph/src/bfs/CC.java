package bfs;

import java.util.*;

/**
 * component count
 */
public class CC {
    private Graph G;
    boolean[] visited;
    int count;

    private List<Integer> order = new ArrayList<>();

    public CC(Graph g) {
        G = g;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < g.getVertex(); i++) {
            if (!visited[i]) {
                count++;
                bfs(i);
            }
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

    public boolean isConnectedTo(int t) {
        return visited[t];
    }


    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        CC cc = new CC(graph);
        System.out.println("cc count " + cc.count);
    }
}
