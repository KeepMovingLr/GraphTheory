package bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * In graph theory, a bridge (also known as a cut-edge or cut-arc) is an edge in a graph whose removal increases the number of connected components of the graph.<br>
 * In other words, if an edge is a bridge, removing it will disconnect the graph. <br>
 * Bridges are important in the study of graph connectivity because they represent a single point of failure in the network. <br>
 * <br>
 * Here are some key points about bridges in a graph: <br>
 * <br>
 * Identification: <br>
 * To identify bridges in a graph, one common algorithm is to use depth-first search (DFS).<br>
 * During DFS traversal, we keep track of the discovery time and the lowest point reachable from each vertex. <br>
 * <br>
 * Articulation Points: <br>
 * Bridges are closely related to articulation points (or cut vertices).
 * An articulation point is a vertex whose removal increases the number of connected components in the graph.<br>
 * The endpoints of bridges are articulation points, but not all articulation points are endpoints of bridges.<br>
 * <br>
 * Graph Types:<br>
 * Bridges can only exist in undirected graphs. <br>
 * In directed graphs, the concept of bridges is not typically used in the same way. <br>
 * <br>
 */
public class FindBridge {

    private Graph G;

    private boolean[] visited;

    /**
     * store the visit order of each vertex
     */
    private int[] order;
    /**
     * store the lowest order of the vertex can reach
     */
    private int[] low;
    private int cnt;

    private List<Edge> bridges;

    // time complexity O(V+E)
    public FindBridge(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        order = new int[G.getVertex()];
        low = new int[G.getVertex()];
        cnt = 0;
        bridges = new ArrayList<>();

        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) dfs(i, i);
        }
    }

    private void dfs(int v, int parent) {
        // visit v operation
        visited[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;

        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer w : adjEdges) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > order[v]) {
                    // v - w is bridge
                    bridges.add(new Edge(v , w));
                }
            } else {
                if (w != parent) {
                    low[v] = Math.min(low[v], low[w]);
                }
            }
        }
    }

    public List<Edge> getBridges() {
        return bridges;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        FindBridge findBridge = new FindBridge(graph);
        System.out.println(findBridge.getBridges());
    }

}
