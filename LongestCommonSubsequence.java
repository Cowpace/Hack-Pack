import java.util.*;

public class LongestCommonSubsequence {
        
    //Use dynamic programming to solve lcs iteratively
    public static String lcs(char[] str1, char[] str2) {
        
        int matrix[][] = new int[str1.length + 1] [str2.length + 1];
        
        for(int i = 1; i < str1.length + 1; i++) {
            for(int j = 1; j < str2.length + 1; j++) {
                if(str1[i - 1] == str2[j - 1]) {
                    matrix[i][j] = matrix[i - 1] [j - 1] + 1;
                }
                else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        //Create a character array with length of the longest subsequence
        char[] answer = new char[matrix[str1.length][str2.length]];
        int index = answer.length - 1;
        
        int a = str1.length;
        int b = str2.length;
        
        //Backtrack the matrix to find the actual subsequence        
        while(a != 0 && b != 0) {
            if(matrix[a][b] == matrix[a][b - 1]) {
                b -= 1;
            }
            else if(matrix[a][b] == matrix[a-1][b]) {
                a -= 1;
            }
            else {
                answer[index--] = str1[a - 1];
                a -= 1;
                b -= 1;
            }
        }
        
        return new String(answer);
       
    }
}