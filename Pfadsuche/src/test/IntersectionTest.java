/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package test;

import data.Line;
import data.Point;
import data.PointList;
import data.PolygonList;
import java.awt.Polygon;

/**
 *
 * @author Roland
 */
public class IntersectionTest {
    
    public static void main(String[] args) {
        PolygonList polygons = new PolygonList();
        PointList points = new PointList();
        Point p1 = points.getPointById("P11");
        Point p2 = points.getPointById("P13");
        Line l = new Line(p1,p2);
        
        System.out.println("Sind P1 und P3 Nachbarn? " + p1.isNeighbourOf(p2));
        for(Polygon p: polygons.getPolygons()) {
            
        }
    }
    
}
