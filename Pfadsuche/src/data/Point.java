/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.awt.geom.Point2D;

/**
 *
 * @author Roland
 */
public class Point extends Point2D.Double {
    private String id;
    
    public Point(double x, double y) {
        super(x, y);
    }
    
    public Point(double x, double y, String s) {
        super(x, y);
        id = s;
    }
    
    public double distance(Point p) {
        return super.distance(p);
    }
    
    public void setId(String s) {
        id = s;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean equals(Point p) {
        return p.getX() == this.x && p.getY() == this.y;
    }
    
    
    
}
