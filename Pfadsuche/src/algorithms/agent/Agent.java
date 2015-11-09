package algorithms.agent;

import data.Line;
import data.Point;
import data.PointList;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import gui.DrawingPanel;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;


/**
 *
 * @author Roland
 */
public class Agent extends Thread {
    final PointList points = new PointList();
    final int delay;
    final boolean randomize;
    World world;
    DrawingPanel d;
    Point position;
    JTextPane log;
    
    public Agent(World w, DrawingPanel d, int delay, boolean randomize, JTextPane log) {
        world = w;
        position = null;
        this.d = d;
        this.delay = delay;
        this.randomize = randomize;
        this.log = log;
//        log.setText("Ich befinde mich bei: " + position.toString());
    }
    
    public int[] search() {
        int[] cost = new int[100];
        int huch = 0;
        int found = 0;
        Point target = points.getPointById("Z");
  
        //100 Episoden
        for(int i=0;i<100;i++) {
            d.clearLastVisited();
            d.clear();
            d.drawAllPolygons();
            Point2D.Double pos = world.calcStartposition();
            world.setAgentPosition(pos);
            world.getAvailablePoints();
            cost[i] = 0;
            //neue Startposition je Episode
            
            position = calcPosition(world);
            
            d.markPoint(position,true);
            boolean goal = false;
            Point nextPoint = null;
            Point2D.Double lastPoint = null;
            
            //Suche implementieren
            while(!goal) {
                
                
                ArrayList<Point> ap = world.getAvailablePoints();
                
                double distance = 1000000;

                //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
                for(Point p : ap) {

                    if(p.equals(target)) {
                        nextPoint = p;
                        d.markPoint(p, true);
                        cost[i] += p.distance(position);
                        cost[i] -= 1000;
                        addLogLine("Ziel gefunden");
                        goal=true;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        d.clearLastVisited();
                        d.clear();
                        d.drawAllPolygons();
                        found++;
                        break;

                    }

                    else if(p.distance(target) < distance) {
                        distance = p.distance(target);
                        nextPoint = p;
                        lastPoint = position;
                    }

                }
                if(randomize) {
                    Random r = new Random();
                    if((r.nextInt()%10)<3){
                        Point2D.Double next = calcPosition(world);
                        nextPoint = new Point((int) next.getX(), (int) next.getY(), "P"+next.getX()+next.getY());
                        lastPoint = position;
                        huch++;
                    }
                }
                d.markPoint(nextPoint,true);
                cost[i] += nextPoint.distance(position);
                position = nextPoint;
                world.setAgentPosition(nextPoint);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        addLogLine("Die Suche ist beendet. Folgende Ergebnisse wurden festgestellt:");
        addLogLine("Verlaufen: " + huch);
        addLogLine("Gefunden: "+ found);
        addLogLine("Durchschnittliche Kosten: " + Arrays.stream(cost).average().toString());
        addLogLine("Maximale Kosten: " + Arrays.stream(cost).max().toString());
        addLogLine("Minimale Kosten: " + Arrays.stream(cost).min().toString());
        return cost;
    }
    
    private Point calcPosition(World w) {
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
    
    private void addLogLine(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.getText());
        sb.append(s + "\n");
        log.setText(sb.toString());
    }
    
    public void run() {
        search();
    }
    
}
