package weightedGraph;

public class UF {
    private int[] parent;
    private int[] rank;

    public UF(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        int rankP = rank[pId];
        int rankQ = rank[qId];
        if (rankP > rankQ) {
            parent[qId] = parent[pId];
        } else if (rankP < rankQ) {
            parent[pId] = parent[qId];
        } else {
            parent[qId] = parent[pId];
            rank[pId] = rank[pId] + 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if (p != parent[p]) parent[p] = find(parent[p]);
        return parent[p];
    }
}
