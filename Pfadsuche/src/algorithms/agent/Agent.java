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
                        
                        //Wenn es sich dabei um den vorgegebenen Zielpunkt handelt
                        if(target.equals(points.getPointById("Z"))) {
                            cost[i] += p.distance(position);
                            cost[i] -= 1000;
                            nextPoint = p;
                        
                            d.markPoint(p, true);
                            d.drawActualPosition(target);
                                                       
                            //Ausgabe im Logfenster
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
                    //Wenn kein Ziel, dann prüfe ob die Distanz zum Ziel kleiner ist als die bisherige
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
                    addLogLine("Ich bin auf dem richtigen Weg!!!");
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
        
        //Ausgabe im Logfenster
        addLogLine("Die Suche ist beendet. Folgende Ergebnisse wurden festgestellt:");
        addLogLine("Insgesamt Verlaufen: " + huch);
        addLogLine("Durchschnittliche Schritte: " + steps/100);
        addLogLine("Durchschnittlich Verlaufen: " + huch/100);
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
        // Wenn keine Vektoren vorhanden Fehler ausgeben
        if(al.isEmpty()) {
            addLogLine("Position kann nicht errechnet werden.");
            throw new NullPointerException();
        }
        // Ansonsten Position berechnen
        else {
            int size = al.size();
            boolean found = false;
            int count = 0;
            
            // So lange die Position nicht bestimmt wurde
            while(!found) {
                
                // Nimm den ersten Vektor aus der Liste und bilde seinen Gegenvektor
                Vector2D first = al.get(0);
                first = first.opposite();
                
                // Prüfe für jeden Punkt der Polygone
                for(Point p : points.getAllPoints()) {
                 
                    // Bilde den Punkt auf einen Ortsvektor ab und addiere den Gegenvektor
                    Vector2D pos = Vector2D.createVectorFromPoint(p).add(first);
                    
                    // Wenn der neue Ortsvektor nicht auf einen Punkt innerhalb eines Polygons verweist
                    // Prüfe ob dann für jeden Vektor, von diesem Ortsvektor aus, ob ein Eckpunkt erreicht wird
                    if(!polygons.inPolygon(pos.getPoint())) {
                        for(int i = 1;i < size; i++) {
                            Vector2D v = al.get(i);
                            Point possiblePoint = pos.add(v).getPoint();
                            if(points.contains(possiblePoint)) {
                                count++;
                            }
                        }
                        // Wenn die Anzahl der Vektoren die einen Eckpunkt erreichen gleich der Anzahl der 
                        // Vektoren-1 ist (der erste Vektor wird ja nicht mitgezählt), dann ist die Position bestimmt.
                        if(count == size-1) {
                            found = true;
                            return pos.getPoint();
                        }
                        // Ansonsten wird der counter zurückgesetzt
                        else {
                            count = 0;
                        }
                    }
                    
                }
                
                
            }
            
        }
        throw new NullPointerException();
        
    }
    
    /**
     * Setzt übergebene Strings zusammen und übergibt sie an das Logfenster
     * @param s übergebener String
     */
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
