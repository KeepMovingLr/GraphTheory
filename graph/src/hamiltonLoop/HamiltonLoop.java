package hamiltonLoop;


import java.util.*;

public class HamiltonLoop {

    private Graph G;

    private boolean[] visited;

    private int[] pre;
    private int end;

    private int remain;

    // time complexity O(V+E)
    public HamiltonLoop(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        end = -1;
        remain = G.getVertex();
        dfs(0, 0, remain);
    }

    private boolean dfs(int v, int parent, int remain) {
        visited[v] = true;
        pre[v] = parent;
        remain--;
        TreeSet<Integer> adjNodes = G.getAdjs(v);
        for (Integer adjNode : adjNodes) {
            if (!visited[adjNode]) {
                if (dfs(adjNode, v, remain)) {
                    return true;
                }
            } else {
                if (adjNode == 0 && remain == 0) {
                    end = v;
                    return true;
                }
            }
        }

        visited[v] = false;
        return false;
    }

    public List<Integer> getHamiltonLoop() {
        List<Integer> res = new ArrayList<>();
        if (end == -1)
            return res;
        res.add(end);
        while (end != 0) {
            end = pre[end];
            res.add(end);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/hamiltonLoop/graph.txt");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(graph);
        System.out.println(hamiltonLoop.getHamiltonLoop());
    }

}
