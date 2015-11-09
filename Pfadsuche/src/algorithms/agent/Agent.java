package algorithms.agent;

import data.Line;
import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import gui.DrawingPanel;
import java.util.Random;
import javax.swing.JTextPane;


/**
 *
 * @author Roland
 */
public class Agent {
    final PointList points = new PointList();
    World world;
    Point2D.Double position;
    JTextPane log;
    
    public Agent(World w) {
        world = w;
        w.getAvailablePoints();
        position = null;
//        log.setText("Ich befinde mich bei: " + position.toString());
    }
    
    public double search() {
        int cost = 0;
        int huch = 0;
        int found = 0;
        Point target = points.getPointById("Z");
  
        //100 Episoden
        for(int i=0;i<100;i++){
            
            //neue Startposition je Episode
            position = calcPosition(world);
            
            boolean goal = false;
            Point nextPoint = null;
            Point2D.Double lastPoint = null;
            
            //Suche implementieren
            while(!goal) {
                world.setAgentPosition(position);
                System.out.println("Startposition = (" + position.getX() + "," + position.getY() + ")");
                ArrayList<Point> ap = world.getAvailablePoints();
                System.out.println("Erreichbare Punkte: " + ap.toString());
                double distance = 1000000;

                //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
                for(Point p : ap) {

                    if(p.equals(target)) {
                        nextPoint = p;
                        cost += p.distance(position);
                        cost -= 1000;
                        System.out.println("Ziel gefunden");
                        goal=true;
                        found++;

                    }

                    else if(p.distance(target) < distance) {
                        distance = p.distance(target);
                        nextPoint = p;
                        lastPoint = position;
                    }

                }
                Random r = new Random();
                if((r.nextInt()%10)<3){
                    nextPoint = (Point) calcPosition(world);
                    lastPoint = position;
                    huch++;
                }
                cost += nextPoint.distance(position);
                position = (Point2D.Double) nextPoint;
                System.out.println("Next Point: " + nextPoint.toString());
            }
            System.out.println(i+1);
        }
        System.out.println("Verlaufen: " + huch);
        System.out.println("Gefunden: "+ found);
        return cost;
    }
    
    private Point2D.Double calcPosition(World w) {
        ArrayList<Line> al = w.getAvailableLines();
        Line l = al.get(0);
        if(points.getAllPoints().contains(l.getP1())) {
            return l.getP2();
        }
        else if(points.getAllPoints().contains(l.getP2())) {
            return l.getP1();
        }
        
        else return null;
    }
    
}
