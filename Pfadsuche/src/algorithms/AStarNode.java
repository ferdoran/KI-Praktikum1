/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author Mahdi
 */
public class AStarNode {

    private NavNode node;

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

    public String getId() {
       return node.getId();
    }

    public double getF() {
        return g + h;
    }

    public double getG() {
        return g;
    }

    void setCameFrom(AStarNode x) {
        cameFrom = x;
    }

    void setG(double g) {
        this.g = g;
    }

    void setH(double calcHeuristicDistance) {
        h = calcHeuristicDistance;
    }

    NavNode getNode() {
        return node;
    }

    AStarNode getCameFrom() {
        return cameFrom;
    }

}
