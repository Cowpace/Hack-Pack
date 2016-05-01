
public class MCM {

    public static void main(String[] args) {
        MCM mcm = new MCM(5);
        mcm.intialize();
        System.out.println(mcm.minMCM());
    }

    public int matrix[][];
    public int dimensions[];

    public MCM (int numMatrix) {
        this.matrix = new int[numMatrix][numMatrix];
        this.dimensions = new int[numMatrix+1];
    }

    //Intialize the matrix here
    public void intialize() {

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {

                if (i == j) this.matrix[i][j] = 0;
                else this.matrix[i][j] = Integer.MAX_VALUE;

            }
        }

        this.dimensions[0] = 2;
        this.dimensions[1] = 4;
        this.dimensions[2] = 2;
        this.dimensions[3] = 3;
        this.dimensions[4] = 1;
        this.dimensions[5] = 4;

    }

    public int minMCM() {
        for(int i = 1; i <= matrix.length; i++) {

            for(int j = 0; j <= matrix.length - i; j++) {

                for(int k = j; k < j+i-1; k++) {

                    int current = matrix[j][j+i-1];
                    int alternative = matrix[j][k]+matrix[k+1][j+i-1] + dimensions[j]*dimensions[k+1]*dimensions[i+j];

                    if(current > alternative) {
                        matrix[j][j+i-1] = alternative;
                    }

                }
            }
        }
        //The least cost of MCM
        return matrix[0][matrix.length - 1];
    }


}
