package hamiltonLoop;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class HamiltonLoop {

    private Graph G;

    private boolean[] visited;

    // store dfs result
    private List<Integer> pre = new ArrayList<>();

    private List<Integer> post = new ArrayList<>();

    // time complexity O(V+E)
    public HamiltonLoop(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if (!visited[i]) dfs(i);
        }
    }

    private void dfs(int v) {

        // visit v operation
        visited[v] = true;
        pre.add(v); // pre order traverse

        TreeSet<Integer> adjEdges = G.getAdjs(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) dfs(adjEdge);
        }

        // post order traverse
        post.add(v);
    }

    public List<Integer> getPre() {
        return pre;
    }

    public List<Integer> getPost() {
        return post;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph3.txt");
        HamiltonLoop graphDFS = new HamiltonLoop(graph);
        for (Integer pre : graphDFS.getPre()) {
            System.out.println(pre);
        }
        System.out.println("--------------------");
        for (Integer post : graphDFS.getPost()) {
            System.out.println(post);
        }

    }

}
