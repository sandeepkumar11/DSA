package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectedCycleBFS {
    static boolean checkCycle(List<List<Integer>> adj, int V) {
        int[] inDegree = new int[V];
        Arrays.fill(inDegree, 0);
        boolean[] visited = new boolean[V];

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int node = q.poll();
            visited[node] = true;

            for (int x : adj.get(node)) {
                if (visited[x])
                    return true;
                q.offer(x);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 10;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);
        adj.get(3).add(3);

        if (checkCycle(adj, V)) {
            System.out.println("Cycle detected.");
        } else {
            System.out.println("No Cycle detected.");
        }
    }
}
