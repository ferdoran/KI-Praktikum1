/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.List;

/**
 *
 * @author Mahdi
 */
class NavNode extends Node{

    protected Position position;

    protected List<String> extraData;

}

class NavEdge extends Edge {

    protected double cost;
}

public class NavGraph extends Graph<NavNode, NavEdge>{

    public void addConnection(String firstId, String secondId){
        NavNode node1 = this.getNode(firstId);
        NavNode node2 = this.getNode(secondId);
        if(node1 != null && node2 != null){
            double cost = this.calcManhattanDistance(node1, node2);
            NavEdge edge1 = new NavEdge(firstId, secondId, cost);
            NavEdge edge2 = new NavEdge(secondId, firstId, cost);
            this.addEdge(edge1);
            this.addEdge(edge2);
        }
    }

    public void removeConnection(String firstId, String secondId){
        NavEdge edge1 = new NavEdge(firstId, secondId);
        NavEdge edge2 = new NavEdge(secondId, firstId);
        this.removeEdge(edge1);
        this.removeEdge(edge2);
    }

    public double calcHeuristicDistance(NavNode a, NavNode b){
        return sqrt(power(abs(a.getPosition().getX() - b.getPosition().getX()),2)
                + (power(abs(a.getPosition().getY() - b.getPosition().getY())),2));
    }
}
