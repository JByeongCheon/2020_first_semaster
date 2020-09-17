package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DFS {
	
	private boolean[] visited;
	private int[] edgeTo;
	private int st;
	
	public DFS(Graph G, int s) {
		visited = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.st = s;
		dfs(G,s);
		
	}
	
	private void dfs(Graph G, int v) {
		visited[v] = true;
		System.out.println(v);
		for(Object x: G.adj(v)) {
			int w = (int)x;
			if(!visited[w]) {
				edgeTo[w] = v;
				dfs(G,w);
				}
			}
		}
	
	public int edgeTo(int v) {return edgeTo[v];}
	
	


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
		
			in.close();
			
			DFS df_search = new DFS(g, 0);
			for(int v = 0; v<g.V();v++)
				System.out.println(v + " - "+df_search.edgeTo(v));
		
	} catch(IOException e) {
		System.err.println("File Error");
		System.exit(1);
	}


}
}
