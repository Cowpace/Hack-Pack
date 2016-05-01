// Arup Guha
// 4/15/2016
// Solution to UCF Practice Problem: Intersect (Pl/Line intersection)

import java.util.*;

public class intersect {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int numCases = stdin.nextInt();

		// Process each case.
		for (int loop=1; loop<=numCases; loop++) {

			// Case header.
			System.out.println("Data Set #"+loop+":");

			// Read in the line.
			pt[] ptsOnLine = new pt[2];
			for (int i=0; i<ptsOnLine.length; i++) {
				double x = stdin.nextDouble();
				double y = stdin.nextDouble();
				double z = stdin.nextDouble();
				ptsOnLine[i] = new pt(x,y,z);
			}
			line L = new line(ptsOnLine[0], ptsOnLine[1]);

			// Read in the plane.
			pt[] ptsOnPlane = new pt[3];
			for (int i=0; i<ptsOnPlane.length; i++) {
				double x = stdin.nextDouble();
				double y = stdin.nextDouble();
				double z = stdin.nextDouble();
				ptsOnPlane[i] = new pt(x,y,z);
			}
			plane Pl = new plane(ptsOnPlane[0], ptsOnPlane[1], ptsOnPlane[2]);

			// Get the intersection.
			pt res = Pl.intersect(L);

			// Regular case.
			if (res != null)
				System.out.printf("The intersection is the point (%.1f, %.1f, %.1f).\n", res.x, res.y, res.z);

			// L on plane.
			else if (Pl.onPlane(L.start))
				System.out.println("The line lies on the plane.");

			// They don't touch, ever!
			else
				System.out.println("There is no intersection.");

			// Between cases.
			System.out.println();
		}
	}
}

class pt {

	public double x;
	public double y;
	public double z; //only used in 3D geometry

	public pt(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;//only used in 3D geometry
	}

	// Returns the vector
	public vector getvector(pt next) {
		return new vector(next.x-x, next.y-y, next.z-z);
	}
}

class vector {

	public double x;
	public double y;
	public double z;//only used in 3D geometry

	public vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;//only used in 3D geometry
	}

	//returns cross product of 2 vectors
	public vector crossProduct(vector next) {
		return new vector(y*next.z-next.y*z, z*next.x-next.z*x, x*next.y-next.x*y);
	}
	
	//returns vector of vector
	public double magnitude() {
		return Math.sqrt(x*x+y*y+z*z);
	}
}

class line {

	public pt start;
	public pt end;
	public vector dir;

	public line(pt start, pt end) {
		this.start = s;
		this.end = e;
		dir = start.getvector(end); //paramatrizes the vector
	}
	
	//returns point on the line from paramtrized form
	public pt getPt(double t) {
		return new pt(start.x+dir.x*t, start.y+dir.y*t, start.z+dir.z*t);
	}
}

class plane {

	public pt p1;
	public pt p2;
	public pt p3;
	public vector normal;
	public double d;

	public plane(pt a, pt b, pt c) {
		p1 = a;
		p2 = b;
		p3 = c;
		vector v1 = p1.getvector(p2);
		vector v2 = p1.getvector(p3);
		normal = v1.cross(v2);

		//solves for D
		d = normal.x*p1.x + normal.y*p1.y + normal.z*p1.z;
	}

	public pt intersect(line L) {

		//T coeffienct
		double tCoeff = normal.x*L.dir.x + normal.y*L.dir.y + normal.z*L.dir.z;
		if (Math.abs(tCoeff) < 1e-9) return null;

		//solve for the parameter
		double rhs = d - normal.x*L.start.x - normal.y*L.start.y - normal.z*L.start.z;

		//ONLY USE IF LINE SEGMENT
		double t = rhs/tCoeff;
		if (t < NEG_INF || t > INF) return null; // return null if intersection not on line segment
		
		
		return L.getPt(rhs/tCoeff);
	}
	
	//checks if point p is on plane
	public boolean onPlane(pt p) {
		return normal.x*p.x + normal.y*p.y + normal.z*p.z == d;
	}
}




