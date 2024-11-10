package graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphCycle {

    static boolean checkCycleUtil(List<List<Integer>>adj,int u, boolean[] visited, boolean[] rec){
        visited[u] = true;
        rec[u] = true;

        for(int x : adj.get(u)){
            if(!visited[x]){
                if(checkCycleUtil(adj, x, visited, rec)) return true;
            }else if(rec[x]){
                return true;
            }
        }

        rec[u] = false;
        return false;
    }

    static boolean checkCycle(List<List<Integer>>adj, int V){
        boolean[] visited = new boolean[V];
        boolean[] rec = new boolean[V];

        for(int i=0;i<V;i++){
            if(!visited[i] && checkCycleUtil(adj, i, visited, rec)){
                return true;
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

        if(checkCycle(adj,V)){
            System.out.println("Cycle detected.");
        }else{
            System.out.println("No Cycle detected.");
        }
    }
}
