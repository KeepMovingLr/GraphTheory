import java.util.Arrays;
import java.util.TreeSet;

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
        visited[v] = ccCount;
        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (visited[adjEdge] == -1) {
                dfs(adjEdge, ccId);
            }
        }
    }

    public boolean inSameComponent(int vertex1 , int vertex2) {
        return visited[vertex1] == visited[vertex2];
    }

    public int getCcCount() {
        return ccCount;
    }

    public int[] getVisited() {
        return visited;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        CC graphDFS = new CC(graph);
        System.out.println(graphDFS.ccCount);
        for (int ccId : graphDFS.getVisited()) {
            System.out.println("ccId " + ccId);
        }
        System.out.println(graphDFS.inSameComponent(0 , 5));
        System.out.println(graphDFS.inSameComponent(0 , 4));
    }

}
