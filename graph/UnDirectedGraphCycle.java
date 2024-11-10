package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnDirectedGraphCycle {
    public static boolean checkCycle(List<List<Integer>> adj, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int x : adj.get(u)) {
            if (!visited[x]) {
                if (checkCycle(adj, x, visited, u)) {
                    return true;
                }
            } else if (x != parent) {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(1).add(2);
        adj.get(2).add(1);

        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        if (checkCycle(adj, 0, visited, -1)) {
            System.out.println("Cycle Detected.");
        } else {
            System.out.println("No Cycle detected.");
        }

    }
}
