package algorithms.astar;

import data.NavNode;

/**
 *
 * @author Mahdi
 * 
 * Diese Klasse dient dazu die entsprechenden Zustände innerhalb des Zustands-
 * raumes darzustellen.
 */
public class AStarNode {

    // Der zugehörige Knoten
    private final NavNode node;

    // wird genutzt um am Ende den Weg zu konstruieren
    private AStarNode cameFrom;

    // Entfernung zum Start entlang des optimalen Pfades
    private double g;

    // heuristische Entfernung zum Ziel
    private final double h;
    
    // Konstruktor
    public AStarNode(NavNode n, double dist, double heu){
        node = n;
        g = dist;
        h = heu;    
    }

    // Abfrage der ID
    String getId() {
       return node.getId();
    }

    // Abfrage des Wertes für die PriorityQueue
    double getF() {
        return g + h;
    }

    // Abfrage der Weglänge
    double getG() {
        return g;
    }

    // setzen des vorherigen Knotens
    void setCameFrom(AStarNode x) {
        cameFrom = x;
    }

    // ändern der Entfernung zur Quelle
    void setG(double g) {
        this.g = g;
    }

    // Abfrage des zugehörigen Knoten
    NavNode getNode() {
        return node;
    }

    // Abfrage des vorherigen Knotens
    AStarNode getCameFrom() {
        return cameFrom;
    }

}
