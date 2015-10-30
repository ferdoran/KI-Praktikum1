package algorithms.astar;

import data.NavNode;

/**
 *
 * @author Mahdi
 */
public class AStarNode {

    private final NavNode node;

    //used to construct the path after the search is done
    private AStarNode cameFrom;

    // Distance from source along optimal path
    private double g;

    // Heuristic estimate of distance from the current node to the target node
    private double h;
    
    public AStarNode(NavNode n, double dist, double heu){
        node = n;
        g = dist;
        h = heu;    
    }

    String getId() {
       return node.getId();
    }

    double getF() {
        return g + h;
    }

    double getG() {
        return g;
    }

    void setCameFrom(AStarNode x) {
        cameFrom = x;
    }

    void setG(double g) {
        this.g = g;
    }

    void setH(double heu) {
        h = heu;
    }

    NavNode getNode() {
        return node;
    }

    AStarNode getCameFrom() {
        return cameFrom;
    }

}
