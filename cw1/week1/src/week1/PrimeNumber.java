package week1;
import java.util.*;

public class PrimeNumber {
	
	// check whether number is prime or not
    public boolean isPrime(int num) {
        boolean flag = true;
        if(num==2) {
            return flag;
        }
        else if (num % 2 == 0){
            flag = false;
            return flag;
        } else {
            for(int i=3; i<=Math.sqrt(num); i += 2) {
                if(num%i == 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }       
    }
    
    public boolean check(int val, int arr[]) {
        boolean flag = false;
        for(int i=0; i<arr.length; i++) {
            if (arr[i] == val) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    public static void main(String[] args) {
        int arr[] = {2, 3, 5, 11, 17, 23};
        int last = arr[arr.length-1];
        ArrayList<Integer> remaining = new ArrayList<Integer>();
        
        PrimeNumber p = new PrimeNumber();
        for(int i=2; i<=last; i++) {
            if (!p.check(i, arr)) {
                if (p.isPrime(i)) {
                    remaining.add(i);
                }
            }
        }
        
        System.out.println("Given array:");
        for(int a: arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
        
        System.out.println("Missing prime numbers: " +remaining);
    }
}