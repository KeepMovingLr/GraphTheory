package directed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * topological sort
 */
public class TopologicalSort {
    private Graph G;

    private ArrayList<Integer> res;

    private boolean hasCycle = false;

    public TopologicalSort(Graph G) {
        if (!G.isDirected()) {
            throw new IllegalArgumentException("Topological sort only works on directed graph");
        }
        this.G = G;
        res = new ArrayList<>();

        int[] inDegrees = new int[G.getVertex()];
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < inDegrees.length; v++) {
            inDegrees[v] = G.inDegree(v);
            if (inDegrees[v] == 0) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            int vertex = q.poll();
            res.add(vertex);
            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                inDegrees[adj]--;
                if (inDegrees[adj] == 0) {
                    q.add(adj);
                }
            }
        }
        if (res.size() != G.getVertex())
            hasCycle = true;
    }

    public ArrayList<Integer> getRes() {
        return res;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }
}
