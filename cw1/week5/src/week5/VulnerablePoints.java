package week5;

import java.util.*;

public class VulnerablePoints {
	
	int vertex; 

	LinkedList<Integer> adj[];
	
	int time = 0;
	int NIL = -1;

	VulnerablePoints(int vtx)
	{
		vertex = vtx;
		adj = new LinkedList[vtx];
		for (int i=0; i<vtx; ++i) {
			adj[i] = new LinkedList();
		}
	}

	//adds an edge into the graph
	void addEdge(int left, int right)
	{
		adj[left].add(right); // Add right to left's list.
		adj[right].add(left); //Add left to right's list
	}

	
	// nextVertex --> The vertex to be visited next
	// visited[] --> keeps tract of visited vertices
	// discTime[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in DFS tree
	// ap[] --> Store articulation points
	
	// A recursive function that find articulation points using DFS
	void findArticulationPoint(int nextVertex, boolean visited[], int discTime[],
				int lowValue[], int parent[], boolean ap[])
	{

		// Count children in DFS Tree
		int children = 0;

		// Marking current node as visited
		visited[nextVertex] = true;

		// Initializing discovery time and low value
		discTime[nextVertex] = lowValue[nextVertex] = ++time;

		// Go through all vertices adjacent to this
		Iterator<Integer> val = adj[nextVertex].iterator();
		while (val.hasNext())
		{
			int curAdj = val.next(); // current adjacent of nexVertex

			// If curAdj is not visited yet, then make it a child of nexVertex
			if (!visited[curAdj])
			{
				children++;
				parent[curAdj] = nextVertex;
				findArticulationPoint(curAdj, visited, discTime, lowValue, parent, ap);

				// Check if the subtree rooted with nexVertex has a connection to one of the ancestors of nexVertex
				lowValue[nextVertex] = Math.min(lowValue[nextVertex], lowValue[curAdj]);

				// nexVertex is an articulation point in following cases

				// 1. nexVertex is root of DFS tree and has two or more chilren.
				if (parent[nextVertex] == NIL && children > 1)
					ap[nextVertex] = true;

				// 2. If nexVertex is not root and low value of one of its child
				// is more than discovery value of nexVertex.
				if (parent[nextVertex] != NIL && lowValue[curAdj] >= discTime[nextVertex])
					ap[nextVertex] = true;
			}

			// Update low value of nexVertex for parent function calls.
			else if (curAdj != parent[nextVertex])
				lowValue[nextVertex] = Math.min(lowValue[nextVertex], discTime[curAdj]);
		}
	}

	// to do DFS traversal.
	void AP(){
		
		// Mark as not visited
		boolean visited[] = new boolean[vertex];
		int discTime[] = new int[vertex];
		int lowValue[] = new int[vertex];
		int parent[] = new int[vertex];
		boolean ap[] = new boolean[vertex]; // store articulation points

		// Initialize parent and visited
		for (int i = 0; i < vertex; i++)
		{
			parent[i] = NIL;
			visited[i] = false;
			ap[i] = false;
		}

		// Call the recursive function to find articulation points in DFS tree rooted with vertex 'i'
		for (int i = 0; i < vertex; i++)
			if (visited[i] == false)
				findArticulationPoint(i, visited, discTime, lowValue, parent, ap);

		// ap[] contains articulation points so print it
		for (int i = 0; i < vertex; i++)
			if (ap[i] == true)
				System.out.print(i+", ");
	}

	// main method
	public static void main(String args[])
	{
		VulnerablePoints g = new VulnerablePoints(15);
		g.addEdge(5, 7);
		g.addEdge(7, 9);
		g.addEdge(9, 5);
		g.addEdge(7, 12);
		g.addEdge(12, 13);
		g.AP();
		System.out.println(" are vulnerable points ");

	}
}
