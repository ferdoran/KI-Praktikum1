/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package algorithms.agent;

import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 *
 * @author Roland
 */
public class Agent {
    final PointList points = new PointList();
    
    Point2D.Double position;
    
    public Agent() {
        position = null;
    }
    
    public double search(World w) {
        int cost = 0;
        Point target = points.getPointById("Z");
        boolean goal = false;
        
        
        //Suche implementieren
        while(!goal) {
            ArrayList<Point> ap = w.getAvailablePoints();
            double distance = 1000000;
            Point nextPoint = null;
            
            //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
            for(Point p : ap) {
                if(p.equals(target)) {
                    cost += p.distance(target);
                    cost -= 100;
                    return cost;
                }
                if(p.distance((target)) < distance) {
                    distance = p.distance(target);
                    nextPoint = p;
                }
                
            }
            w.setAgentPosition(nextPoint);
            cost += distance;
        }
        return cost;
    }
    
    private Point2D.Double calcPosition(World w) {
        ArrayList<Point> ap = w.getAvailablePoints();
    }
    
}
