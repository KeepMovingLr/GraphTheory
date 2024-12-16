package directed;

import java.util.*;

/**
 * 深度优先后序遍历的逆序就是拓扑排序结果。<br>
 * The reverse of the post-order traversal in depth-first search (DFS) is the result of topological sorting.
 * 对于一个节点，遍历完其所有相邻节点之后，再遍历它自身。 <br>
 * 存在的问题：不能进行环检测。
 */
public class TopologicalSort2 {
    private Graph G;

    private ArrayList<Integer> res;

    private boolean hasCycle = false;


    public TopologicalSort2(Graph G) {
        if (!G.isDirected()) {
            throw new IllegalArgumentException("Topological sort only works on directed graph");
        }
        this.G = G;
        res = new ArrayList<>();

        // todo 先进行环检测
        // hasCycle = new DirectedCycleDetection
        if (hasCycle) return;
        GraphDFS graphDFS = new GraphDFS(G);
        res.addAll(graphDFS.getPost());
        Collections.reverse(res);
    }


    public ArrayList<Integer> getRes() {
        return res;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }
}
