package week3;

import java.util.Stack;

public class PostfixEval {
	
	   static int evalPostfix(String express) 
	    { 
	        Stack<Integer> st = new Stack<>(); 
	        
	        /* loop to scan all elements of the expression one by one */
	        for(int i=0; i < express.length(); i++) 
	        { 
	            char ch = express.charAt(i); 
	             
	            if(Character.isDigit(ch)) 
	            /* pushing value into the stack */
	            st.push(ch - '0'); 
	              
	            /* if the operator comes it will be evaluated */
	            else       
	            { 
	                int value1 = st.pop(); 
	                int value2 = st.pop(); 
	                  
	                switch(ch) 
	                { 
	                    case '+': 
	                    st.push(value2 + value1); 
	                    break; 
	                      
	                    case '-': 
	                    st.push(value2 - value1); 
	                    break; 
	                        
	                    case '*': 
	                    st.push(value2*value1); 
	                    break; 
	                    
	                    case '/': 
	                    st.push(value2/value1); 
	                    break; 
	              } 
	            } 
	        } 
	        return st.pop();    
	    } 
	      	
}
