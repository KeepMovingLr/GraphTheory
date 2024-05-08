package dfs;

import java.util.*;

/**
 * Single source path
 * 单源路径问题, 判断从source to destination, optimize by return boolean for dfs
 */
public class Path {

    private Graph G;

    private boolean[] visited;

    private int source;

    private int destination;

    private int[] pre;

    // time complexity O(V+E)
    public Path(Graph G, int s, int t) {
        this.G = G;
        this.source = s;
        this.destination = t;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre, -1);

        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == destination)
            return true;
        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) {
                if (dfs(adjEdge, v))
                    return true;
            }
        }
        return false;
    }

    public boolean isConnected(int t) {
        return visited[t];
    }

    /**
     * return the path from source to t
     *
     * @param t
     * @return
     */
    public List<Integer> path(int t) {
        List<Integer> path = new ArrayList<>();
        if (!isConnected(t)) return path;

        int cur = t;
        while (cur != source) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        Path singleSourcePath = new Path(graph, 0, 5);
        List<Integer> path = singleSourcePath.path(6);
        System.out.println(path);
        System.out.println(singleSourcePath.isConnected(4));
    }

}
