package weightedGraph;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * use this to represent graph
 * undirected weighted graph
 */
public class WeightedGraph implements Cloneable {
    private int vertex;
    private int edge;

    // space complexity O(v+e)
    // key: node  value: weight
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            vertex = scanner.nextInt();
            adj = new TreeMap[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new TreeMap<>();
            }
            edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int weight = scanner.nextInt();
                if (adj[a].keySet().contains(b)) {
                    throw new Exception("Parallel edge");
                }
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // time complexity O(1), but when we want to traverse all edges, time complexity is O(degree(v))
    public TreeMap<Integer, Integer> getAdjs(int v) {
        return adj[v];
    }

    // time complexity O(1)
    public int degree(int v) {
        return adj[v].size();
    }

    public int getWeight(int v, int w) {
        return adj[v].get(w);
    }

    /**
     * remove edge v-w
     *
     * @param v
     * @param w
     */
    public void removeEdge(int v, int w) {
        TreeMap<Integer, Integer> adjV = getAdjs(v);
        TreeMap<Integer, Integer> adjW = getAdjs(w);
        adjV.remove(w);
        adjW.remove(v);
    }

    // time complexity O(log(v))
    public boolean hasEdge(int v1, int v2) {
        return adj[v1].containsKey(v2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacentSet " + "vertex=" + vertex + ", edge=" + edge);
        sb.append('\n');
        for (int v = 0; v < vertex; v++) {
            sb.append(String.format("%d: ", v));
            for (Integer e : adj[v].keySet()) {
                sb.append("(");
                sb.append(String.format("%d ", e));
                sb.append(": ");
                sb.append(String.format("%d", adj[v].get(e)));
                sb.append(")");
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

    public TreeMap<Integer, Integer>[] getAdj() {
        return adj;
    }

    @Override
    protected Object clone() {
        try {
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[vertex];
            for (int v = 0; v < vertex; v++) {
                cloned.adj[v] = new TreeMap<>();
                for (int w : adj[v].keySet()) {
                    cloned.adj[v].put(w, adj[v].get(w));
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

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph.txt");
        System.out.println(weightedGraph.toString());
    }
}
