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
        int check = 0;
        while (mst.size() < G.getVertex() - 1) {
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

            check = smallest.getV();
            mst.add(smallest);
        }
    }

    // another version
    public Prim(WeightedGraph G, int version) {
        this.G = G;
        mst = new ArrayList<>();
        // check if it is connected
        CC cc = new CC(G);
        if (cc.ccCount != 1) return;

        // Prim,  time complexity E * log(E)
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(Comparator.comparingInt(WeightedEdge::getWeight));
        Set<Integer> partition = new HashSet<>();
        int check = 0;
        partition.add(check);
        for (int nextV : G.getAdj()[0].keySet()) {
            pq.add(new WeightedEdge(check, nextV, G.getAdj()[0].get(nextV)));
        }
        while (!pq.isEmpty()) {
            WeightedEdge smallest = pq.poll();
            while (smallest != null && partition.contains(smallest.getV()) && partition.contains(smallest.getW())) {
                smallest = pq.poll();
            }
            if (smallest == null) break;
            mst.add(smallest);

            // get all cut edges
            check = partition.contains(smallest.getV()) ? smallest.getW() : smallest.getV();
            partition.add(check);

            TreeMap<Integer, Integer> adjs = G.getAdjs(check);
            for (Integer node : adjs.keySet()) {
                if (!partition.contains(node)) { // i - node
                    pq.add(new WeightedEdge(check, node, adjs.get(node)));
                }
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph.txt");
        Prim prim = new Prim(weightedGraph, 0);
        System.out.println(prim.result());
    }
}
