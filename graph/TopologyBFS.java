package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Kahn's Algorithm
public class TopologyBFS {
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
        int[] inDegree = new int[V];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            for (int x : adj.get(i)) {
                inDegree[x]++;
            }
        }

        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int visited = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " -> ");
            visited++;
            for (int x : adj.get(u)) {
                inDegree[x]--;
                if (inDegree[x] == 0) { 
                    q.offer(x);
                }
            }
        }

        if(visited!=V){
            System.out.println("There is a cycle in the Graph");
        }
    }
}
