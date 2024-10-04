package directed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 */
public class DirectedEulerLoop {
    private Graph G;

    public DirectedEulerLoop(Graph g) {
        G = g;
    }

    private boolean hasEulerLoop() {
        for (int i = 0; i < G.getVertex(); i++) {
            if (G.inDegree(i) != G.outDegree(i)) return false;
        }
        return true;
    }

    public ArrayList<Integer> getEulerLoop() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;
        Graph g = G;

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(0);
        while (!stack.isEmpty()) {
            if (g.outDegree(curv) != 0) {
                stack.push(curv);
                int w = g.getAdjs(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("src/directed/ug2.txt", true);
        System.out.println(graph);
        DirectedEulerLoop directedEulerLoop = new DirectedEulerLoop(graph);
        ArrayList<Integer> eulerLoop = directedEulerLoop.getEulerLoop();
        System.out.println(eulerLoop);
    }
}
