package represent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Use a two dimension array to represent a simple graph
 * simple graph: no self-loop edges and parallel edges.
 */
public class AdjacentMatrix {
    private int vertex;
    private int edge;

    // space complexity O(v^2)
    private int[][] adj;

    public AdjacentMatrix(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        this.adj = new int[vertex][vertex];
    }

    // time complexity O(e)
    public AdjacentMatrix(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            vertex = scanner.nextInt();
            adj = new int[vertex][vertex];
            edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // time complexity O(v)
    public List<Integer> getAdjEdges(int v) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < adj[v].length; i++) {
            if (adj[v][i] == 1)
                res.add(i);
        }
        return res;
    }

    // time complexity O(v)
    public int degree(int v) {
        int degree = 0;
        for (int i = 0; i < adj[v].length; i++) {
            if (adj[v][i] == 1)
                degree++;
        }
        return degree;
    }

    // time complexity O(1)
    public boolean hasEdge(int v1, int v2) {
        return adj[v1][v2] == 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacentMatrix " +
                "vertex=" + vertex +
                ", edge=" + edge);
        sb.append('\n');
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
