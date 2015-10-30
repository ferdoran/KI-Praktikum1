package algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mahdi
 */
public class NavNode {
    protected double x;
    protected double y;
    protected String id;
    protected HashSet<NavNode> neighbours;
    
    public NavNode(double x, double y, String id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.neighbours = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public void addNeighbour(NavNode n){
        this.neighbours.add(n);        
    }
    
    public HashSet<NavNode> getNeighbours(){
        return neighbours;
    }
}
