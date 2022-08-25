import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Edge>[] node_list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(bf.readLine())-1;
		
		int[] minEdge = new int[V];
		boolean[] visit = new boolean[V]; 
		node_list =new ArrayList[V];
		for (int i = 0; i < V; i++) {
			node_list[i] = new ArrayList<>();
			minEdge[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			node_list[s].add(new Edge(d,w));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->{
			return e1.weight - e2.weight;
		}) ;
		pq.add(new Edge(target,0));
		minEdge[target] = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
//			System.out.println(edge);
//			System.out.println(node_list[edge.dest]);
			if(minEdge[edge.dest]<edge.weight) {
				continue;//해당경로는 최단경로 이후 진행할 필요없다. 우선순위 큐라서 
			}
			for(int i = 0 ; i <node_list[edge.dest].size();i++) {
				int target_dest = node_list[edge.dest].get(i).dest;
				int target_weight = node_list[edge.dest].get(i).weight;
				
				if(minEdge[target_dest]>edge.weight+target_weight) {
					minEdge[target_dest] = edge.weight+target_weight;
					pq.add(new Edge(target_dest,minEdge[target_dest]));
				}
				
			}
//			System.out.println(Arrays.toString(minEdge));
		}
		for(int a : minEdge) {
			if(a == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else
				System.out.println(a);
		}
		
	}
}


class Edge {
	int dest;
	int weight;

	public Edge(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [dest=" + dest + ", weight=" + weight + "]";
	}
	
}
