import java.util.*;

/**
 * Single source path
 * 单源路径问题
 */
public class CycleDetection {

    private Graph G;

    private boolean[] visited;

    private int[] pre;

    private boolean hasCycle;

    // time complexity O(V+E)
    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre, -1);
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) {
                dfs(i, i);
            }
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) {
                dfs(adjEdge, v);
            } else {
                if (pre[adjEdge] != v)
                    hasCycle = true;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        CycleDetection singleSourcePath = new CycleDetection(graph);
    }

}
