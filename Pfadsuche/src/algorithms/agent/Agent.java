package algorithms.agent;

import data.Line;
import data.Point;
import data.PointList;
import data.Vector2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import gui.DrawingPanel;
import java.util.Arrays;
import java.util.HashMap;
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
        int steps = 0;
        Point target = points.getPointById("Z");
  
        //100 Episoden
        for(int i=0;i<100;i++) {
            d.clearLastVisited();
            d.clear();
            d.drawAllPolygons();
            Point2D.Double pos = world.calcStartposition();
            world.setAgentPosition(pos);
            world.getAvPoints();
            cost[i] = 0;
            //neue Startposition je Episode
            int huchActual = 0;
            position = calcPosition(world);
            Point lastValid = position;
            Point nextValid = null;
            
            d.markPoint(position,true);
            boolean goal = false;
            Point nextPoint = null;
            Point lastPoint = null;
            
            //Suche implementieren
            while(!goal) {
                
                
                ArrayList<Point> ap = world.getAvPoints();
                
                double distance = 1000000;

                //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
                for(Point p : ap) {

                    if(p.equals(target)) {
                        
                        if(target.equals(points.getPointById("Z"))) {
                        
                            nextPoint = p;
                        
                            d.markPoint(p, true);
                            d.drawActualPosition(target);
                            cost[i] += p.distance(position);
                            cost[i] -= 1000;
                            addLogLine("[Suche " + (i+1) + "] Ziel gefunden");
                            addLogLine("[Suche " + (i+1) + "] Verlaufen: " + huchActual);
                            addLogLine("[Suche " + (i+1) + "] Kosten: " + cost[i]);
                            addLogLine("");
                            goal=true;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            d.clearLastVisited();
                            d.clear();
                            d.drawAllPolygons();
                            found++;
                            break;
                        }
                        else {
                            target = points.getPointById("Z");
                        }

                    }

                    else if(p.distance(target) < distance) {
                        distance = p.distance(target);
                        nextPoint = p;
                        lastPoint = position;
                    }
                
                nextValid = nextPoint;
                }
                if(randomize) {
                    Random r = new Random();
                    if((r.nextInt()%10)<3){
                        if(ap.size()>0) {
                            nextPoint = ap.get( (Math.abs(r.nextInt()) % ap.size()));
                            huch++;
                            huchActual++;
                            lastPoint = position;
                        }
                        else {
                            nextPoint = position;
                        }
                        
                    }
                }
                if(!nextPoint.equals(nextValid)){
                    target = lastValid;
                }
                else{
                    target = points.getPointById("Z");
                    lastValid = position;
                }
                
                
                d.markPoint(nextPoint,true);
                cost[i] += nextPoint.distance(position);
                steps++;
                position = nextPoint;
                d.drawActualPosition(position);
                world.setAgentPosition(nextPoint);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        addLogLine("Die Suche ist beendet. Folgende Ergebnisse wurden festgestellt:");
        addLogLine("Insgesamt Verlaufen: " + huch);
        addLogLine("Durchschnittliche Schritte: " + steps/100);
        addLogLine("Durchschnittlich Verlaufen: " + huch/100);
        //addLogLine("Gefunden: "+ found);
        addLogLine("Durchschnittliche Kosten: " + Arrays.stream(cost).average().toString());
        addLogLine("Maximale Kosten: " + Arrays.stream(cost).max().toString());
        addLogLine("Minimale Kosten: " + Arrays.stream(cost).min().toString());
        return cost;
    }
    
    private Point calcPosition(World w) {
        ArrayList<Point> ap = w.getAvPoints();
        HashMap<Point,Vector2D> al = w.getAvailableVectors();
        
        if(al.isEmpty()) {
            addLogLine("Position kann nicht errechnet werden.");
            return null;
        }
        Point first = ap.get(0);
        Vector2D a = al.get(first);
        a = a.opposite();
        
        Vector2D b = new Vector2D(first.getX(), first.getY());
        b.subtract(a);
        return new Point(b.getX(), b.getY(),"apos");
        
    }
    
    private void addLogLine(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.getText());
        sb.append(s + "\n");
        log.setText(sb.toString());
        log.selectAll();
    }
    
    public void run() {
        search();
    }
    
}
