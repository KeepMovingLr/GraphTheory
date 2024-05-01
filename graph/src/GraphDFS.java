import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GraphDFS {

    private Graph G;

    private boolean[] visited;

    // store dfs result
    private List<Integer> order = new ArrayList<>();

    public GraphDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.getVertex()];
        for (int i = 0; i < G.getVertex(); i++) {
            if(!visited[i])
                dfs(i);
        }
    }

    private void dfs(int v) {

        // visit v opertion
        visited[v] = true;
        order.add(v);

        TreeSet<Integer> adjEdges = G.getAdjEdges(v);
        for (Integer adjEdge : adjEdges) {
            if (!visited[adjEdge]) dfs(adjEdge);
        }
    }

    public List<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("./graph/graph3.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        for (Integer vertex : graphDFS.getOrder()) {
            System.out.println(vertex);
        }
    }

}
