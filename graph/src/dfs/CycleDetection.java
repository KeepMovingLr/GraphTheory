package dfs;

import java.util.*;

/**
 * 检查是否有环
 */
public class CycleDetection {

    private Graph G;

    private boolean[] visited;

    private boolean hasCycle;

    // time complexity O(V+E)
    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) {
                if (dfs(i, i))
                    break;
            }
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) {
                if (dfs(adjEdge, v)) {
                    return true;
                }
            } else {
                if (adjEdge != parent) {
                    hasCycle = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph4.txt");
        CycleDetection singleSourcePath = new CycleDetection(graph);
        System.out.println(singleSourcePath.hasCycle);
    }

}
