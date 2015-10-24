/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
/**
 *
 * @author Roland
 */
public class Points {
    
    HashSet<Point> points;
    HashMap<Point, HashSet<Point>> neighbourTable;
    
    public Points() {
        points = new HashSet<>();
        neighbourTable = new HashMap<>();
    }
    
    public void addPoint(Point p) {
        points.add(p);
    }
    
    public void addNeighbour(Point p, Point n) {
        HashSet<Point> neighbours = (HashSet)neighbourTable.get(p);
        if(neighbours == null) {
            neighbours = new HashSet<>();
            neighbours.add(n);
            neighbourTable.put(p, neighbours);
        }
        else {
            neighbours.add(n);
            neighbourTable.replace(p, neighbours);
        }
    }
    
    public Point getPoint(double x, double y) {
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        
        throw new NoSuchElementException();
    }
    
    public HashSet<Point> getAllPoints() {
        return (HashSet<Point>) points.clone();
    }
    
    public HashSet<Point> getNeighbours(Point p) {
        return neighbourTable.get(p);
    }
    
    public boolean removePoint(Point p) {
        return points.remove(p);
    }
    
    public void removeNeighbour(Point p, Point n) {
        HashSet<Point> neighbours = (HashSet)neighbourTable.get(p);
        neighbours.remove(n);
        neighbourTable.replace(p, neighbours);
    }
    
    
    
}
