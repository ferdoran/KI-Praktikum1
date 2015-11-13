/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
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
    
    public void add(Vector2D v) {
        x += v.getX();
        y += v.getY();
    }
    
    public void subtract(Vector2D v) {
        x -= v.getX();
        y -= v.getY();
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
    
}
