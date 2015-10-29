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
public class NavNode {
    protected double x;
    protected double y;
    protected String id;
    
    public NavNode(double x, double y, String id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    String getId() {
        return id;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}
