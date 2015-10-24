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
    HashMap<Point, Double> costTable;
    
    public Points() {
        points = new HashSet<>();
        neighbourTable = new HashMap<>();
        costTable = new HashMap<>();
    }
    
    public void addPoint(Point p) {
        points.add(p);
    }
    
    public boolean addNeighbour(Point p, Point n) {
        if(exists(p) && exists(n)) {
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
            return true;
        }
        else {
            return false;
        }
    }
    
    public Point getPoint(double x, double y) throws NoSuchElementException {
        
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        
        throw new NoSuchElementException();
        
    }
    
    public boolean exists(Point p) {
        try {
            this.getPoint(p.getX(), p.getY());
        }
        catch(NoSuchElementException e) {
            return false;
        }
        return true;
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
    
    public boolean removeNeighbour(Point p, Point n) {
        HashSet<Point> neighbours = (HashSet)neighbourTable.get(p);
        if(neighbours.remove(n)) {
            neighbourTable.replace(p, neighbours);
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Points:\n" + points.toString()+ "\n\n Neighbours:\n" + neighbourTable.toString();
    }
    
    
    
    
}
