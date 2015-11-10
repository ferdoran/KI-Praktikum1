package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import data.PolygonList;
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
    ArrayList<Line> actualLines;
    final PolygonList polygons;
    ArrayList<Point> usedStartpoints; 
    
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        
        this.target = points.getPointById("Z");
        actualLines = new ArrayList<>();
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
        return res;
    }
    
    //Ermitteln der erreichbaren Punkte
    public ArrayList<Point> getAvailablePoints() {
        if(agentPosition == null) throw new NullPointerException();
        ArrayList<Point> result = new ArrayList<>();
        actualLines.clear();
        
        
        //System.out.println("Agent: " + agentPosition.toString());
        //für jeden der bekannten Punkte
        for(Point p : points.getAllPoints()) {
            
            //erstellen der Linie von der AgentenPosition zum aktuellen Knoten der Liste (Here 2 Point)
            Point point = new Point(agentPosition.getX(), agentPosition.getY(), "apos");
            Line h2p = new Line(point,p);
            Point h2p1 = h2p.getP1();
            Point h2p2 = h2p.getP2();
            int count = 0;
            int limit;
            
            
            if(points.contains(h2p1)) {
                limit = 4;
                //System.out.println("Limit 4");
            }
            else {
                limit = 2;
                //System.out.println("Limit 2");
            }
            
            boolean intersects = false;
            
            

            
            //diese Linie mit allen Polygon-Linien auf Schnittpunkte prüfen
            for(Line l : polyLines.getList()){
                
                Point lp1 = l.getP1();
                Point lp2 = l.getP2();
                
                
                
                
            
                //wenn es einen Schnittpunkt gibt, und dieser NICHT einer der Eckpunkte ist
//                if(h2p.intersectsLine(l) && (!(h2p1.equals(lp1) || h2p1.equals(lp2) || !(h2p2.equals(lp1) || h2p2.equals(lp2)))) && !h2p.equals(l)) {
//                    intersects = true;
//                    
//                    if(points.contains(h2p1) && !points.neigbours(h2p1.getX(), h2p1.getY(), h2p2.getX(), h2p2.getY())) {
//                        count++;
//                    }
//                    else {
//                        
//                    }
//                }
//                else {
//                    
//                }
                if(points.contains(h2p1)) {
                    System.out.println("punkt vorhanden");
                    if(h2p.intersectsLine(l)) {
                        intersects = true;
                        if( (h2p1.equals(lp2) || h2p1.equals(lp1)) || (h2p2.equals(lp1) || h2p2.equals(lp2)) ) {
                            intersects = false;
                                
                            if( !points.neigbours(h2p1.getX(), h2p1.getY(), h2p2.getX(), h2p2.getY()) ) {
                                intersects = true;
                                
                                
                            }
                            break;
                            
                        }
                        else {
                            
                            
                        }
                    }
                }
                else {
                    if(h2p.intersectsLine(l)) {
                        //System.out.println("Intersektion zwischen: " + h2p + " und " + l);
                        intersects = true;
                        if((h2p2.equals(lp2) || h2p2.equals(lp1))) {
                            intersects = false;
                            
                        }

                                                
                    }
                    
                }
            
            }
            
//             if ( count <= limit ) {
//                
//                
//                //füge den Punkt zur Ergebnisliste hinzu
//                if(!result.contains(p)){
//                   result.add(p);
//                   System.out.println("punkt hinzugefügt");
//                }
//                 
//                //für die Berechnung der eigenen Position des Agenten wird diese Liste von Linien geführt
//                actualLines.add(h2p);
//                
//            }
            
            //wenn es keine "echten" Schnittpunkte gibt und der zu prüfende Punkt nicht der Agentenposition entspricht
            if ( !intersects && !p.equals(point) ) {
                
                
                //füge den Punkt zur Ergebnisliste hinzu
                if(!result.contains(p)){
                   result.add(p);
                   System.out.println("punkt hinzugefügt");
                }
                 
                //für die Berechnung der eigenen Position des Agenten wird diese Liste von Linien geführt
                actualLines.add(h2p);
                
            }
        }
        
        return result;
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
    public ArrayList<Line> getAvailableLines() {
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
    
}
