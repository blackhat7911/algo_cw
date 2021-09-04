package week8to11;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
	int vertices;
    int adjacency_matrix[][];
    
    Graph(int v){
        vertices=v;
        
        adjacency_matrix=new int[vertices][vertices];
    }
    
    
    void addEdge(int source,int destination) {
        
        adjacency_matrix[source][destination]=1;
        adjacency_matrix[destination][source]=1;
        
    }
    public int[] shortestPath(int source, int destination) {
        
        boolean visited[]=new boolean[vertices];
        int mindistance[]=new int[vertices];
        int prevpath [] =new int[vertices];
        
        for(int i=0;i<vertices;i++) {
            
            mindistance[i]=Integer.MAX_VALUE;
            prevpath[i]=-1;
        }
        
        mindistance[source]=0;
        
        
        
        for(int i=0;i<adjacency_matrix.length;i++) {
            
            int minvertex=findMinvertex(mindistance,visited);
            visited[minvertex]=true;
            
            for(int j=0;j<adjacency_matrix.length;j++) {
                
                if(adjacency_matrix[minvertex][j]!=0 && !visited[j]) {
                    
                    int newdistance=mindistance[minvertex]+adjacency_matrix[minvertex][j];
                    
                    if(newdistance<mindistance[j]) {
                        mindistance[j]=newdistance;
                        prevpath[j]=minvertex;
                        
                    }
                }
            }
        }
                        
    
        return prevpath;
        
    }
    
    public int findMinvertex(int mindistance[],boolean visited[]) {
        int minvertex=-1;
        for(int i=0;i<mindistance.length;i++) {
            
            if(!visited[i] && (minvertex==-1 || mindistance[i]<mindistance[minvertex])) {
                
                minvertex=i;
            }
        }
        
        return minvertex;
    }
    
    public void printGraph() {
        
        System.out.println("Graph is");
        for(int i=0;i<vertices;i++) {
            
          for(int j=0;j<vertices;j++) {
              
              System.out.print(adjacency_matrix[i][j]+" ");
          }
          System.out.println("");
        }
    }
    
    
    public void printEdges() {
        
        for(int i=0;i<vertices;i++) {
            
            System.out.print("vertex "+i+ " is connected to ");
            
           for(int j=0;j<vertices;j++) {
              
               if(adjacency_matrix[i][j]>0) {
                   System.out.print(j+" ");
               }
               
               
           }
           System.out.println("");
        }
    }
    
    public ArrayList<Integer> getEdgeOf(int index) {
        ArrayList<Integer> edges = new ArrayList<Integer>();
        
        for(int i=0;i<vertices;i++) {
            if (i==index) {
                for(int j=0;j<vertices;j++) {
                   if(adjacency_matrix[i][j]>0) {
                       edges.add(j);
                   } 
               }
                break;
                
            }
        }
        return edges;
    }
	
}
