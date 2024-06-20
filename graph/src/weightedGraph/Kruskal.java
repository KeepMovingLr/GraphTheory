package weightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

public class Kruskal {
    private CC cc;
    private WeightedGraph G;

    private ArrayList<WeightedEdge> mst;

    // time complexity O(E * log(E)) because of ranking
    public Kruskal(WeightedGraph weightedGraph) {
        this.G = weightedGraph;
        mst = new ArrayList<>();

        // check if it is connected
        cc = new CC(G);
        if (cc.ccCount != 1) return;

        // Kruskal
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.getVertex(); v++) {
            TreeMap<Integer, Integer> adjs = G.getAdjs(v);
            Set<Integer> adjNodes = adjs.keySet();
            for (Integer adjNode : adjNodes) {
                if (v < adjNode) { // remove duplication
                    edges.add(new WeightedEdge(v, adjNode, adjs.get(adjNode)));
                }
            }
        }
        Collections.sort(edges, (WeightedEdge a, WeightedEdge b) -> (a.getWeight() - b.getWeight()));
        UF uf = new UF(G.getVertex());
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                mst.add(edge);
                uf.union(v, w);
            }
        }

    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph.txt");
        Kruskal kruskal = new Kruskal(weightedGraph);
        System.out.println(kruskal.result());
    }

}
