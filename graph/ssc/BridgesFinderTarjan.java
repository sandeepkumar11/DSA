import java.util.ArrayList;
import java.util.List;

public class BridgesFinderTarjan {
    int[] disc, low;
    int time;
    boolean[] visited;
    boolean[] isArticulationPoint;
    
    private void dfs(List<List<Integer>> adj, int u, int parent) {
        visited[u] = true;
        disc[u] = low[u] = time++;
        for (int v : adj.get(u)) {

            if(v == parent) continue;

            if (!visited[v]) {
                dfs(adj, v, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    System.out.println(u + " -- " + v);
                }
                if(low[v] >= disc[u]){
                    isArticulationPoint[u] = true;
                }
            } else{
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public void findBridges(List<List<Integer>> adj, int V) {
        disc = new int[V];
        low = new int[V];
        visited = new boolean[V];
        isArticulationPoint = new boolean[V];
        time = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, i, -1);
            }
        }

        for(int i = 0; i < V; i++){
            if(isArticulationPoint[i]){
                System.out.println(i + " is an articulation point");
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        int V = 5;
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(0);
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(3).add(4);
        adj.get(4).add(3);

        BridgesFinderTarjan bridgesFinderTarjan = new BridgesFinderTarjan();
        bridgesFinderTarjan.findBridges(adj, V);
    }
}

// 📌 Key Concepts

// Discovery Time (disc[]) → When a node is first visited.
// Lowest Reachable Time (low[]) → The earliest visited node reachable (using back edges).
// Bridges → If low[v] > disc[u], then edge (u, v) is a bridge.

// 📌 Complexity Analysis
// Time complexity: O(V + E)

// Steps
// Create a list of lists to represent the graph.
// Initialize the discovery time (disc[]) and lowest reachable time (low[]).
// If the lowest reachable time of the adjacent node is greater than the discovery time of the current node, print the edge as a bridge.
// If the adjacent node is visited and not the parent, update the lowest reachable time.