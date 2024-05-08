package bfs;

import java.util.*;

public class CycleDetection {
    private Graph G;
    boolean[] visited;
    int[] pre;

    boolean hasCycle;

    public CycleDetection(Graph g) {
        G = g;
        visited = new boolean[G.getVertex()];
        pre = new int[G.getVertex()];
        Arrays.fill(pre , -1);
        for (int i = 0; i < g.getVertex(); i++) {
            if (!visited[i]) {
                if (bfs(i)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // time complexity is O(v+e)
    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            TreeSet<Integer> adjs = G.getAdjs(vertex);
            for (Integer adj : adjs) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    pre[adj] = vertex;
                } else {
                    // note, not pre[adj] != vertex
                    // we know that adj is an adjacent of vertex
                    // 如果 w 已经被访问过了，我们还必须判断，w 不是 v 的上一个节点
                    // 如果 w 不是 v 的上一个节点，说明我们找到了一个环
                    if (pre[vertex] != adj) { // adj -> vertex, then vertex -> adj
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isConnectedTo(int t) {
        return visited[t];
    }


    public static void main(String[] args) {
        Graph graph = new Graph("graph5.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle);
    }
}
