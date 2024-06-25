package weightedGraph;

import java.util.*;

/**
 * Dijkstra的局限性：没有负权边
 * Dijkstra 和 BFS的比较：
 * Dijkstra算法用来解决带权图的最短路径问题(无负权), BFS用来解决无权图的最短路径问题。<br><br>
 * 这两个算法在结构上是有共同之处的。<br>
 * BFS 的核心是一个队列;而对于带权图，因为每条边有权值了，所以一个简单的队列不够了，我们需要一个优先队列。<br>
 * Dijkstra算法,其实是BFS算法在有权图上的拓展。为了解决带权边，加入了一些东西，但是，整体算法的思路是一致的。<br>
 */
public class Dijkstra {
    private WeightedGraph graph;
    private int source;
    private int[] dis;
    private int[] pre;

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        dis = new int[graph.getVertex()];
        pre = new int[graph.getVertex()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        // Dijkstra time complexity O(E * log(E))
        boolean[] finished = new boolean[graph.getVertex()];
        // for the element in priority queue int[] a, a[0] is the vertex, a[1] is the distance from a[0] to source
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.add(new int[]{source, 0});
        int preNode = source;
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int curNode = next[0];
            if (finished[curNode])
                continue;
            finished[curNode] = true;
            dis[curNode] = next[1];
            pre[curNode] = preNode;
            preNode = curNode;
            TreeMap<Integer, Integer> adjs = graph.getAdjs(next[0]);
            for (Integer adjNode : adjs.keySet()) {
                if (finished[adjNode])
                    continue;
                pq.add(new int[]{adjNode, dis[next[0]] + adjs.get(adjNode)});
            }
        }
    }

    public boolean isConnectedTo(int v) {
        return dis[v] != Integer.MAX_VALUE;
    }

    public String getShortestPath(int v) {
        List<Integer> list = new ArrayList<>();
        int cur = v;
        while (cur != source) {
            list.add(cur);
            cur = pre[cur];
        }
        list.add(source);
        Collections.reverse(list);
        return list.toString();
    }

    public int getDis(int destination) {
        return dis[destination];
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("src/weightedGraph/graph2.txt");
        Dijkstra dijkstra = new Dijkstra(weightedGraph, 0);
        System.out.println(dijkstra.getDis(1));
        System.out.println(dijkstra.getShortestPath(0));
        System.out.println(dijkstra.getShortestPath(1));
        System.out.println(dijkstra.getShortestPath(2));
        System.out.println(dijkstra.getShortestPath(3));
        System.out.println(dijkstra.getShortestPath(4));
        for (int di : dijkstra.dis) {
            System.out.println(di);
        }
    }


}
