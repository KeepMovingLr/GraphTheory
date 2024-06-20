package weightedGraph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * connected component
 */
public class CC {

    private WeightedGraph G;

    private int[] visited;

    // connection component count
    int ccCount;

    // time complexity O(V+E)
    public CC(WeightedGraph G) {
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
        Set<Integer> nodes = G.getAdjs(v).keySet();
        for (Integer adjNode : nodes) {
            if (visited[adjNode] == -1) {
                dfs(adjNode, ccId);
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

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src/weightedGraph/graph.txt");
        CC cc = new CC(graph);
        System.out.println(cc.ccCount);
        for (int ccId : cc.getVisited()) {
            System.out.println("ccId " + ccId);
        }
        System.out.println(cc.isConnected(0, 5));
        System.out.println(cc.isConnected(0, 4));

        ArrayList<Integer>[] components = cc.components();
        for (ArrayList<Integer> ccId : components) {
            System.out.println(ccId);
        }

    }

}
