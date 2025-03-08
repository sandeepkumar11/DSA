import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Bellman Ford algorithm is used to find the shortest path from source to all other vertices in a weighted graph.
// It can also detect negative weight cycle in the graph.
// Time complexity of Bellman Ford algorithm is O(V*E).
public class BellmanFord {

    record Pair(int src, int dest, int weight){}

    public void bellmanFord(List<List<Pair>> graph, int V, int src){
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i=0;i<V-1;i++){
            for(int j=0;j<V;j++){
                for(Pair edge: graph.get(j)){
                    if(dist[j] != Integer.MAX_VALUE && dist[j] + edge.weight() < dist[edge.dest()]){
                        dist[edge.dest()] = dist[j] + edge.weight();
                    }
                }
            }
        }

        for(int j=0;j<V;j++){
            for(Pair edge: graph.get(j)){
                if(dist[j] != Integer.MAX_VALUE && dist[j] + edge.weight() < dist[edge.dest()]){
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        for(int i=0;i<V;i++){
            System.out.println("Distance from source to "+i+" is "+dist[i]);
        }
    }

    public static void main(String[] args) {
        List<List<Pair>> graph = new LinkedList<>();
        int V = 4;
        for(int i=0;i<V;i++){
            graph.add(new LinkedList<>());
        }
        graph.get(0).add(new Pair(0, 1, 1));
        graph.get(0).add(new Pair(0, 2, 4));
        graph.get(1).add(new Pair(1, 2, 4));
        graph.get(1).add(new Pair(1, 3, 2));
        graph.get(2).add(new Pair(2, 3, 3));

        BellmanFord obj = new BellmanFord();
        obj.bellmanFord(graph, V, 0);

    }
}


// 📌 When to Use Bellman-Ford?

// ✅ When the graph contains negative weight edges
// ✅ When we need to detect negative weight cycles
// ❌ Not suitable for large graphs (Dijkstra is faster for non-negative edges: O((V+E)log⁡V)O((V+E)logV))