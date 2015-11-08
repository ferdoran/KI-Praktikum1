package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import data.PolygonList;
import java.awt.Polygon;
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
    
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        
        this.target = points.getPointById("Z");
        actualLines = new ArrayList<>();
        polygons = new PolygonList(points);
        agentPosition = calcStartposition();
        
    }
    
    private Point2D.Double calcStartposition() {
        Point2D.Double res = null;
        int minX = 80;
        int maxX = 388;
        int minY = 496;
        int maxY = 690;
        Random r = new Random();
        boolean valid = false;
        while(true) {
            int randX = r.nextInt((maxX - minX) +1) + minX;
            int randY = r.nextInt((maxY - minY) +1) + minY;
            for(Polygon p : polygons.getPolygons()) {
                if(!p.contains(randX, randY)) {
                    valid = true;
                }
                else {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                res = new Point2D.Double(randX, randY);
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
            boolean intersects = false;
            
            //diese Linie mit allen Polygon-Linien auf Schnittpunkte prüfen
            for(Line l : polyLines.getList()){
                
                //wenn es einen Schnittpunkt gibt, und dieser NICHT einer der Eckpunkte ist
                if(h2p.intersectsLine(l)/* && !(h2p.getP2().equals(l.getP1()) || h2p.getP2().equals(l.getP2())) && !h2p.equals(l)*/){
                    if(!h2p.equals(l)) {
                        if(!(h2p.getP2().equals(l.getP1()) || h2p.getP2().equals(l.getP2()))) {
                            intersects = true;
                            break;
                        }
                    }
                    
                    
                    //wenn eine "Mauer" gefunden wurde, müssen die anderen Linien nicht mehr geprüft werden für diesen Punkt
                    break;
                }
            }
            
            //wenn es keine "echten" Schnittpunkte gibt und der zu prüfende Punkt nicht der Agentenposition entspricht
            if (!intersects && !(p.equals(new Point(agentPosition.getX(),agentPosition.getY(),"apos")))) {
                
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
        //System.out.println("Agenten Position: "+pos.toString());
    }
    
    //gibt eine Liste der von diesem Punkt aus gültigen Linien zurück
    public ArrayList<Line> getAvailableLines() {
        if(actualLines==null) throw new NullPointerException();
        return actualLines;        
    }
    
}
