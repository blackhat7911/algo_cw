package week4;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class MaxEffort {
    
    int maxDiff = Integer.MIN_VALUE;
    public int minimumEffortPath(int[][] heights) 
    {
        int r = heights.length;
        int c = heights[0].length;
     
        PriorityQueue<Point> q = new PriorityQueue<Point>((a,b) -> b.diff - a.diff);
        q.add(new Point(0,0,heights[0][0],0));
        Set<String> visited = new HashSet<>();
        int[][] dirs = {{0,1}, {1, 0}, {-1, 0}, {0, -1}};
        
        while(!q.isEmpty())
        {
            Point curr = q.poll();
            maxDiff = Math.max(maxDiff, curr.diff);
            if(curr.x == r-1 && curr.y == c-1) return maxDiff;
            
            visited.add(getKey(curr.x,curr.y));
            for(int[] d : dirs)
            {
                int nextX = d[0] + curr.x;
                int nextY = d[1] + curr.y;
                String key = getKey(nextX,nextY);
                    
                if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c || visited.contains(key)) continue;
                
                int nextDiff = Math.abs(heights[nextX][nextY]-curr.h);
                q.add(new Point(nextX, nextY, heights[nextX][nextY], nextDiff));
            }
        }
        
        return -1;
    }
    
    private String getKey(int x, int y){
        return x + "->" + y;
    }

}