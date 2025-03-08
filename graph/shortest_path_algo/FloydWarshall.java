package graph.shortest_path_algo;

// Floyd Warshall Algorithm is used to find the shortest path between all pairs of vertices in a weighted graph.
// The time complexity of the algorithm is O(V^3) where V is the number of vertices in the graph.
// The space complexity of the algorithm is O(V^2).
public class FloydWarshall {

    final static int INF = 1_000_000_000;

    public static void main(String[] args) {
        int[][] graph = {
                { 0, 5, INF, 10 },
                { INF, 0, 3, INF },
                { INF, INF, 0, 1 },
                { INF, INF, INF, 0 }
        };
        int[][] result = floydWarshall(graph, 4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (result[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static int[][] floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++) {
            dist[i] = graph[i].clone();
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }
}


// 📌 When to Use Floyd-Warshall?

// ✅ If you need shortest paths between all pairs of nodes
// ✅ If the graph is small (~500 vertices or fewer)
// ✅ Works with both positive and negative weights (but no negative cycles!)
// ❌ Not efficient for large graphs (Dijkstra's algorithm is better for single-source shortest paths)