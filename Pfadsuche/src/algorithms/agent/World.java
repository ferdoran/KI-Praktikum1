/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package algorithms.agent;

import data.LineList;
import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Roland
 */
public class World {
    
    final PointList points = new PointList();
    final LineList lines;
    Point2D.Double agentPosition;
    
    public World() {
        this.lines = new LineList(points);
        
    }
    
    public Point2D.Double calcStartposition() {
        Point2D.Double res;
        
        return res;
    }
    
    public ArrayList<Point> getAvailablePoints() {
        ArrayList<Point> result = new ArrayList<>();
        
        //Ermitteln der erreichbaren Punkte
        
        return result;
    }
}
