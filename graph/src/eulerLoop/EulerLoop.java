package eulerLoop;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

/**
 * for a connected component, the degree of each vertex is even, then it has Euler loop; <br>
 * if one graph has Euler loop, then the degree of each vertex is even <br>
 *
 * An Euler path, or Eulerian path, in a graph is a path that visits every edge exactly once. <br>
 * An Eulerian circuit,if such a path exists that starts and ends at the same vertex, it's called an Eulerian circuit or Eulerian cycle. <br>
 * Eulerian Circuit: A graph has an Eulerian circuit if and only if all vertices have even degree, and all vertices with nonzero degree are connected.<br>
 * Eulerian Path: A graph has an Eulerian path if and only if exactly zero or two vertices have an odd degree, and all vertices with nonzero degree belong to a single connected component.<br>
 */
public class EulerLoop {
    private Graph g;

    public EulerLoop(Graph g) {
        this.g = g;
    }

    public boolean hasEulerLoop() {
        CC cc = new CC(g);
        if (cc.getCcCount() > 1)
            return false;
        // get all degree
        for (int v = 0; v < g.getVertex(); v++) {
            if (g.degree(v) % 2 == 1)
                return false;
        }
        return true;
    }

    // Hierholzer algorithm
    public ArrayList<Integer> getEulerLoop() {
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop())
            return res;
        Stack<Integer> stack = new Stack<>();
        Graph graph = (Graph)g.clone();
        // start from 0
        int curV = 0;
        stack.push(curV);
        while (!stack.empty()) {
            if (graph.degree(curV) != 0) {
                stack.push(curV);
                TreeSet<Integer> adjs = graph.getAdjs(curV);
                int next = adjs.first();
                graph.removeEdge(curV , next);
                curV = next;
            } else {
                res.add(curV);
                curV = stack.pop();
            }
        }
        return res;
    }

    // Hierholzer algorithm -- my solution is easy to understand
    public ArrayList<Integer> getEulerLoop2() {
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop())
            return res;
        Stack<Integer> stack = new Stack<>();
        Graph graph = (Graph)g.clone();
        // start from 0
        int curV = 0;
        stack.push(curV);
        while (!stack.empty()) {
            if (graph.degree(curV) != 0) {
                TreeSet<Integer> adjs = graph.getAdjs(curV);
                int next = adjs.first();
                stack.push(next);
                graph.removeEdge(curV , next);
                curV = next;
            } else {
                res.add(stack.pop());
                if(!stack.isEmpty())
                    curV = stack.peek();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph6.txt");
        EulerLoop el = new EulerLoop(graph);
        System.out.println(el.hasEulerLoop());
        ArrayList<Integer> eulerLoop = el.getEulerLoop();
        ArrayList<Integer> eulerLoop2 = el.getEulerLoop2();
        System.out.println(eulerLoop);
        System.out.println(eulerLoop2);
    }



}
