package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import java.util.ArrayList;

/**
 *
 * @author Roland
 * @author Mahdi
 */
public class World {
    
    final PointList points;
    final LineList polyLines;
    Point agentPosition;
    final Point target;
    ArrayList<Line> actualLines;
    
    public World() {
        this.points = new PointList();
        this.polyLines = new LineList(points);
        agentPosition = null;
        this.target = points.getPointById("Z");
        actualLines = new ArrayList<>();
        
    }
    
//    public Point2D.Double calcStartposition() {
//        Point2D.Double res;
//        
//        return res;
//    }
    
    //Ermitteln der erreichbaren Punkte
    public ArrayList<Point> getAvailablePoints() {
        if(agentPosition == null) throw new NullPointerException();
        ArrayList<Point> result = new ArrayList<>();
        actualLines.clear();
        
        //für jeden der bekannten Punkte
        points.getAllPoints().stream().forEach((Point p) -> {
            
            //erstellen der Linie von der AgentenPosition zum aktuellen Knoten der Liste (Here 2 Point)
            Line h2p = new Line(new Point(agentPosition.getX(),agentPosition.getY(),"apos"),p);
            boolean intersects = false;
            
            //diese Linie mit allen Polygon-Linien auf Schnittpunkte prüfen
            for(Line l : polyLines.getList()){
                
                //wenn es einen Schnittpunkt gibt, und dieser NICHT einer der Eckpunkte ist
                if(h2p.intersectsLine(l)&& !(h2p.getP2().equals(l.getP1()) || h2p.getP2().equals(l.getP2()))){
                    intersects = true;
                    
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
        });
        
        return result;
    }
    
    //verändern der Agentenposition
    public void setAgentPosition (Point pos){
        agentPosition = pos;
    }
    
    //gibt eine Liste der von diesem Punkt aus gültigen Linien zurück
    public ArrayList<Line> getAvailableLines() {
        if(actualLines==null) throw new NullPointerException();
        return actualLines;        
    }
    
}
