package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class GraphDFSNoRecursion {

    private Graph G;

    private boolean[] visited;

    // store dfs result

    private List<Integer> order = new ArrayList<>();

    // time complexity O(V+E)
    public GraphDFSNoRecursion(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) dfs(i);
        }
    }

    // non recursive method, check the similarity with BFS, just change the Queue to Stack
    private void dfs(int v) {
        // visit v operation
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            order.add(vertex);
            TreeSet<Integer> adjEdges = G.getAdjs(vertex);
            for (Integer adjEdge : adjEdges) {
                if (!visited[adjEdge]) {
                    stack.push(adjEdge);
                    visited[adjEdge] = true;
                }
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        GraphDFSNoRecursion graphDFS = new GraphDFSNoRecursion(graph);
        System.out.println(graphDFS.order);
    }

}
