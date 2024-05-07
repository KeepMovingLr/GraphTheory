package bfs;

import java.util.*;

public class SingleSourcePath {
    private Graph G;
    boolean[] visited;
    int source;

    int[] pre;

    private List<Integer> order = new ArrayList<>();

    public SingleSourcePath(Graph g, int s) {
        G = g;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        source = s;
        bfs(s);
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

    public boolean isConnectedTo(int t) {
        return visited[t];
    }

    public List<Integer> path(int t) {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t))
            return res;
        int cur = t;
        while (cur != source) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(graph, 0);
        System.out.println("BFS order " + graphBFS.order);
        System.out.println("to 3 " + graphBFS.path(3));
    }
}
