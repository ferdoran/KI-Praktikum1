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
    private String name;
    
    public Point(double x, double y) {
        super(x, y);
    }
    
    public Point(double x, double y, String s) {
        super(x, y);
        name = s;
    }
    
    public double distance(Point p) {
        return super.distance(p);
    }
    
    public void setName(String s) {
        name = s;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean equals(Point p) {
        if(p.getX() == this.x && p.getY() == this.y) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
}
