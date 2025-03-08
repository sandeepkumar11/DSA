import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TarjanSCC {
    int[] disc, low;
    int time;
    boolean[] visited;
    Stack<Integer> stack;
    boolean[] onStack;

    private void dfs(List<List<Integer>> adj, int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        onStack[u] = true;
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(adj, v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (low[u] == disc[u]) {
            System.out.print("SCC: [");
            while (stack.peek() != u) {
                System.out.print(stack.pop() + ",");
            }
            System.out.print(stack.pop()+"]\n");
        }
    }

    public void findSCC(List<List<Integer>> adj, int V) {
        disc = new int[V];
        low = new int[V];
        visited = new boolean[V];
        stack = new Stack<>();
        onStack = new boolean[V];
        time = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, i);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        int V = 5;
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(1).add(3);
        adj.get(3).add(4);

        TarjanSCC tarjanSCC = new TarjanSCC();
        tarjanSCC.findSCC(adj, V);
    }
}

// 📌 Key Concepts

// Discovery Time (disc[]): The time when a node is first visited.
// Low-Link Value (low[]): The lowest discovery time reachable from that node.
// Stack: Used to track nodes in the current SCC.
// On-Stack Array: Helps check if a node is already in the stack.

// 📝 Algorithm
// 1. Initialize the discovery time, low-link value, visited, and on-stack arrays.
// 2. For each unvisited node, perform a DFS traversal.
// 3. Update the discovery time and low-link value for each node.
// 4. If the low-link value of a node is equal to its discovery time, it is the root of an SCC.
// 5. Pop nodes from the stack until the current node is reached.
// 6. The popped nodes form an SCC.

// 🧩 Complexity Analysis
// The algorithm has a time complexity of O(V+E) since it performs a DFS traversal on the graph.
// The space complexity is O(V) since we use arrays to store the discovery time, low-link value, and visited status of each node.

// 📌 When to Use Tarjan's Algorithm?

// ✅ More efficient than Kosaraju's (single DFS)
// ✅ Does not require reversing the graph
// ✅ Works well for large graphs
// ❌ Harder to understand than Kosaraju’s