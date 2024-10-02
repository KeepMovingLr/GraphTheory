package directed;

import java.util.TreeSet;

/**
 * 检查是否有环
 * Very Important!!!
 */
public class DirectedCycleDetection {

    private Graph G;

    private boolean[] visited;

    private boolean[] onPath;

    private boolean hasCycle;

    // time complexity O(V+E)
    public DirectedCycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        onPath = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) {
                if (dfs(i)) break;
            }
        }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        onPath[v] = true;
        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer adjVertex : adjEdges) {
            if (!visited[adjVertex]) {
                if (dfs(adjVertex)) {
                    return true;
                }
            } else {
                if (onPath[adjVertex]) {
                    hasCycle = true;
                    return true;
                }
            }
        }
        onPath[v] = false;
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph4.txt");
        DirectedCycleDetection singleSourcePath = new DirectedCycleDetection(graph);
        System.out.println(singleSourcePath.hasCycle);
    }

}
