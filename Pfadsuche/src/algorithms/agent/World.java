/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.lang.NullPointerException;

/**
 *
 * @author Roland
 */
public class World {
    
    final PointList points;
    final LineList polyLines;
    Point2D.Double agentPosition;
    final Point target;
    
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        agentPosition = null;
        this.target = points.getPointById("Z");
        
    }
    
    public Point2D.Double calcStartposition() {
        Point2D.Double res;
        
        return res;
    }
    
    public ArrayList<Point> getAvailablePoints() {
        if(agentPosition == null) throw new NullPointerException();
        ArrayList<Point> result = new ArrayList<>();
        
        //Ermitteln der erreichbaren Punkte
        for(Point p : points.getAllPoints()) {
            //erstellen der Linie von der AgentenPosition zum aktuellen Knoten der Liste (Here 2 Point)
            Line h2p = new Line(new Point(agentPosition.getX(),agentPosition.getY(),"apos"),p);
            boolean intersects = false;
            for(Line l : polyLines.getList()){
                if(h2p.intersectsLine(l)){
                   intersects = true;
                   break;
                }
            }
            
        }
        
        return result;
    }
    
    public void setAgentPosition (Point2D.Double pos){
        agentPosition = pos;
    }
    public ArrayList<Line> getAvailableLines() {
        ArrayList<Line> result = new ArrayList<>();
        
        return result;        
    }
}
