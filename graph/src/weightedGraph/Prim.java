package weightedGraph;

import java.util.ArrayList;
import java.util.TreeMap;

public class Prim {
    private WeightedGraph G;

    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G) {
        this.G = G;
        mst = new ArrayList<>();
        // check if it is connected
        CC cc = new CC(G);
        if (cc.ccCount != 1) return;

        // Prim
        boolean[] visited = new boolean[G.getVertex()];
        visited[0] = true;
        // find  G.getVertex() - 1 edge to generate tree
        for (int times = 0; times < G.getVertex() - 1; times++) {
            // find all Edges crossing the cut and get the smallest one
            WeightedEdge smallest = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < G.getVertex(); v++) {
                if (visited[v]) {
                    TreeMap<Integer, Integer> adjs = G.getAdjs(v);
                    for (Integer node : adjs.keySet()) {
                        if (!visited[node] && adjs.get(node) <= smallest.getWeight()) {
                            smallest = new WeightedEdge(v, node, adjs.get(node));
                        }
                    }
                }
            }
            mst.add(smallest);
            visited[smallest.getV()] = true;
            visited[smallest.getW()] = true;
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph.txt");
        Prim prim = new Prim(weightedGraph);
        System.out.println(prim.result());
    }
}
