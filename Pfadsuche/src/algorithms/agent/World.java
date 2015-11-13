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
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Roland
 * @author Mahdi
 */
public class World {
    
    final PointList points;
    final LineList polyLines;
    Point2D.Double agentPosition;
    final Point target;
    HashMap<Point, Vector2D> actualLines;
    final PolygonList polygons;
    ArrayList<Point> usedStartpoints; 
    
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        
        this.target = points.getPointById("Z");
        actualLines = new HashMap<>();
        polygons = new PolygonList();
        usedStartpoints = new ArrayList<>();
        agentPosition = calcStartposition();
        
        
        
    }
    
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
        System.out.println("Failed Positions: "+failedPositions.toString());
        System.out.println("Used Startpoints: "+usedStartpoints.toString());
        return res;
    }
    
    
    
    //verändern der Agentenposition
    public void setAgentPosition (Point2D.Double pos){
        agentPosition = pos;
        System.out.println("Agenten Position: "+pos.toString());
    }
    
    public boolean isValidPoint(Point p) {
        for(Polygon pol : polygons.getPolygons()) {
            if(!pol.contains(p)) {
                
            }
            else return false;
            
        }
        return true;
    }
    
    //gibt eine Liste der von diesem Punkt aus gültigen Linien zurück
    public HashMap<Point,Vector2D > getAvailableVectors() {
        if(actualLines==null) throw new NullPointerException();
        return actualLines;        
    }
    
    private Point getMiddleOfLine(Point from, Point to) {
        int x1 = (int) from.getX();
        int y1 = (int) from.getY();
        int x2 = (int) to.getX();
        int y2 = (int) to.getY();
        
        int resX = ((Math.max(x1, x2) - Math.min(x1, x2)) / 2) + Math.min(x1, x2);
        int resY = ((Math.max(y1, y2) - Math.min(y1, y2)) / 2) + Math.min(y1, y2);
        return new Point(resX, resY, "P"+resX+resY);
    }
    
    public ArrayList<Point> getAvPoints() {
        ArrayList<Point> result = new ArrayList<>();
        actualLines.clear();
        Point agPos = new Point(agentPosition.getX(), agentPosition.getY(), "agPos");
        
        for(Point p : points.getAllPoints()) {
            Line connection = new Line(agPos, p);
            Point p1 = connection.getP1();
            Point p2 = connection.getP2();
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
                    actualLines.put(p,new Vector2D(p1, p2));
                }
                else if(!points.contains(p1)) {
                    result.add(p);
                    actualLines.put(p,new Vector2D(p1, p2));
                }
                
            }
            
        }
        
        
        
        
        
        
        
        
        return result;
    }
    
    public boolean intersection(Line a, Line b) {
        Point a1 = a.getP1();
        Point a2 = a.getP2();
        
        Point b1 = b.getP1();
        Point b2 = b.getP2();
        
        
        return Line2D.linesIntersect(a1.getX(), a1.getY(), a2.getX(), a2.getY(), b1.getX(), b1.getY(), b2.getX(), b2.getY());
        
    }
    
}
