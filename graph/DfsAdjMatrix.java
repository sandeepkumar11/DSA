public class DfsAdjMatrix {

    private static int V;

    public static void addEdge(int[][] graph, int src, int dest) {
        graph[src][dest] = 1;
        graph[dest][src] = 1;
    }

    public static void dfs(int[][] graph, int start) {
        boolean[] visited = new boolean[V];
        dfsUtil(graph, start, visited);
    }

    public static void dfsUtil(int[][] graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int i = 0; i < V; i++) {
            if (graph[start][i] == 1 && !visited[i]) {
                dfsUtil(graph, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        V = 4;
        int[][] graph = new int[V][V];
        addEdge(graph,0, 1);
        addEdge(graph,0, 2);
        addEdge(graph,1, 2);
        addEdge(graph,2, 0);
        addEdge(graph,2, 3);

        dfs(graph,2);
    }
}
