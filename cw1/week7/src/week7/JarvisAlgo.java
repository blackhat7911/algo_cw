package week7;

import java.util.Random;
import java.util.Stack;


public class JarvisAlgo {
	
	// finding orientation
	int orientation(Points p, Points q, Points r) {
		int val = ((q.y - p.y) * (r.x - q.x)) - ((q.x - p.x) * (r.y - q.y));
		return val;
	}
	
	// find convex hull of set of num of points
	void convexHull(Points points[], int num) {
		
		Stack<Points> stack = new Stack<Points>();
		
		int left = 0;
		// find left most point
		for (int i=0; i<num; i++) {
			if(points[i].x < points[left].x) {
				left = i;
			}
		}
		
		int p = left;
		do {
			stack.add(points[p]);
			int q = (p+1) % num;
			for(int r=0; r<num; r++) {
				if (orientation(points[p], points[q], points[r]) > 0) {
					q = r;
				}
			}
			p = q;
		} 
		while(p != left);
		
		stack.add(points[p]);
		
		for (Points i : stack) {
			System.out.println("("+ i.x + "," + i.y + ")");
		}
		
	}
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		JarvisAlgo gfw = new JarvisAlgo();
		
		int num = 8;
		
		Points point[] = new Points[num];
		System.out.println("Printing random points ");
		for(int i=0; i<num; i++) {
			point[i] = new Points(rand.nextInt(20), rand.nextInt(20));
			System.out.println("("+ point[i].x + "," + point[i].y + ")");
		}
		System.out.println("Convex Hull");
		gfw.convexHull(point, num);
		
	}
	
}
