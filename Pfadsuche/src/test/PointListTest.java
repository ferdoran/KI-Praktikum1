/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package test;

import data.PointList;
import data.PolygonList;
import java.awt.Polygon;

/**
 *
 * @author Roland
 */
public class PointListTest {
    
    public static void main(String[] args) {
        PointList p = new PointList();
        PolygonList pol = new PolygonList(p);
        for(Polygon po : pol.getPolygons()) {
            for(int i = 0; i < po.npoints; i++) {
                System.out.print("("+po.xpoints[i]+","+po.ypoints[i]+")");
            }
            System.out.println("\n-----------------");
            
        }
    }
    
}
