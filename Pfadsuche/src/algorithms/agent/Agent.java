package algorithms.agent;

import data.Point;
import data.PointList;
import data.PolygonList;
import data.Vector2D;
import java.util.ArrayList;
import gui.DrawingPanel;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;


/**
 * Implementiert einen uninformierten Agenten
 * @author Roland Müller & Stephan Schneider
 */
public class Agent extends Thread {
    final PointList points = new PointList();
    final PolygonList polygons = new PolygonList();
    final int delay;
    final boolean randomize;
    World world;
    DrawingPanel d;
    Point position;
    JTextPane log;
    
    /**
     * Erstellt einen neuen Agenten
     * @param w Welt in der sich der Agent befindet
     * @param d JPanel in dem die grafische Ausgabe erfolgt
     * @param delay Verzögerung für die Suche
     * @param randomize 
     * @param log Log in dem Geschehnisse ausgegeben werden
     */
    public Agent(World w, DrawingPanel d, int delay, boolean randomize, JTextPane log) {
        world = w;
        position = null;
        this.d = d;
        this.delay = delay;
        this.randomize = randomize;
        this.log = log;
//        log.setText("Ich befinde mich bei: " + position.toString());
    }
    
    /**
     * Führt die uninformierte Suche 100 mal aus
     * @return Array der Kosten für die 100 Suchläufe
     */
    public int[] search() {
        int[] cost = new int[100];
        int huch = 0;
        int found = 0;
        int steps = 0;
        Point target = points.getPointById("Z");
  
        //100 Episoden
        for(int i=0;i<100;i++) {
            //Grafische Ausgabe vorbereiten(resetten)
            d.clearLastVisited();
            d.clear();
            d.drawAllPolygons();
            
            cost[i] = 0;
            
            //zählt wie oft der Agent sich für die aktuelle Episode verläuft
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
                System.out.println(ap.toString());
                
                double distance = 1000000;

                //Ermittle den Punkt mit der kürzesten Distanz zum Ziel
                for(Point p : ap) {
                    
                    //Wenn der zu erreichende Punkt gleich dem Ziel ist (kann auch ein Eckpunkt sein)
                    if(p.equals(target)) {
                        
                        //Wenn es sich dabei um das Ziel handelt
                        if(target.equals(points.getPointById("Z"))) {
                            cost[i] += p.distance(position);
                            cost[i] -= 1000;
                            nextPoint = p;
                        
                            d.markPoint(p, true);
                            d.drawActualPosition(target);
                            
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
                            
                            //Grafische Ausgabe resetten
                            d.clearLastVisited();
                            d.clear();
                            d.drawAllPolygons();
                            found++;
                            nextValid = nextPoint;
                            break;
                        }
                        //Ansonsten handelt es sich um ein Zwischenziel (wenn der Agent sich verlaufen hat)
                        else {
                            nextPoint = p;
                            d.markPoint(p, true);
                            d.drawActualPosition(target);
                            cost[i] += p.distance(position);
                            cost[i] -= 1000;
                            target = points.getPointById("Z");
                        }

                    }
                    //Wenn kein Ziel, dann prüfe ob die Distanz zum target kleiner ist als die bisherige
                    else if((p.distance(target) < distance) && (p.distance(target) < position.distance(target))) {
                        distance = p.distance(target);
                        nextPoint = p;
                        lastPoint = position;
                    }
                //Nächste gültige Position
                nextValid = nextPoint;
                }
                
                //Das Verlaufen
                if(randomize) {
                    Random r = new Random();
                    if((r.nextInt()%10)<3){
                        if(ap.size()>0) {
                            nextPoint = ap.get( (Math.abs(r.nextInt()) % ap.size()));
                            huch++;
                            huchActual++;
                            lastPoint = position;
                            addLogLine("Ich habe mich Verlaufen!!!");
                        }
                        else {
                            nextPoint = position;
                        }
                        
                    }
                }
                //Wenn der nächste Punkt nicht dem nächst gültigen gleicht (z.B. wenn er sich verläuft), ist das Ziel die letzte gültige Position
                if(!nextPoint.equals(nextValid)){
                    target = lastValid;
                }
                //Ansonsten ist das Ziel der Zielpunkt Z
                else{
                    target = points.getPointById("Z");
                    lastValid = position;
                    addLogLine("Ich bin wieder auf dem richtigen Weg!!!");
                }
                
                //Grafische Ausgabe, sowie Aufbereitung für nächsten Durchlauf(nicht Episode!)
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
    
    /**
     * Berechnet die aktuelle Position des Agenten
     * @param w Welt in der sich der Agent befindet
     * @return aktuelle Position
     */
    private Point calcPosition(World w) {
        ArrayList<Vector2D> al = w.getAvailableVectors();
        
        if(al.isEmpty()) {
            addLogLine("Position kann nicht errechnet werden.");
            throw new NullPointerException();
        }
        else {
            int size = al.size();
            boolean found = false;
            int count = 0;
            
            while(!found) {
                Vector2D first = al.get(0);
                first = first.opposite();
                
                for(Point p : points.getAllPoints()) {
                 
                    Vector2D pos = Vector2D.createVectorFromPoint(p).add(first);
                    if(!polygons.inPolygon(pos.getPoint())) {
                        for(int i = 1;i < size; i++) {
                            Vector2D v = al.get(i);
                            Point possiblePoint = pos.add(v).getPoint();
                            if(points.contains(possiblePoint)) {
                                count++;
                            }
                        }
                        if(count == size-1) {
                            found = true;
                            return pos.getPoint();
                        }
                        else {
                            count = 0;
                        }
                    }
                    
                }
                
                
            }
            
        }
        throw new NullPointerException();
        
    }
    
    private void addLogLine(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.getText());
        sb.append(s + "\n");
        log.setText(sb.toString());
        log.selectAll();
    }
    
    @Override
    public void run() {
        search();
    }
    
}
