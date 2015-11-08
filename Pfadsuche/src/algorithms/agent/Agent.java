/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package algorithms.agent;

import data.Line;
import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 *
 * @author Roland
 */
public class Agent {
    final PointList points = new PointList();
    World world;
    Point2D.Double position;
    
    public Agent(World w) {
        world = w;
        w.getAvailablePoints();
        position = calcPosition(world);
    }
    
    public double search() {
        int cost = 0;
        Point target = points.getPointById("Z");
        boolean goal = false;
        System.out.println("Startposition = (" + position.getX() + "," + position.getY() + ")");
        
        //Suche implementieren
        while(!goal) {
            ArrayList<Point> ap = world.getAvailablePoints();
            double distance = 1000000;
            Point nextPoint = null;
            
            
            
            //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
            for(Point p : ap) {
                if(p.equals(target)) {
                    nextPoint = p;
                    cost += p.distance(position);
                    cost -= 1000;
                    return cost;
                }
                if(p.distance((target)) < distance) {
                    distance = p.distance(target);
                    nextPoint = p;
                }
                
            }
//            if(nextPoint == null) {
//                System.out.println("nextPoint null");
//                break;
//            }
            world.setAgentPosition(nextPoint);
            cost += nextPoint.distance(position);
            position = nextPoint;
            System.out.println("Next Point: " + nextPoint.toString());
        }
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
