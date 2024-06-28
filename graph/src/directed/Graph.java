package directed;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * use this to represent both a directed graph or an undirected graph.
 */
public class Graph {
    private int vertex;
    private int edge;

    // space complexity O(v+e)
    private TreeSet<Integer>[] adj;

    private boolean directed;

    public Graph(String filename, boolean directed) {
        File file = new File(filename);
        this.directed = directed;
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
                if (!directed)
                    adj[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Graph(String filename) {
        this(filename, false);
    }

    /**
     * check if a graph is directed graph or undirected graph
     *
     * @return
     */
    public boolean isDirected() {
        return directed;
    }

    // time complexity O(1), but when we want to traverse all edges, time complexity is O(degree(v))
    public TreeSet<Integer> getAdjs(int v) {
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

    /**
     * remove edge v-w
     *
     * @param v
     * @param w
     */
    public void removeEdge(int v, int w) {
        TreeSet<Integer> adjV = getAdjs(v);
        adjV.remove(w);
    }

    @Override
    protected Object clone() {
        try {
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[vertex];
            for (int v = 0; v < vertex; v++) {
                cloned.adj[v] = new TreeSet<>();
                for (int w : adj[v]) {
                    cloned.adj[v].add(w);
                }
            }
            cloned.vertex = this.vertex;
            cloned.edge = this.edge;
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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

    public int getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }

    public TreeSet<Integer>[] getAdj() {
        return adj;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/directed/ug.txt");
        System.out.println(graph);
        Graph graph2 = new Graph("src/directed/ug.txt", true);
        System.out.println(graph2);
    }
}
