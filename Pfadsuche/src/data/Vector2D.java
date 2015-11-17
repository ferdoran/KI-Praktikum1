package data;

/**
 *
 * @author Roland
 */
public class Vector2D {
    
    private double x;
    private double y;
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2D(Point a, Point b) {
        x = b.getX() - a.getX();
        y = b.getY() - a.getY();
    }
    
    public Vector2D add(Vector2D v) {
        return new Vector2D(x+v.getX(), y+v.getY());
    }
    
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(x-v.getX(), y-v.getY());
    }
    
    public Vector2D opposite() {
        return new Vector2D(this.x * -1, this.y * -1);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    public static Vector2D createVectorFromPoint(Point a) {
        return new Vector2D(a.getX(), a.getY());
    }
    
    public Point getPoint() {
        return new Point(x,y,"Vector"+x+y);
    }
    
}
