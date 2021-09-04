package week2;

public class Parenthesis {
	
	boolean isBalanced(String data) {
		String openBracket = "[{(";
		String closeBracket = "]})";
		int size = data.length();
		
		Stack stack = new Stack(size);
		
		for(int i=0; i<size; i++) {
			char bracket = data.charAt(i);
			if(openBracket.indexOf(bracket) != -1) {
				stack.push(bracket);
			}
			else {
				int index = closeBracket.indexOf(bracket);
				char correspond = openBracket.charAt(index);
				if(stack.pop() != correspond) {
					return false;
				}
			}
		}
		
		if(stack.peek() == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		String parenthesis = "{()}{}";
		Parenthesis par = new Parenthesis();
		System.out.println(par.isBalanced(parenthesis));
		
	}
	
}
