import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSisBipartite {
    public boolean isBipartite(List<List<Integer>>graph,int V){
        int color[] = new int[V];
        Arrays.fill(color,-1);

        Queue<Integer> q = new LinkedList<>();
        
        q.offer(0);
        color[0] = 1;

        while(!q.isEmpty()){
            int node = q.poll();
            for(int neighbour : graph.get(node)){
                if(color[neighbour] == -1){
                    color[neighbour] = 1 - color[node];
                    q.offer(neighbour);
                }else if(color[neighbour] == color[node]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new LinkedList<>();
        int V = 4;
        for(int i=0;i<V;i++){
            graph.add(new LinkedList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(0);
        graph.get(0).add(3);

        BFSisBipartite obj = new BFSisBipartite();
        System.out.println(obj.isBipartite(graph,V));

        List<List<Integer>> graph2 = new LinkedList<>();
        int V2 = 3;
        for(int i=0;i<V2;i++){
            graph2.add(new LinkedList<>());
        }
        graph2.get(0).add(1);
        graph2.get(1).add(0);
        graph2.get(1).add(2);
        graph2.get(2).add(1);
        graph2.get(2).add(0);

        System.out.println(obj.isBipartite(graph2,V2));
    }
}
