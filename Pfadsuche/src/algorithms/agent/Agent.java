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
import java.util.List;

/**
 *
 * @author Roland
 */
public class Agent {
    final PointList points = new PointList();
    int cost;
    Point2D.Double position;
    
    public Agent() {
        cost = 0;
        position = null;
    }
    
    public List<Point> search(World w) {
        ArrayList<Point> path = new ArrayList<>();
        Point target = points.getPointById("Z");
        boolean goal = false;
        
        
        //Suche implementieren
        while(!goal) {
            ArrayList<Point> ap = w.getAvailablePoints();
            double distance = 1000000;
            Point nextPoint = null;
            
            //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
            for(Point p : ap) {
                if(p.distance((target)) < distance) {
                    distance = p.distance(target);
                    nextPoint = p;
                }
            }
        }
        return path;
    }
    
}
