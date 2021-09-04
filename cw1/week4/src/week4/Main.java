package week4;

public class Main{
	
	public static void main(String[] args) {
		
		MaxEffort maxEffort = new MaxEffort();
		MinEffort minEffort = new MinEffort();
		int heights [][] = {{1,3,8,9},{8,7,2,6},{13,3,6,4},{13,1,5,3}};
		int maxEffortPath = maxEffort.minimumEffortPath(heights); 
		int minEffortPath = minEffort.minimumEffortPath(heights);
    	System.out.println("Maximum Effort Path: "+maxEffortPath);
    	System.out.println("Minimum Effort Path: "+minEffortPath);		
	}
	
}