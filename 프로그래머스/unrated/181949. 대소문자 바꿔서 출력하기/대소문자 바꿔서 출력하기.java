import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        char[] arr = a.toCharArray();
        int test1 = 'a';
        int test2 = 'A';
       
        for(int i = 0 ; i < arr.length ; i++){
            int target = arr[i];
            if(target<97){
                char result = (char)(target + 32);
                System.out.print(result);
            }else{
                char result = (char)(target - 32);
                System.out.print(result);
            }
        }
    }
}