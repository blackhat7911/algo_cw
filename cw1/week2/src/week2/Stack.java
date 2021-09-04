package week2;

public class Stack {
	int size;
	char stack[];
	int top = -1;
	
	Stack(int size){
		this.size = size;
		stack = new char[size];
	}
	
	// function to check whether stack is full or not
	boolean isFull() {
		return top == size-1;
	}
	
	// function to check whether stack is empty or not
	boolean isEmpty() {
		return top == -1;
	}
	
	void push(char data) {
		if(isFull()) {
			System.out.println("Stack is full");
		}
		else {
			stack[++top]=data;
		}
	}
	
	int pop() {
		if(isEmpty()) {
			System.out.println("Stack underflow : pop");
			return -1;
		}
		else {
			return stack[top--];
		}
	}
	
	int peek() {
		if(isEmpty()) {
			System.out.println("Stack underflow : peek");
			return -1;
		}
		else {
			return stack[top];
		}
	}
	
	void printStack() {
		System.out.println("Start printing stack value");
		for(int i=0; i<=top; i++) {
			System.out.println(stack[i]);
		}
	}
	
}
