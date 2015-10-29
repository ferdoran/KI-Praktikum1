package algorithms;

import java.util.Set;

/**
 *
 * @author Mahdi
 */
public class NavNode {
    protected double x;
    protected double y;
    protected String id;
    protected Set<NavNode> neighbors;
    
    public NavNode(double x, double y, String id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public void addNeighbour(NavNode n){
        this.neighbors.add(n);        
    }    
}
