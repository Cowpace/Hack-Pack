import java.util.*;
import java.io.*;

public class convex {

	public static void main(String[] args) throws Exception {


		Scanner in = new Scanner(System.in);
		
		int cases = in.nextInt();
		int curCase;
		
		for(int loop = 0; loop < cases; loop++)
		{
			curCase = in.nextInt();
			int numPoints = in.nextInt();
			pt[] pts = new pt[numPoints];
			
			for(int i = 0; i < numPoints; i++)
			{
				pts[i] = new pt(in.nextInt(),in.nextInt());
			}
			
			int refIndex = getIndexMin(pts, numPoints);
			pt.refX = pts[refIndex].x;
			pt.refY = pts[refIndex].y;

			
//			System.out.printf("%.1f\n", grahamScan(pts, numPoints));
			
			Stack<pt> stack = grahamScan(pts, numPoints);
			
			//st<pt> list = grahamScan(pts, numPoints);
			
			System.out.println();
			System.out.println(curCase + " " + stack.size());
			
			ArrayList<pt> list = new ArrayList<pt>();
			
			while (stack.size() > 0){
				list.add(stack.pop());
			}
			
			pt ref = list.get(0);
			int indx = 0;
			
			for (int i=1; i < list.size(); i++){
				if (list.get(i).y > ref.y || (list.get(i).y == ref.y && list.get(i).x < ref.x)){
					ref = list.get(i);
					indx = i;
				}
			}
			
			for (int i = 0; i < list.size(); i++){
				pt next = list.get((i + indx)%list.size());
				System.out.println(next.x + " " + next.y);
			}
			
			
			//while (stack.size() > 0) {
			//	pt next = stack.pop();
			//	System.out.println(next.x + " " + next.y);
			//}
		}
		
		
	}

	public static int getIndexMin(pt[] pts, int n) {
		int res = 0;
		for (int i = 1; i < n; i++)
			if (pts[i].y < pts[res].y || (pts[i].y == pts[res].y && pts[i].x < pts[res].x))
				res = i;
		return res;
	}

	public static Stack<pt> grahamScan(pt[] pts, int n) {

		Arrays.sort(pts);

		Stack<pt> stack = new Stack<pt>();
		stack.push(pts[0]);
		stack.push(pts[1]);



		
		
		for (int i = 2; i < n; i++) {

			pt cur = pts[i];
			pt mid = stack.pop();
			pt prev = stack.pop();
			

			while (!prev.isRightTurn(mid, cur) && stack.size() > 0) {
				mid = prev;
				prev = stack.pop();
			}
			stack.push(prev);
			if (stack.size() != 1){
				stack.push(mid);
			}
			stack.push(cur);
		}

//		double res = 0;
//		pt cur = pts[0];

		return stack;
	}
}

class pt implements Comparable<pt> {


	public static int refX;
	public static int refY;

	public int x;
	public int y;

	public pt(int myx, int myy) {
		x = myx;
		y = myy;
	}

	public pt getVect(pt other) {
		return new pt(other.x-x, other.y-y);
	}

	public double dist(pt other) {
		return Math.sqrt((other.x-x)*(other.x-x) + (other.y-y)*(other.y-y));
	}

	public int crossProductMag(pt other) {
		return this.x*other.y - other.x*this.y;
	}

	public boolean isRightTurn(pt mid, pt next) {
		pt v1 = getVect(mid);
		pt v2 = mid.getVect(next);
		return v1.crossProductMag(v2) > 0;
	}


	public boolean isZero() {
		return x == 0 && y == 0;
	}

	public int compareTo(pt other) {

		pt myRef = new pt(refX, refY);
		pt v1 = myRef.getVect(this);
		pt v2 = myRef.getVect(other);

		if (v1.isZero()) return -1;
		if (v2.isZero()) return 1;

		if (v1.crossProductMag(v2) != 0)
			return -v1.crossProductMag(v2);

		if (myRef.dist(v1) < myRef.dist(v2)) return -1;
		return 1;
	}
}