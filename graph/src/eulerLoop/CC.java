package eulerLoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * connected component
 */
public class CC {

    private Graph G;

    private int[] visited;

    // connection component count
    int ccCount;

    // time complexity O(V+E)
    public CC(Graph G) {
        this.G = G;
        visited = new int[G.getVertex()];
        Arrays.fill(visited, -1);
        for (int i = 0; i < G.getVertex(); i++) {
            if (visited[i] == -1) {
                dfs(i, ccCount);
                ccCount++;
            }
        }
    }


    private void dfs(int v, int ccId) {
        visited[v] = ccId;
        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer adjEdge : adjEdges) {
            if (visited[adjEdge] == -1) {
                dfs(adjEdge, ccId);
            }
        }
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return visited[vertex1] == visited[vertex2];
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.getVertex(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public int getCcCount() {
        return ccCount;
    }

    public int[] getVisited() {
        return visited;
    }

}
