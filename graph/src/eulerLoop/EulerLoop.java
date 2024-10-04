package eulerLoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * for a connected component, the degree of each vertex is even, then it has Euler loop; <br>
 * if one graph has Euler loop, then the degree of each vertex is even <br>
 * <p>
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
        if (cc.getCcCount() > 1) return false;
        // get all degree
        for (int v = 0; v < g.getVertex(); v++) {
            if (g.degree(v) % 2 == 1) return false;
        }
        return true;
    }

    // Hierholzer algorithm
    public ArrayList<Integer> getEulerLoop() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;
        Stack<Integer> stack = new Stack<>();
        Graph graph = (Graph) g.clone();
        // start from 0
        int curV = 0;
        stack.push(curV);
        while (!stack.empty()) {
            if (graph.degree(curV) != 0) {
                stack.push(curV);
                TreeSet<Integer> adjs = graph.getAdjs(curV);
                int next = adjs.first();
                graph.removeEdge(curV, next);
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
        if (!hasEulerLoop()) return res;
        Stack<Integer> stack = new Stack<>();
        Graph graph = (Graph) g.clone();
        // start from 0
        int curV = 0;
        stack.push(curV);
        while (!stack.empty()) {
            if (graph.degree(curV) != 0) {
                TreeSet<Integer> adjs = graph.getAdjs(curV);
                int next = adjs.first();
                stack.push(next);
                graph.removeEdge(curV, next);
                curV = next;
            } else {
                res.add(stack.pop());
                if (!stack.isEmpty()) curV = stack.peek();
            }
        }
        return res;
    }

    public boolean hasEulerPath() {
        CC cc = new CC(g);
        if (cc.getCcCount() > 1) return false;
        // get odd degree count
        int cnt = 0;
        for (int v = 0; v < g.getVertex(); v++) {
            if (g.degree(v) % 2 == 1) cnt++;
        }
        return cnt == 2;
    }

    // Hierholzer algorithm
    public List<Integer> getEulerPath() {
        List<Integer> res = new ArrayList<>();
        if (!hasEulerPath()) {
            return res;
        }
        //get the first odd degree node as the start node
        Graph graph = (Graph) g.clone();
        int vertex = graph.getVertex();
        int cur = 0;
        for (int i = 0; i < vertex; i++) {
            if (graph.degree(i) % 2 == 1) {
                cur = i;
                break;
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            if (graph.degree(cur) != 0) {
                TreeSet<Integer> adjs = graph.getAdjs(cur);
                int next = adjs.first();
                stack.push(next);
                graph.removeEdge(cur, next);
                cur = next;
            } else {
                res.add(stack.pop());
                if (!stack.isEmpty()) {
                    cur = stack.peek();
                }
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
        System.out.println("-------------------");
        Graph graph2 = new Graph("graph7.txt");
        EulerLoop el2 = new EulerLoop(graph2);
        System.out.println(el2.hasEulerLoop());
        ArrayList<Integer> e1 = el2.getEulerLoop();
        ArrayList<Integer> e2 = el2.getEulerLoop2();
        System.out.println(e1);
        System.out.println(e2);

        System.out.println("-------------------");
        Graph graph3 = new Graph("graph8.txt");
        EulerLoop el3 = new EulerLoop(graph3);
        System.out.println(el3.hasEulerPath());
        System.out.println(el3.getEulerPath());
    }


}
