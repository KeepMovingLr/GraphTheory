import java.util.*;

/**
 * Single source path
 * 单源路径问题
 */
public class SingleSourcePath {

    private Graph G;

    private boolean[] visited;

    private int source;

    private int[] pre;

    // time complexity O(V+E)
    public SingleSourcePath(Graph G, int s) {
        this.G = G;
        this.source = s;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre, -1);

        dfs(s, s);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) {
                dfs(adjEdge, v);
            }
        }

    }

    public boolean isConnectedTo(int t) {
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
        if (!isConnectedTo(t)) return path;

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
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        List<Integer> path = singleSourcePath.path(6);
        System.out.println(path);
    }

}
