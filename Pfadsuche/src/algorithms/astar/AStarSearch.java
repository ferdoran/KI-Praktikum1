package algorithms.astar;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import data.NavNode;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Mahdi
 * 
 * Diese Klasse beinhaltet die eigentliche Suche nach A*.
 */
public class AStarSearch {

   public static List<String> search(NavNode source, NavNode target) {
       
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
            
            /*// Kommandozeilenausgabe zu Testzwecken
            System.out.print(x.getId());*/
            
            // gefunden?
            if(x.getId().equals(target.getId())){
                goal = x;
                break;   
            }
            
            // wenn nicht:
            else{                
                geschlosseneKnoten.put(x.getId(), x);
                Set<NavNode> neighbors = x.getNode().getNeighbours();
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
                });
            }
        }

        // Wenn das Ziel gefunden wurde, bereite die Ausgabe des Weges vor.
        if(goal != null){
            Stack<NavNode> stack = new Stack<>();
            List<NavNode> list = new ArrayList<>();
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
            return idlist;
        }
        
        return null;  
    }

   
    // Funktion zur Berechnung der Luftliniendistanz zwischen zwei Knoten
    private static double calcDistance(NavNode source, NavNode target) {
        return sqrt(power(((source.getX()) - (target.getX())),2) + (power(((target.getY()) - (source.getY())),2)));
    }

}
