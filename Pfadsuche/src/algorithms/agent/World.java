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
        polygons = new PolygonList(points);
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
            Line h2p = new Line(new Point(agentPosition.getX(),agentPosition.getY(),"apos"),p);
            Point h2p1 = h2p.getP1();
            Point h2p2 = h2p.getP2();
            boolean intersects = false;
            
            //diese Linie mit allen Polygon-Linien auf Schnittpunkte prüfen
            for(Line l : polyLines.getList()){
                Point lp1 = l.getP1();
                Point lp2 = l.getP2();
                
                
                //wenn es einen Schnittpunkt gibt, und dieser NICHT einer der Eckpunkte ist
                if(h2p.intersectsLine(l) && (!(h2p2.equals(lp1) || h2p2.equals(lp2)) && !(h2p1.equals(lp1) || h2p1.equals(lp2))) && !h2p.equals(l) && !h2p1.isNeighbourOf(h2p2)){
                    
                        
                            intersects = true;
                            break;
                          
                     
                    
                    //wenn eine "Mauer" gefunden wurde, müssen die anderen Linien nicht mehr geprüft werden für diesen Punkt
                      
                }    
            }
            
            //wenn es keine "echten" Schnittpunkte gibt und der zu prüfende Punkt nicht der Agentenposition entspricht
            if (!intersects && !(p.equals(new Point(agentPosition.getX(),agentPosition.getY(),"apos"))) ) {
                
                //füge den Punkt zur Ergebnisliste hinzu
                result.add(p);
                
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
                for(int i = 0;i < pol.npoints; i++) {
                    System.out.print("("+pol.xpoints[i] + "," + pol.ypoints[i]+")");
                }
            }
            else return false;
            System.out.println("\n-----------");
        }
        return true;
    }
    
    //gibt eine Liste der von diesem Punkt aus gültigen Linien zurück
    public ArrayList<Line> getAvailableLines() {
        if(actualLines==null) throw new NullPointerException();
        return actualLines;        
    }
    
}
