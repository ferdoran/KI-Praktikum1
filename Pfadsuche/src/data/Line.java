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
public class Line {
    Point pointFrom;
    Point pointTo;
    
    
    public Line(Point pFrom, Point pTo) {
        pointFrom = pFrom;
        pointTo = pTo;
        
    }
    
    public Point getPointFrom() {
        return pointFrom;
    }
    
    public Point getPointTo() {
        return pointTo;
    }
    
    
    
}
