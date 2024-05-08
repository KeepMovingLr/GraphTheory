package represent;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdjacentList {
    private int vertex;
    private int edge;

    // space complexity O(v+e)
    private LinkedList<Integer>[] adj;

    // time complexity O(v*e)
    public AdjacentList(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            vertex = scanner.nextInt();
            adj = new LinkedList[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new LinkedList<>();
            }
            edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                // todo this operation is a time-consuming operation, here is O(n) , n is the degree of the vertex
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
    public List<Integer> getAdjEdges(int v) {
        return adj[v];
    }

    // time complexity O(1)
    public int degree(int v) {
        return adj[v].size();
    }

    // time complexity O(degree(v)) todo: this part need to be optimized
    public boolean hasEdge(int v1, int v2) {
        return adj[v1].contains(v2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacentList " +
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
