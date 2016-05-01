public class Knapsack {

    //Take it or leave it -> Knapsack DP
    public static int knapsack(int value[], int weight[], int totalWeight) {
        
        int matrix[][] = new int[value.length + 1][totalWeight + 1];
        
        for(int i = 0 ; i <= value.length; i++) {
            
            for(int j = 0; j <= totalWeight; j++) {
               
                if(i == 0 || j == 0) matrix[i][j] = 0;
                
                else {
                    //Take it
                    if(j - weight[i-1] >= 0) {
                        matrix[i][j] = Math.max(matrix[i-1][j], value[i-1] + matrix[i-1][j-weight[i-1]]);
                    }
                    //Leave it
                    else {
                        matrix[i][j] = matrix[i-1][j];
                    }
                }
                
            }
        }
        return matrix[value.length][totalWeight];
    } 
   
}