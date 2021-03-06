package graph;

import ds.*;
import java.io.*;
public class Graph {
	
	private int V; //정점의 개수
	private int E;
	private AdjList[] adj;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = new AdjList[V];
		
		for(int v = 0; v < V; v++)
			adj[v] = new AdjList();
	}
	
	public void addEdge(int v, int w) { //
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable adj(int v) { 
		return adj[v];
	}
	
	public int V() {return V;}
	public int E() {return E;}
	
	public static void main(String[] args) {
		String fname = "Graph_2.txt";//파일 가져오기 workspace에 삽입해야 한다.
		BufferedReader in = null;
		Graph g;
		String line;
		int V, E;
		try {
			in = new BufferedReader(new FileReader(fname));
			V = Integer.parseInt(in.readLine());
			E = Integer.parseInt(in.readLine());
			g = new Graph(V);
			
			for(int i = 0; i < E; i++) {
				String[] vs = new String[2];
				line = in.readLine();
				vs = line.split(" ");
				g.addEdge(Integer.parseInt(vs[0]), Integer.parseInt(vs[1]));
			}
			in.close();
			
			for(int v = 0; v < V; v++) { //정점과 그 정점에 연결된 다른 정점들이 출력된다.
				System.out.print(v + " : ");
				for(Object e: g.adj(v))
					System.out.print(e + " ");
				System.out.println();
			}
			
			
			
		} catch(IOException e) {
			System.err.println("File Error");
			System.exit(1);
		}

	}

}
