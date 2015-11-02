package data;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mahdi
 */
public class Point {
    protected double x;
    protected double y;
    protected String id;
    protected HashSet<Point> neighbours;
    
    public Point(double x, double y, String id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.neighbours = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public void addNeighbour(Point n){
        this.neighbours.add(n);        
    }
    
    public HashSet<Point> getNeighbours(){
        return neighbours;
    }
}
