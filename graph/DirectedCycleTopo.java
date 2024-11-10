package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectedCycleTopo {
    static boolean checkCycle(List<List<Integer>> adj, int V) {
        int[] inDegree = new int[V];
        Arrays.fill(inDegree, 0);

        for (int i = 0; i < V; i++) {
            for (int x : adj.get(i)) {
                inDegree[x]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int visited = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            visited++;

            for (int x : adj.get(node)) {
                inDegree[x]--;
                if (inDegree[x] == 0) {
                    q.offer(x);
                }
            }
        }
        return visited != V;
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
