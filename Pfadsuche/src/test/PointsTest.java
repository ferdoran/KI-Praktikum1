/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Point;
import data.PointList;

/**
 *
 * @author Roland
 */
public class PointsTest {
    
    public static void main(String[] args) {
        PointList points = new PointList();
        points.addPoint(new Point(1,1));
        points.addPoint(new Point(2,2));
        points.addPoint(new Point(3,3));
        points.addPoint(new Point(4,4));
        points.addPoint(new Point(5,5));
        points.addPoint(new Point(6,6));
        points.addPoint(new Point(7,7));
        points.addPoint(new Point(8,8));
        
        System.out.print(points.toString());
        
        points.addNeighbour(points.getPoint(1, 1), new Point(10,10));
        points.addNeighbour(points.getPoint(2, 2), new Point(20,20));
        points.addNeighbour(points.getPoint(3, 3), new Point(30,30));
        
        points.addNeighbour(points.getPoint(1, 1), new Point(11,11));
        points.addNeighbour(points.getPoint(1, 1), new Point(12,12));
        points.addNeighbour(points.getPoint(1, 1), new Point(13,13));
        
        System.out.print(points.toString());
        
        points.removeNeighbour(points.getPoint(1, 1), new Point(10, 10));
        points.removeNeighbour(points.getPoint(1, 1), new Point(12, 12));
        points.removeNeighbour(points.getPoint(1, 1), new Point(13, 13));
        
        System.out.print(points.toString());
        
    }
    
}
