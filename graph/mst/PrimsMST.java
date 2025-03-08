import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMST {
    public record Pair(int u, int weight) {}

    public int findMST(List<List<Pair>> graph, int V) {
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        boolean[] mstSet = new boolean[V];
        key[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::weight));
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int u = pair.u();
            mstSet[u] = true;
            for (Pair v : graph.get(u)) {
                if (!mstSet[v.u()] && v.weight() < key[v.u()]) {
                    key[v.u()] = v.weight();
                    parent[v.u()] = u;
                    pq.add(new Pair(v.u(), key[v.u()]));
                }
            }
        }

        for (int i = 1; i < V; i++) {
            System.out.println(i + " -- " + parent[i] + " == " + key[i]);
        }
        int sum = 0;
        for (int i = 1; i < V; i++) {
            sum += key[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        PrimsMST primsMST = new PrimsMST();
        List<List<Pair>> graph = new ArrayList<>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Pair(1, 2));
        graph.get(1).add(new Pair(0, 2));
        graph.get(1).add(new Pair(2, 3));
        graph.get(2).add(new Pair(1, 3));
        graph.get(0).add(new Pair(3, 6));
        graph.get(3).add(new Pair(0, 6));
        graph.get(1).add(new Pair(3, 8));
        graph.get(3).add(new Pair(1, 8));
        graph.get(1).add(new Pair(4, 5));
        graph.get(4).add(new Pair(1, 5));
        graph.get(2).add(new Pair(4, 7));
        graph.get(4).add(new Pair(2, 7));
        graph.get(3).add(new Pair(4, 9));
        graph.get(4).add(new Pair(3, 9));

        System.out.println(primsMST.findMST(graph, n));

    }
}

// Output
// 1 -- 0 == 2
// 2 -- 1 == 3
// 3 -- 0 == 6
// 4 -- 1 == 5
// 16

// The priority queue to get the minimum weight edge.
// The parent array to store the parent of each vertex in the MST.
// The key array to store the weight of the edge connecting the vertex to the MST.
// The mstSet array to store the vertices that are already included in the MST.
// The time complexity of the above solution is O(V^2) for the adjacency matrix representation of the graph.
// The time complexity of the above solution is O(E log V) for the adjacency list representation of the graph.

// 📌 When to Use Prim’s Algorithm?

// ✅ Best for dense graphs (E≈V2E≈V2)
// ✅ More efficient with adjacency lists + min-heap
// ✅ Good when working with graphs stored as adjacency lists
// ❌ Less efficient than Kruskal’s for sparse graphs