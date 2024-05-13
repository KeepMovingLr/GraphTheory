package eulerLoop;

/**
 * for a connected component, the degree of each vertex is even, then it has Euler loop; <br>
 * if one graph has Euler loop, then the degree of each vertex is even
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
}
