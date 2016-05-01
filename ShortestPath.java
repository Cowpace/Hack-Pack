import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int nodes = scan.nextInt();
		int edges = scan.nextInt();
		while (!(nodes == 0 && edges == 0)){
			int[][] matrix = new int[nodes][nodes];
			
			for (int i=0; i < edges; i++){
				int s = scan.nextInt();
				int e = scan.nextInt();
				int w = scan.nextInt();
				matrix[s][e] = w;
				matrix[e][s] = w;
			}
			
			int mode = scan.nextInt();
			
			if (mode == 0){
				System.out.println(DFS(scan.nextInt(), scan.nextInt(), matrix, new boolean[nodes], 0));
			}
			if (mode == 1){
				System.out.println(BFS(scan.nextInt(), scan.nextInt(), matrix));
			}
			if (mode == 2){
				int[][] ans = floyds(matrix);
				for (int i=0; i < ans.length; i++){
					for (int j=0; j < ans[0].length; j++){
						System.out.print(ans[i][j] + " ");
					}
					System.out.println();
				}
			}
			
			nodes = scan.nextInt();
			edges = scan.nextInt();
		}
		
		
		
	}
	
	public static int DFS(int cur, int tar, int[][] matrix, boolean[] visited, int depth){
		if (cur == tar)
			return depth;
		visited[cur] = true;
		int ans = Integer.MAX_VALUE;
		for (int i=0; i < matrix[cur].length; i++){
			if (!visited[i] && matrix[cur][i] > 0){
				ans = Math.min(ans, DFS(i, tar, matrix, visited, depth + 1));
			}
		}
		return ans;
	}
	
	public static int BFS(int start, int tar, int[][] matrix){
		int cur = start;
		int value = -1;
		
		ArrayList<Integer> queue = new ArrayList<Integer>();
		//a separate queue for distance from start. You also could be using a tuple class in one queue
		ArrayList<Integer> dist = new ArrayList<Integer>(); 
		queue.add(cur);
		dist.add(0);
		
		boolean[] v = new boolean[matrix.length];
		
		while (!queue.isEmpty()){
			//pop queue
			cur = queue.get(0);
			queue.remove(0);
			v[cur] = true;
			value = dist.get(0);
			dist.remove(0);
			//check win condition
			if (cur == tar)
				break;
			//add adj nodes that are not visited
			for (int i=0; i < matrix[cur].length; i++){
				if (matrix[cur][i] > 0 && !v[i]){
					queue.add(i);
					dist.add(value + 1);
				}
			}
		}
		return value;
	}
	
	public static int[][] floyds(int[][] matrix){
		//copy array to preserve argument
		int[][] ans = new int[matrix.length][matrix[0].length];
		for (int i=0; i < matrix.length; i++){
			for (int j=0; j < matrix[0].length; j++){
				ans[i][j] = matrix[i][j];
			}
		}
		//floyds
		for (int k=0; k < matrix.length; k++){
			for (int i=0; i < matrix.length; i++){
				for (int j=0; j < matrix.length; j++){
					ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
				}
			}
		}
		return ans;
	}

}
