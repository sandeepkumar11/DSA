import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KosarajuSCC {

    private void dfsFinishOder(List<List<Integer>>adj,int u,boolean[] visited, Stack<Integer>stack){
        visited[u] = true;

        for(int v : adj.get(u)){
            if(!visited[v]){
                dfsFinishOder(adj, v, visited, stack);
            }
        }
        stack.push(u);
    }

    private void dfs(List<List<Integer>>adj,int u,boolean[] visited){
        visited[u] = true;
        System.out.print(u + " ");

        for(int v : adj.get(u)){
            if(!visited[v]){
                dfs(adj, v, visited);
            }
        }
    }

    public void kosarajuAlgo(List<List<Integer>>adj,int V){
        Stack<Integer>stack = new Stack<>();

        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfsFinishOder(adj, i, visited, stack);
            }
        }

        List<List<Integer>>revAdj = new ArrayList<>();
        for(int i = 0; i < V; i++){
            revAdj.add(new ArrayList<>());
        }

        for(int i = 0; i < V; i++){
            for(int v : adj.get(i)){
                revAdj.get(v).add(i);
            }
        }

        Arrays.fill(visited, false);
        int totatSSC = 0;

        while(!stack.isEmpty()){
            int u = stack.pop();
            if(!visited[u]){
                dfs(revAdj, u, visited);
                System.out.println();
                totatSSC++;
            }
        }

        System.out.println("Total Strongly Connected Components: " + totatSSC);
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>>adj = new ArrayList<>();
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(1).add(3);
        adj.get(3).add(4);

        KosarajuSCC scc = new KosarajuSCC();
        scc.kosarajuAlgo(adj, V);
    }
}

// Kosaraju's Algorithm to find Strongly Connected Components in a Directed Graph
// Time Complexity: O(V+E)
// Space Complexity: O(V+E)

// Steps:
// 1. Create a Stack to store the vertices in the order of their finish time.
// 2. Do a DFS traversal of the graph and store the vertices in the stack in the order of their finish time.
// 3. Create a reverse graph by reversing the direction of the edges.
// 4. Pop the vertices from the stack and do a DFS traversal of the reverse graph.
// 5. The DFS traversal of the reverse graph gives the Strongly Connected Components of the graph.

// 📌 When to Use Kosaraju's Algorithm?

// ✅ Efficient for finding SCCs in a directed graph
// ✅ Linear time complexity O(V+E) makes it very fast
// ✅ Simple to implement with DFS and stack
// ❌ Requires graph reversal, which may use extra space