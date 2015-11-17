package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import data.PolygonList;
import data.Vector2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Roland Müller & Stephan Schneider
 */
public class World {
    
    final PointList points;
    final LineList polyLines;
    Point2D.Double agentPosition;
    final Point target;
    ArrayList<Vector2D> actualLines;
    final PolygonList polygons;
    ArrayList<Point> usedStartpoints; 
    
    
    /**
     * Konstruktor
     */
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        this.target = points.getPointById("Z");
        actualLines = new ArrayList<>();
        polygons = new PolygonList();
        usedStartpoints = new ArrayList<>();
        agentPosition = calcStartposition();
        
        
        
    }
    
    
    /**
     * Berechnet eine zufällige Startposition innerhalb der Welt, welche nicht innerhalb der Polygone liegt
     * @return ein Punkt im Zweidimensionalen Raum
     */
    public Point2D.Double calcStartposition() {
        Point2D.Double res = null;
        int minX = 80;
        int maxX = 388;
        int minY = 496;
        int maxY = 690;
        ArrayList<Point> failedPositions = new ArrayList();
        
        boolean valid = false;
        while(true) {
            Random r = new Random();
            int randX = r.nextInt((maxX - minX) +1) + minX;
            int randY = r.nextInt((maxY - minY) +1) + minY;
            Point po = new Point(randX, randY, "P"+Integer.toString(randX)+Integer.toString(randY));
            if(failedPositions.contains(po)) {
                continue;
            }
            for(Polygon p : polygons.getPolygons()) {
                if(p.contains(po)) {
                    valid = false;
                    
                    failedPositions.add(po);
                    break;
                }
                else {
                    valid = true;
                }
                
            }
            if(!(usedStartpoints.contains(po)) && valid) {
                res = new Point2D.Double(randX, randY);
                usedStartpoints.add(po);
                break;
            }
        }
        return res;
    }
    
    
    
    /**
     * Verändert die Position des Agenten
     * @param pos Zweidimensionaler Punkt
     */
    public void setAgentPosition (Point2D.Double pos){
        agentPosition = pos;
    }
    
    /**
     * Prüft ob ein Punkt gültig ist => Ob er außerhalb der Polygone liegt
     * @param p zu prüfender Punkt
     * @return true wenn der Punkt gültig ist, sonst false
     */
    public boolean isValidPoint(Point p) {
        for(Polygon pol : polygons.getPolygons()) {
            if(pol.contains(p)) {
                return false;
            }         
        }
        return true;
    }
    
    /**
     * Gibt die Variable 'actualLines' aus
     * @return ArrayList von 2D Vektoren
     */
    public ArrayList<Vector2D > getAvailableVectors() {
        if(actualLines==null) throw new NullPointerException();
        return actualLines;        
    }
    
    /**
     * Ermittelt die vom Agenten aus in gerader Linie erreichbaren Punkte
     * @return ArrayList von Punkten
     */
    public ArrayList<Point> getAvPoints() {
        ArrayList<Point> result = new ArrayList<>();
        actualLines.clear();
        Point agPos = new Point(agentPosition.getX(), agentPosition.getY(), "agPos");
        
        for(Point p : points.getAllPoints()) {
            Line connection = new Line(agPos, p);
            Point p1 = connection.getP1();
            Point p2 = connection.getP2();
            if(p1.equals(target)) {
                result.add(target);
                actualLines.add(new Vector2D(p1,p1));
            }
            int count = 0;
            int limit = 0;
            for(Line l : polyLines.getList()) {
                if(intersection(connection, l)) {
                    if(points.contains(p1)) {
                        limit = 4;
                        count++;                       
                    }
                    else {
                        limit = 2;
                        if(p.equals(target) || p.equals(points.getPointById("S"))) {
                            limit = 0;
                        }
                        count++;                       
                    }
                }                
            }
            if(count <= limit) {
                
                if(points.contains(p1) && points.neigbours(p1.getX(), p1.getY(), p2.getX(), p2.getY())) {
                    result.add(p);
                    actualLines.add(new Vector2D(p1, p2));
                }
                
                else if(!points.contains(p1)) {
                    result.add(p);
                    actualLines.add(new Vector2D(p1, p2));
                }   
            }            
        }    
        return result;
    }

    /**
     * Prüft ob sich zwei Linien schneidern
     * @param a erste Linie
     * @param b zweite Linie
     * @return true, wenn sie sich schneiden, false, wenn nicht
     */
    public boolean intersection(Line a, Line b) {
        Point a1 = a.getP1();
        Point a2 = a.getP2();
        
        Point b1 = b.getP1();
        Point b2 = b.getP2();
                
        return Line2D.linesIntersect(a1.getX(), a1.getY(), a2.getX(), a2.getY(), b1.getX(), b1.getY(), b2.getX(), b2.getY());        
    }    
}