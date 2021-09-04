package week7;

import java.util.ArrayList;

public class CircularQueue {
	
	// declaring variables
	int size, front, rear;
	// declaring array of integer type
	ArrayList<Integer> queue = new ArrayList<Integer>();
	
	CircularQueue(int size) {
		this.size = size;
		this.front = this.rear = -1;
	}
	
	boolean isFull() {
		return ((front == 0 && rear == size -1) || (rear == (front-1) % (size-1)));
	}
	
	boolean isEmpty() {
		return front == -1;
	}
	
	public void enQueue(int val) {
		
		if(isFull()) {
			System.out.println("Queue is full");
		}
		else if(isEmpty()) {
			front = 0;
			rear = 0;
			queue.add(rear, val);
		}
		else if(rear == size-1 && front != 0) {
			rear = 0;
			queue.set(rear, val);
		}
		else {
			rear = (rear + 1);
			// adding new element
			if (front <= rear) {
				queue.add(rear, val);
			}
			// updating old value
			else{
				queue.set(rear, val);
			}
		}
		
	}
	
	public int deQueue() {
		
		int temp;
		
		// condition for empty queue
		if(front == -1) {
			System.out.println("Queue is empty");
			return -1;
		}
		
		temp = queue.get(front);
		
		//condition for only one element
		if(front==rear) {
			front = -1;
			rear = -1;
		}
		else if(front == size-1) {
			front = 0;
		}
		else {
			front = front + 1;
		}
		return temp;
	}
	
	public void printQueue() {
		// Condition for empty queue. 
	    if(front == -1) 
	    { 
	        System.out.print("Queue is Empty"); 
	        return; 
	    } 
	  
	    // If rear has not crossed the max size 
	    // or queue rear is still greater then 
	    // front. 
	    System.out.print("Elements in the " + 
	                     "circular queue are: "); 
	  
	    if(rear >= front) 
	    { 
	      
	        // Loop to print elements from 
	        // front to rear. 
	        for(int i = front; i <= rear; i++) 
	        { 
	            System.out.print(queue.get(i)); 
	            System.out.print(" "); 
	        } 
	        System.out.println(); 
	    } 
	  
	    // If rear crossed the max index and 
	    // indexing has started in loop 
	    else
	    { 
	          
	        // Loop for printing elements from 
	        // front to max size or last index 
	        for(int i = front; i < size; i++) 
	        { 
	            System.out.print(queue.get(i)); 
	            System.out.print(" "); 
	        } 
	  
	        // Loop for printing elements from 
	        // 0th index till rear position 
	        for(int i = 0; i <= rear; i++) 
	        { 
	            System.out.print(queue.get(i)); 
	            System.out.print(" "); 
	        } 
	        System.out.println(); 
	    } 
	} 
	
	public static void main(String[] args) 
	{ 
	      
	    CircularQueue q = new CircularQueue(5); 
	      
	    q.enQueue(10);
	    q.enQueue(20);
	    q.enQueue(30);
	    q.enQueue(40);
	    //q.enQueue(50);
	    System.out.println("Front value at index "+q.front);
	    System.out.println("Rear value at index "+q.rear);
	    q.printQueue();
	      
	} 
}
