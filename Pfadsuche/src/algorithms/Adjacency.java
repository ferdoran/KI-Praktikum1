/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.Set;

/**
 *
 * @author Mahdi
 * @param <N>
 */
public class Adjacency {
    protected NavNode node;
    protected Set<NavNode> neighbors;
    
    public Adjacency(NavNode n) {
        this.node = n;
    }
    
    public boolean addNeighbour(NavNode n){
        boolean add;
        add = this.neighbors.add(n);
        return add;
    }
}
