package data;

import java.awt.geom.Point2D;
import java.util.HashSet;

/**
 *
 * @author Mahdi
 */
public class Point extends Point2D.Double {
    protected String id;
    protected HashSet<Point> neighbours;
    protected HashSet<Point> polygonNeighbours;
    
    public Point(double x, double y, String id){
        super(x, y);
        this.id = id;
        this.neighbours = new HashSet<>();
        this.polygonNeighbours = new HashSet<>();
    }

    public String getId() {
        return id;
    }
    
    public void addNeighbour(Point n){
        this.neighbours.add(n);        
    }
    
    public void addPolygonNeighbour(Point n){
        this.polygonNeighbours.add(n);        
    }
    
    public HashSet<Point> getNeighbours(){
        return neighbours;
    }
    
    public boolean isNeighbourOf(Point p) {
        return neighbours.contains(p);
    }
    
    public boolean isPolygonNeighbourOf(Point p) {
        return polygonNeighbours.contains(p);
    }
    
    
    public boolean equals(Point p) {
        if(this.getX() == p.getX() && this.getY() == p.getY()) {
            return true;
        }
        else {
            return false;
        }
    }
}
