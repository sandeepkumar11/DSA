import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {
    public record Edge(int u, int v, int weight) {}

    public class DisJointSet {
        private final int[] parent;
        private final int[] rank;

        public DisJointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] == u) {
                return u;
            }
            return parent[u] = find(parent[u]);
        }

        public void union(int u, int v) {
            int pu = find(u);
            int pv = find(v);
            if (pu == pv) {
                return;
            }
            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pu] = pv;
                rank[pv]++;
            }
        }
    }

    public int kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight()));

        DisJointSet dsu = new DisJointSet(V);

        List<Edge> mst = new ArrayList<>();
        int mstWeight = 0;
        for (Edge edge : edges) {
            if (dsu.find(edge.u()) != dsu.find(edge.v())) {
                mst.add(edge);
                mstWeight += edge.weight();
                dsu.union(edge.u(), edge.v());
            }
        }

        for (Edge edge : mst) {
            System.out.println(edge.u() + " -- " + edge.v() + " == " + edge.weight());
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        KruskalMST kruskalMST = new KruskalMST();
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        System.out.println(kruskalMST.kruskalMST(4, edges));
    }
}

// Kruskal’s Algorithm for Minimum Spanning Tree
// Kruskal’s Algorithm is a Greedy Algorithm used to find the MST of a connected, undirected graph.
// The MST is a subset of the edges of the graph that connects all the vertices with the minimum possible total edge weight.
// Kruskal’s Algorithm is based on the Disjoint Set data structure to detect cycles in the graph.
// The algorithm works by sorting all the edges in non-decreasing order of their weight and adding them one by one to the MST if adding the edge does not form a cycle.
// The algorithm terminates when V-1 edges have been added to the MST, where V is the number of vertices in the graph.
// The time complexity of Kruskal’s Algorithm is O(E log E) or O(E log V).


// 📌 When to Use Kruskal’s Algorithm?

// ✅ Best for sparse graphs (fewer edges E≈VE≈V)
// ✅ Works well when edges are already sorted
// ✅ Useful when edges are stored separately (e.g., in an edge list)
// ❌ Less efficient for dense graphs (Prim’s Algorithm is better for adjacency matrix)
