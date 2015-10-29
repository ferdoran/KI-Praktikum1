package algorithms;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import java.util.Set;
import static java.lang.Math.sqrt;


/**
 *
 * @author Mahdi
 */
class NavEdge {
    protected String from;
    protected String to;
    protected double cost;

    NavEdge(String firstId, String secondId, double cost) {
        this.from = firstId;
        this.to = secondId;
        this.cost = cost;
    }

    NavEdge(String firstId, String secondId) {
        this.from = firstId;
        this.to = secondId;
    }
}

public class NavGraph<N extends NavNode, E extends NavEdge>{
    
    public void addConnection(String firstId, String secondId){
        NavNode node1 = this.getNode(firstId);
        NavNode node2 = this.getNode(secondId);
        if(node1 != null && node2 != null){
            double cost = this.calcDistance(node1, node2);
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

    double calcDistance(NavNode a, NavNode b){
        return sqrt(power(a.getX() - b.getX(),2) + (power(a.getY() - b.getY(),2)));
    }

    Set<NavNode> getAdjacentNodes(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private NavNode getNode(String firstId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addEdge(NavEdge edge1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void removeEdge(NavEdge edge1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
