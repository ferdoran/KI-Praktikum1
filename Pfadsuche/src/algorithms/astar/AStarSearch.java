package algorithms.astar;


import data.Point;
import gui.DrawingPanel;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

/**
 *
 * @author Mahdi
 * 
 * Diese Klasse beinhaltet die eigentliche Suche nach A*.
 */
public class AStarSearch extends Thread {
    
    final Point source;
    final Point target;
    DrawingPanel d;
    final int delay;
    JTextPane log;
    
    public AStarSearch(Point source, Point target, DrawingPanel d, int delay, JTextPane log) {
        this.source = source;
        this.target = target;
        this.d = d;
        this.delay = delay;
        this.log = log;
    }

    private List<String> search() {
       
        // Map zur Überprüfung ob ein Knoten bereits in der PQ ist.
        Map<String, AStarNode> offeneKnoten = new HashMap<>();
        
        // PQ der Zustände welche noch geprüft werden müssen.
        PriorityQueue<AStarNode> grenzListe = new PriorityQueue(40, new AStarNodeComparator());
        
        // Map zur Überprüfung ob ein Knoten bereits besucht wurde.
        Map<String, AStarNode> geschlosseneKnoten = new HashMap<>();
        
        // Erzeugung eines Startzustandes
        AStarNode start = new AStarNode(source, 0, calcDistance(source, target));
        
        // Hinzufügen zu der Liste der offenen Knoten sowie der PQ
        offeneKnoten.put(source.getId(), start);
        grenzListe.add(start);

        AStarNode goal = null;
        while(offeneKnoten.size() > 0){
            AStarNode x = grenzListe.poll();
            offeneKnoten.remove(x.getId());

            d.markPoint(x.getNode(),false);
            
            
            
            // gefunden?
            if(x.getId().equals(target.getId())){
                goal = x;
                break;   
            }
            
            // wenn nicht:
            else{                
                geschlosseneKnoten.put(x.getId(), x);
                Set<Point> neighbors = x.getNode().getNeighbours();
                neighbors.stream().forEach((neighbor) -> {
                    
                    // in der geschlossenen Liste?
                    AStarNode visited = geschlosseneKnoten.get(neighbor.getId());
                    
                    // wenn nicht:
                    if (visited == null) {
                        double g = x.getG() + calcDistance(x.getNode(), neighbor);
                        
                        // Bereits in der PQ?
                        AStarNode n = offeneKnoten.get(neighbor.getId());
                        
                        // wenn nicht:
                        if (n == null) {
                            //not in the open set
                            n = new AStarNode(neighbor, g, calcDistance(neighbor, target));
                            n.setCameFrom(x);
                            offeneKnoten.put(neighbor.getId(), n);
                            grenzListe.add(n);
                            
                            
                        }
                        
                        // wenn ja und der Weg ist kürzer als der bisher
                        // gefundene: Neuen Elternknoten sowie neue Entfernung
                        // setzen.
                        else if (g < n.getG()) {
                            n.setCameFrom(x);
                            n.setG(g);
                        }                                           
                    }
                    else {
                        d.markPoint(visited.getNode(),false);
                    }                   
                });
            }
            
            try {
                
                sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(AStarSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Wenn das Ziel gefunden wurde, bereite die Ausgabe des Weges vor.
        if(goal != null){
            Stack<Point> stack = new Stack<>();
            List<Point> list = new ArrayList<>();
            stack.push(goal.getNode());
            AStarNode parent = goal.getCameFrom();
            while(parent != null){
                stack.push(parent.getNode());
                parent = parent.getCameFrom();
            }
            while(stack.size() > 0){
                list.add(stack.pop());
            }
            
            List<String> idlist = new ArrayList<>();
            list.stream().forEach((list1) -> {
                idlist.add(list1.getId());
            });
            StringBuilder sb = new StringBuilder();
            sb.append(log.getText());
            if(idlist != null) {
                
                sb.append("Der Pfad zum Ziel lautet: " + idlist + "\n");
            }
            else {
                sb.append("Nichts gefunden.\n");
            }
            d.drawFinalPath(idlist);
            log.setText(sb.toString());           
        }       
        return null;  
    }

   
    // Funktion zur Berechnung der Luftliniendistanz zwischen zwei Knoten
    private double calcDistance(Point source, Point target) {
        return sqrt(Math.pow(((source.getX()) - (target.getX())),2) + (Math.pow(((target.getY()) - (source.getY())),2)));
    }
    
    @Override
    public void run() {
        search();
    }

}
