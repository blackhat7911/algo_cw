package week1;
import java.util.*;
public class Permutations { 
	
	// function to convert binary to decimal
    public int binaryToDecimal(char list[]) {
        int decimal = 0;
        int power = 0;
        for(int i=list.length-1; i>=0; i--) {           
            decimal += Integer.parseInt(String.valueOf(list[i])) * (int)Math.pow(2, power);
            power++;
        }
        
        return decimal;
    }
       
    public LinkedList<Integer> convertToDecimal(LinkedHashSet<String> unique_permutations) {
        LinkedList<Integer> decimal_num_list = new LinkedList<Integer>();
        for (String val: unique_permutations) {
            char[] individual_number = val.toCharArray();
            int decimal_value = binaryToDecimal(individual_number);
            decimal_num_list.add(decimal_value);
        }
        return decimal_num_list;
    }
    
    // function to permute
    public void permute(String str, LinkedHashSet<String> all_binary_permutes, int left, int right) { 
        if (left == right) {
            all_binary_permutes.add(str);   
        }
      
        else { 
            for (int i = left; i <= right; i++) { 
                str = swap(str, left, i); 
                permute(str, all_binary_permutes, left+1, right); 
                str = swap(str,left,i); 
            } 
        } 
    } 
 
 
    public String swap(String data, int i, int j) { 
        char temp; 
        char[] charArray = data.toCharArray(); 
        temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    } 
    
    public String convertToString(LinkedList<Integer> number) {
        String converted = new String("");
        for(int i=0; i<number.size(); i++) {
            converted += number.get(i);
        }
        return converted;
    }
    
    public static void main(String[] args) { 
        Permutations permutation = new Permutations(); 
                         
        //value for input numbers
        LinkedList<Integer> input = new LinkedList<Integer>();
        input.add(1);
        input.add(0);
        input.add(1);
                
        //converting linked list to string
        String input_number = permutation.convertToString(input);
        System.out.println("Inputs: "+input_number);
        
        //getting length or size
        int lngth = input_number.length(); 
        
        //LinkedHashset so that no permutes are repeated
        LinkedHashSet<String> all_binary_permutes = new LinkedHashSet<String>();
        permutation.permute(input_number, all_binary_permutes, 0, lngth-1);
        System.out.println(all_binary_permutes);
        
        //converting unique binary permutes to decimal value
        LinkedList<Integer> result = permutation.convertToDecimal(all_binary_permutes);
        for(int k=0; k<result.size(); k++) {
            System.out.println(result.get(k));
        }
    } 
    
 
}