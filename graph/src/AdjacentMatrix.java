import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdjacentMatrix {
    private int vertex;
    private int edge;

    private int[][] adj;

    public AdjacentMatrix(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        this.adj = new int[vertex][vertex];
    }

    public AdjacentMatrix(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            vertex = scanner.nextInt();
            adj = new int[vertex][vertex];
            edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacentMatrix " +
                "vertex=" + vertex +
                ", edge=" + edge);
        sb.append('\n');
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                sb.append(String.format("%d " , adj[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
