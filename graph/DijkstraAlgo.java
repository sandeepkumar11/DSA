package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Pair {
    int node;
    int weight;

    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
public class DijkstraAlgo {
    public static int[] Dijkstra(List<Pair>[] graph,int src,int V){
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)->a.weight-b.weight);
        pq.add(new Pair(src,0));
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int node = p.node;
    
            for(Pair neighbour:graph[node]){
                if(distance[node]+neighbour.weight<distance[neighbour.node]){
                    distance[neighbour.node] = distance[node]+neighbour.weight;
                    pq.add(new Pair(neighbour.node,distance[neighbour.node]));
                }
            }
        }
        return distance;
    }
    public static void main(String[] args){
        int V = 9;
        List<Pair>[]graph = new ArrayList[V];
        for(int i=0;i<V;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Pair(1,4));
        graph[0].add(new Pair(7,8));
        graph[1].add(new Pair(0,4));
        graph[1].add(new Pair(7,11));
        graph[1].add(new Pair(2,8));
        graph[2].add(new Pair(1,8));
        graph[2].add(new Pair(8,2));
        graph[2].add(new Pair(5,4));
        graph[2].add(new Pair(3,7));
        graph[3].add(new Pair(2,7));
        graph[3].add(new Pair(5,14));
        graph[3].add(new Pair(4,9));
        graph[4].add(new Pair(3,9));
        graph[4].add(new Pair(5,10));
        graph[5].add(new Pair(4,10));
        graph[5].add(new Pair(3,14));
        graph[5].add(new Pair(2,4));
        graph[5].add(new Pair(6,2));
        graph[6].add(new Pair(5,2));
        graph[6].add(new Pair(8,6));
        graph[6].add(new Pair(7,1));
        graph[7].add(new Pair(6,1));
        graph[7].add(new Pair(8,7));
        graph[7].add(new Pair(1,11));
        graph[7].add(new Pair(0,8));
        graph[8].add(new Pair(2,2));
        graph[8].add(new Pair(6,6));
        graph[8].add(new Pair(7,7));

        int[] distance = Dijkstra(graph, 0, V);

        for(int i=0;i<V;i++){
            System.out.println("Distance of node "+i+" from source is "+distance[i]);
        }
    }
}
