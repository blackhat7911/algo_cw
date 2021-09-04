package week2;

public class SumTwoStack {
	
	public int pop_max(int k, int[] stack1, int[] stack2) {
		 
        int stack1_index = 0;
        int stack2_index = 0;
        int count = 0;
        int sum = 0;
        
        // move stack2_index to the position where if only take elements from stk_2, last element it can take
        while (stack2_index < stack2.length && sum + stack2[stack2_index] <= k) {
            sum += stack2[stack2_index];
            stack2_index++;
        }
        
        count = stack2_index;
        for(stack1_index=1; stack1_index <= stack1.length; stack1_index++) {
        	sum += stack1[stack1_index - 1];

            
            while(sum > k && stack2_index > 0) {
            	stack2_index--;
            	sum -= stack2[stack2_index];
            }
            
            if(sum > k) {
            	break;
            }
            count = Math.max(count, stack1_index + stack2_index);
        }
        
 
        return count;
    }
 
    public static void main(String[] args) {
 
        int k = 11;
        int stack1[] = {4, 3, 6, 7, 9};
        int stack2[] = {1, 2, 9, 5};
        
        SumTwoStack stackObj = new SumTwoStack();
        
        int count = stackObj.pop_max(k, stack1, stack2);
        System.out.println("Result : " + count);
        
    }
	
}
