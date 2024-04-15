import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * use this to represent graph
 */
public class Graph {
    private int vertex;
    private int edge;

    // space complexity O(v+e)
    private TreeSet<Integer>[] adj;

    public Graph(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            vertex = scanner.nextInt();
            adj = new TreeSet[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new TreeSet<>();
            }
            edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                // todo this operation is a time-consuming operation, here is O(log(n) n is the degree of the vertex
                if (adj[a].contains(b)) {
                    throw new Exception("Parallel edge");
                }
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // time complexity O(1), but when we want to traverse all edges, time complexity is O(degree(v))
    public TreeSet<Integer> getAdjEdges(int v) {
        return adj[v];
    }

    // time complexity O(1)
    public int degree(int v) {
        return adj[v].size();
    }

    // time complexity O(log(v))
    public boolean hasEdge(int v1, int v2) {
        return adj[v1].contains(v2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacentSet " +
                "vertex=" + vertex +
                ", edge=" + edge);
        sb.append('\n');
        for (int v = 0; v < vertex; v++) {
            sb.append(String.format("%d: ", v));
            for (Integer e : adj[v]) {
                sb.append(String.format("%d ", e));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
