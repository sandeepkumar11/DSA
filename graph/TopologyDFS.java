package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologyDFS {
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(3).add(1);
        adj.get(3).add(2);

        topologicatSort(adj, V);
    }

    private static void topologicatSort(List<List<Integer>> adj, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicatSortUtil(adj, i, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop() + " -> ");
        }
    }

    private static void topologicatSortUtil(List<List<Integer>> adj, int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;

        for (int x : adj.get(u)) {
            if (!visited[x]) {
                topologicatSortUtil(adj, x, visited, stack);
            }
        }
        stack.push(u);
    }
}
