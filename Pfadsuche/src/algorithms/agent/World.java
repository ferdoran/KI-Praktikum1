package algorithms.agent;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import java.util.ArrayList;

/**
 *
 * @author Roland
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
    
    public ArrayList<Point> getAvailablePoints() {
        if(agentPosition == null) throw new NullPointerException();
        ArrayList<Point> result = new ArrayList<>();
        actualLines.clear();
        
        //Ermitteln der erreichbaren Punkte
        points.getAllPoints().stream().forEach((Point p) -> {
            //erstellen der Linie von der AgentenPosition zum aktuellen Knoten der Liste (Here 2 Point)
            Line h2p = new Line(new Point(agentPosition.getX(),agentPosition.getY(),"apos"),p);
            boolean intersects = false;
            for(Line l : polyLines.getList()){
                if(h2p.intersectsLine(l)&& !(h2p.getP2().equals(l.getP1()) || h2p.getP2().equals(l.getP2()))){
                    intersects = true;
                    break;
                }
            }
            if (!intersects && !(p.equals(new Point(agentPosition.getX(),agentPosition.getY(),"apos")))) {
                result.add(p);
                actualLines.add(h2p);
            }
        });
        
        return result;
    }
    
    public void setAgentPosition (Point pos){
        agentPosition = pos;
    }
    
    public ArrayList<Line> getAvailableLines() {
        return actualLines;        
    }
    
}
