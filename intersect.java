import java.util.Arrays;
import java.util.Scanner;

public class intersect {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int cases = in.nextInt();

		for (int loop = 0; loop < cases; loop++) {
			line l = new line(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(),
					in.nextDouble());
			plane p = new plane(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(),
					in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());

			double pt[] = { 0, 0, 0 };

			System.out.println("Data Set #"+(loop+1)+":");
			
			if (l.x1 * p.i3 + l.y1 * p.j3 + l.z1 * p.k3 == p.D) {
				System.out.println("The line lies on the plane.\n");
			} else {

				//algebra
				//solve for demoninator
				double dem = p.i3 * l.i + p.j3 * l.j + p.k3 * l.k;
				
				//solve for subtraction
				double sub = p.i3 * l.x1 + p.j3 * l.y1 + p.k3 * l.z1;
				
				//solve lambda
				double lambda = (p.D - sub) / dem;

				//if lambda is inf, does not intersect *limit here if line segment
				if (Double.isInfinite(lambda) || Double.isNaN(lambda)) {
					System.out.println("There is no intersection.\n");

				} else {
					
					pt[0] = l.x1 + l.i * lambda;
					pt[1] = l.y1 + l.j * lambda;
					pt[2] = l.z1 + l.k * lambda;
					System.out.printf("The intersection is the point (%.1f, %.1f, %.1f).\n\n",pt[0],pt[1],pt[2]);
				}

			}
		}
	}

}

class plane {

	public double x1, x2, x3, y1, y2, y3, z1, z2, z3;

	public double i1, j1, k1;
	public double i2, j2, k2;

	public double i3, j3, k3;

	public double D;

	public plane(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
		this.x1 = x1;
		this.x2 = x3;
		this.x3 = x2;

		this.y1 = y1;
		this.y2 = y3;
		this.y3 = y2;

		this.z1 = z1;
		this.z2 = z3;
		this.z3 = z2;
		
		//parametrize
		i1 = x2 - x1;
		i2 = x3 - x1;

		j1 = y2 - y1;
		j2 = y3 - y1;

		k1 = z2 - z1;
		k2 = z3 - z1;
		
		//solve cross product
		i3 = j1 * k2 - j2 * k1;
		j3 = -(i1 * k2 - i2 * k1);
		k3 = i1 * j2 - i2 * j1;
		
		//reduce coeff
		D = Math.abs(gcd(i3, gcd(j3, k3)));

		if (D != 0) {
			i3 /= D;
			j3 /= D;
			k3 /= D;
		}
		
		//solve for D
		D = i3 * x1 + j3 * y1 + k3 * z1;
	}

	double gcd(double x, double y) {
		double k = x;
		double m = y;
		if (x == 0 || y == 0)
			return 0;
		while (Math.abs(k - m) < 1e-9) {
			if (k > m) {
				k = k - m;
			} else {
				m = m - k;
			}
		}
		return k;
	}

}

class line {

	public double x1, y1, z1;
	public double x2, y2, z2;

	public double i, j, k;

	public line(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;

		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		
		//parametrize
		i = x2 - x1;
		j = y2 - y1;
		k = z2 - z1;

	}

}