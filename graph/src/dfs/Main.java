package dfs;

public class Main {
    public static void main(String[] args) {
        AdjacentMatrix adjacentMatrix = new AdjacentMatrix("./graph/graph.txt");
        System.out.println(adjacentMatrix);

        AdjacentList adjacentList = new AdjacentList("./graph/graph.txt");
        System.out.println(adjacentList);

        AdjacentSet adjacentSet = new AdjacentSet("./graph/graph.txt");
        System.out.println(adjacentSet);
    }
}