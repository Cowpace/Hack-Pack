/*
 * A program that solves a bipartide matching problem using Edmond Karp in O(VE^2)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Edge{
    public int start;
    public int end;
    public int flow;
    public int cap;
    
    public Edge(int s, int e, int c){
        start = s;
        end = e;
        flow = 0;
        cap = c;
    }
    
    public int maxPushForward(){
        return cap - flow;
    }
    
    public int maxPushBack(){
        return flow;
    }
    
    public boolean pushForward(int moreFlow){
        if(flow + moreFlow > cap){
            return false;
        }
        
        flow += moreFlow;
        return true;
    }
    
    public boolean pushBack(int lessFlow){
        if(flow < lessFlow){
            return false;
        }
        
        flow -= lessFlow;
        return true;
    }
    
    public boolean isForwardEdge(int s){
        return s == this.start;
    }
    public String toString(){
    	String str = flow + "/" + cap;
    	while (str.length() < 5)
    		str += " ";
    	return str;
    }
}

public class EdmondKarp {
	public static int SRC = 52;
    public static int SINK = 53;
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int runs = scan.nextInt();
		
		for (int i=1; i <= runs; i++){
			System.out.print("Case #" + i + ": ");
			//setup graph
			Edge[][] matrix = new Edge[SINK+1][SINK+1];
            
			//src to set1
            for (int j=0; j < 10; j++){
                Edge e = new Edge(SRC, j, 20);
                matrix[SRC][j] = e;
                matrix[j][SRC] = e;
            }
            
            //set2 to sink
            for (int j=10; j < 52; j++){
                Edge e = new Edge(j, SINK, 3);
                matrix[SINK][j] = e;
                matrix[j][SINK] = e;
            }
            
            //set1 to set2
            for (int student=0; student < 10; student++){
            	int[] avail = new int[42];
            	Arrays.fill(avail, 1);
            	int I = scan.nextInt();
            	for (int j=0; j < I; j++){
            		int day = scan.nextInt();
            		int start = scan.nextInt();
            		int end = scan.nextInt();
            		
            		int offset = (day-1)*6;
            		
            		for (int k=start/4; k < (end + 3)/4; k++){
            			avail[offset + k] = 0;
            		}
            		
            	}
            	for (int j=0; j < 42; j++){
            		if (avail[j] == 1){
            			Edge e = new Edge(student, j+10, 1);
                        matrix[student][j+10] = e;
                        matrix[j+10][student] = e;
            		}
            	}
            }
			//begin edmond-karp O(VE^2)
			int ans = 0;
            ArrayList<Integer> path = getAugmentingPath(matrix, SRC, new ArrayList<Integer>(), new boolean[SINK+1]);
            
            while (path != null){
                int flow = getPathFlow(matrix, path);
                ans += flow;
                applyFlow(matrix, flow, path);
                path = getAugmentingPath(matrix, SRC, new ArrayList<Integer>(), new boolean[SINK+1]);
            }
            //end
            if (ans == 126)
            	System.out.println("YES");
            else
            	System.out.println("NO");
        	System.out.println();
		}
		scan.close();
	}
	
	public static ArrayList<Integer> getAugmentingPath(Edge[][] matrix, int cur, ArrayList<Integer> ans, boolean[] v){
        v[cur] = true;
        ArrayList<Integer> newPath = new ArrayList<Integer>();
        for (Integer item : ans){
            newPath.add(item.intValue());
        }
        newPath.add(cur);
        if (cur == SINK)
            return newPath;
        
        for (int i=0; i < matrix[cur].length; i++){
            if (matrix[cur][i] != null && !v[i]){
                ArrayList<Integer> ret = null;
                Edge e = matrix[cur][i];
                if (e.isForwardEdge(cur) && e.maxPushForward() > 0){
                    ret = getAugmentingPath(matrix, i, newPath, v);
                } else if (!e.isForwardEdge(cur) && e.maxPushBack() > 0){
                    ret = getAugmentingPath(matrix, i, newPath, v);
                }
                if (ret != null)
                    return ret;
            }
        }
        return null;
    }
    
    public static int getPathFlow(Edge[][] matrix, ArrayList<Integer> path){
        int temp = path.get(0); //pass by reference sucks
        int s = temp;
        path.remove(0);
        int ans = 9999999;
        for (Integer thing : path){
            if (matrix[s][thing].isForwardEdge(s)){
                ans = Math.min(ans, matrix[s][thing].maxPushForward());
            } else {
                ans = Math.min(ans, matrix[s][thing].maxPushBack());
            }
            s = thing;
        }
        path.add(0, temp);
        return ans;
    }
    
    public static void applyFlow(Edge[][] matrix, int flow, ArrayList<Integer> path){
        int temp = path.get(0);
        int s = temp;
        path.remove(0);
        for (Integer thing : path){
            if (matrix[s][thing].isForwardEdge(s)){
                matrix[s][thing].pushForward(flow);
            } else {
                matrix[s][thing].pushBack(flow);
            }
            s = thing;
        }
        path.add(0, temp);
    }

}
