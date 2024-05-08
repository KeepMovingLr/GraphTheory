package bfs;

import java.util.*;

/**
 * Unweighted Single Source Shortest Path
 */
public class USSSPath {
    private Graph G;
    boolean[] visited;
    int source;

    int[] pre;

    int[] distance;

    private List<Integer> order = new ArrayList<>();

    public USSSPath(Graph g, int s) {
        G = g;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre, -1);
        distance = new int[G.getVertex()];
        Arrays.fill(distance, -1);
        source = s;
        bfs(s);
    }

    // time complexity is O(v+e)
    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        distance[s] = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            order.add(vertex);
            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    pre[adj] = vertex;
                    distance[adj] = distance[vertex] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        return visited[t];
    }


    /**
     * get the shortest dis from f to source
     * @param f
     * @return
     */
    public int dis(int f) {
        return distance[f];
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
        USSSPath usssPath = new USSSPath(graph , 0);
        System.out.println(usssPath.dis(4));
        System.out.println(usssPath.dis(5));
    }
}
