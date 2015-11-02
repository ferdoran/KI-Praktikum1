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
public class PointList {
    
    HashSet<Point> points;
    HashMap<Point, HashSet<Point>> neighbourTable;
    
    
    public PointList() {
        points = new HashSet<>();
        neighbourTable = new HashMap<>();
        
        //Definition der Punkte
        
        //Start und Zielpunkt
        points.add(new Point(100,534, "S"));
        points.add(new Point(388,663, "Z"));
        
        //Polygon1
        points.add(new Point(220,616, "P11"));
        points.add(new Point(220,666, "P12"));
        points.add(new Point(251,670, "P13"));
        points.add(new Point(272,647, "P14"));
        
        //Polygon2
        points.add(new Point(341,655, "P21"));
        points.add(new Point(359,667, "P22"));
        points.add(new Point(374,651, "P23"));
        points.add(new Point(366,577, "P24"));
        
        //Polygon3
        points.add(new Point(311,530, "P31"));
        points.add(new Point(311,559, "P32"));
        points.add(new Point(339,578, "P33"));
        points.add(new Point(361,560, "P34"));
        points.add(new Point(361,528, "P35"));
        points.add(new Point(336,516, "P36"));
        
        //Polygon4
        points.add(new Point(105,628, "P41"));
        points.add(new Point(151,670, "P42"));
        points.add(new Point(180,629, "P43"));
        points.add(new Point(156,577, "P44"));
        points.add(new Point(113,587, "P45"));
        
        //Polygon5
        points.add(new Point(118,517, "P51"));
        points.add(new Point(245,517, "P52"));
        points.add(new Point(245,557, "P53"));
        points.add(new Point(118,557, "P54"));
        
        //Polygon6
        points.add(new Point(280,583, "P61"));
        points.add(new Point(333,583, "P62"));
        points.add(new Point(333,665, "P63"));
        points.add(new Point(280,665, "P64"));
        
        //Polygon7
        points.add(new Point(252,594, "P71"));
        points.add(new Point(290,562, "P72"));
        points.add(new Point(264,538, "P73"));
        
        //Polygon8
        points.add(new Point(198,635, "P81"));
        points.add(new Point(217,574, "P82"));
        points.add(new Point(182,574, "P83"));
        
        
        
    }
    
    public boolean addPoint(Point p) {
        if(!exists(p)) {
           points.add(p);
           return true;
        }
        else {
            return false;
        }
            
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
                if(!neighbourTable.get(p).contains(n)) {
                    neighbours.add(n);
                    neighbourTable.replace(p, neighbours);
                }
                else {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public Point getPoint(double x, double y) {
        
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        
        return null;
        
    }
    
    public Point getPointById(String id) {
        for(Point p: points)  {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
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
    
    public boolean exists(double x, double y) {
        try {
            getPoint(x, y);
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
    
    public boolean removePoint(double x, double y) {
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return points.remove(p);
            }
        }
        return false;
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
