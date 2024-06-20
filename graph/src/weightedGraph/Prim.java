package weightedGraph;

import java.util.*;

public class Prim {
    private WeightedGraph G;

    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G) {
        this.G = G;
        mst = new ArrayList<>();
        // check if it is connected
        CC cc = new CC(G);
        if (cc.ccCount != 1) return;

        // Prim,  time complexity E * log(E)
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(Comparator.comparingInt(WeightedEdge::getWeight));
        Set<Integer> partition = new HashSet<>();
        int edges = 1;
        int check = 0;
        while (edges < G.getVertex()) {
            partition.add(check);
            // get all cut edges
            TreeMap<Integer, Integer> adjs = G.getAdjs(check);
            for (Integer node : adjs.keySet()) {
                if (!partition.contains(node)) { // i - node
                    pq.add(new WeightedEdge(check, node, adjs.get(node)));
                }
            }

            WeightedEdge smallest = pq.poll();
            while (partition.contains(smallest.getV()) && partition.contains(smallest.getW())) {
                smallest = pq.poll();
            }

            partition.add(smallest.getW());
            partition.add(smallest.getV());
            check = smallest.getV();
            mst.add(smallest);
            edges++;
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
