package bfs;

import java.util.*;

public class Path {
    private Graph G;
    boolean[] visited;
    int source, destination;
    int[] pre;

    private List<Integer> order = new ArrayList<>();

    public Path(Graph g, int s, int d) {
        G = g;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre, -1);
        source = s;
        destination = d;
        bfs();
    }

    // time complexity is O(v+e)
    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (vertex == destination)
                return;
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

    public List<Integer> path() {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(destination))
            return res;
        int cur = destination;
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
        Path graphBFS = new Path(graph, 0, 6);
        System.out.println("path : " + graphBFS.path());
    }
}
