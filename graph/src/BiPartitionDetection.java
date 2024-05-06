import java.util.Arrays;
import java.util.TreeSet;

/**
 * 二分图检测
 */
public class BiPartitionDetection {

    private Graph G;

    private boolean[] visited;

    // contains 0 or 1
    private int[] colors;

    private boolean isBiPartition = true;

    // time complexity O(V+E)
    public BiPartitionDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        colors = new int[G.getVertex()];
        Arrays.fill(colors, -1);
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) {
                if (!dfs(i, 0)) {
                    isBiPartition = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        color = (color + 1) % 2;
        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) {
                if (!dfs(adjEdge, color)) {
                    return false;
                }
            } else {
                if (colors[adjEdge] != color)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph5.txt");
        BiPartitionDetection singleSourcePath = new BiPartitionDetection(graph);
        System.out.println(singleSourcePath.isBiPartition);
    }

}
