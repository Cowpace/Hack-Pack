

public class birdman {

    public static void main(String[] args) {

        pt2D a = new pt2D(1, 2);
        pt2D b = new pt2D(5, 1);
        pt2D c = new pt2D(5, 2);
        pt2D d = new pt2D(5,4);

        line first = new line(a, b);
        line second = new line(c, d);
        boolean intersect = first.intersect(second);
        System.out.println(intersect);

    }
}

class vect2D {

    public double x;
    public double y;

    public vect2D(double myx, double myy) {
        x = myx;
        y = myy;
    }

    public vect2D(pt2D start, pt2D end) {
        x = end.x - start.x;
        y = end.y - start.y;
    }

    public double dot(vect2D other) {
        return this.x*other.x + this.y*other.y;
    }

    public double mag() {
        return Math.sqrt(x*x+y*y);
    }

    public double angle(vect2D other) {
        return Math.acos(this.dot(other)/mag()/other.mag());
    }

    public double signedCrossMag(vect2D other) {
        return this.x*other.y-other.x*this.y;
    }

    public double crossMag(vect2D other) {
        return Math.abs(signedCrossMag(other));
    }
}

class line {

    final public static double EPSILON = 1e-9;

    public pt2D p;
    public vect2D dir;

    public line(pt2D start, pt2D end) {
        p = start;
        dir = new vect2D(start, end);
    }

    public boolean intersect(line other) {
 
        // This is the denominator we get when setting up our system of equations for
        // our two parametric line parameters.
        double den = det(dir.x, -other.dir.x, dir.y, -other.dir.y);
        if (Math.abs(den) < EPSILON) return false;

        // We already have the denominator, now solve for the numerator for lambda, the
        // parameter for this line. Then return the resultant point.
        double numLambda = det(other.p.x-p.x, -other.dir.x, other.p.y-p.y, -other.dir.y);
        double t = numLambda / den;
        
        if(t < 0 || t > 1) return false;
        
        double numLambda2 = det(dir.x,  -other.dir.x, other.p.x, -other.dir.x);
        double s = numLambda2 /den;
        
        if(s < 0 || s > 1)return false;
        
        return true;
        
        
    }

    // Returns the shortest distance from other to this line. Sets a vector from the starting
    // point of this line to other and uses the cross product with that vector and the direction
    // vector of the line.
    public double distance(pt2D other) {
        vect2D toPt = new vect2D(p, other);
        return dir.crossMag(toPt)/dir.mag();
    }

    // Returns the point on this line corresponding to parameter lambda.
    public pt2D eval(double lambda) {
        return new pt2D(p.x+lambda*dir.x, p.y+lambda*dir.y);
    }

    public static double det(double a, double b, double c, double d) {
        return a*d - b*c;
    }
}

class pt2D {

    public double x;
    public double y;

    public pt2D(double myx, double myy) {
        x = myx;
        y = myy;
    }
}
