import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CC {

    private Graph G;

    private boolean[] visited;

    // connection component count
    int ccCount;

    // time complexity O(V+E)
    public CC(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) {
                ccCount++;
                dfs(i);
            }
        }
    }


    private void dfs(int v) {
        visited[v] = true;
        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) dfs(adjEdge);
        }

    }

    public int getCcCount() {
        return ccCount;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        CC graphDFS = new CC(graph);
        System.out.println(graphDFS.ccCount);

    }

}
