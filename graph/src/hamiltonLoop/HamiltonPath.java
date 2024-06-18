package hamiltonLoop;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class HamiltonPath {

    private Graph G;

    private boolean[] visited;

    private int[] pre;
    private int start;
    private int end;
    private int remain;

    // time complexity O(V+E)
    public HamiltonPath(Graph G, int start) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        this.start = start;
        end = -1;
        remain = G.getVertex();
        dfs(start, start, remain);
    }

    private boolean dfs(int v, int parent, int remain) {
        visited[v] = true;
        pre[v] = parent;
        remain--;
        if (remain == 0) {
            end = v;
            return true;
        }
        TreeSet<Integer> adjNodes = G.getAdjs(v);
        for (Integer adjNode : adjNodes) {
            if (!visited[adjNode]) {
                if (dfs(adjNode, v, remain)) {
                    return true;
                }
            }
        }
        visited[v] = false;
        return false;
    }

    public List<Integer> getHamiltonPath() {
        List<Integer> res = new ArrayList<>();
        if (end == -1)
            return res;
        res.add(end);
        while (end != start) {
            end = pre[end];
            res.add(end);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/hamiltonLoop/graph2.txt");
        HamiltonPath hamiltonPath = new HamiltonPath(graph, 1);
        System.out.println(hamiltonPath.getHamiltonPath());
    }

}
