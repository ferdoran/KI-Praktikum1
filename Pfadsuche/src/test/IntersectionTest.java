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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Roland
 */
public class IntersectionTest {
    
    public static void main(String[] args) {
        PolygonList polygons = new PolygonList();
        PointList points = new PointList();
        
        Line2D.Double a = new Line(points.getPoint(118,517), points.getPoint(118, 557));
        Line2D.Double b = new Line(points.getPoint(113,587), points.getPoint(105, 625));
        
        
        Line2D.linesIntersect(118, 517, 118, 557, 113, 587, 105, 625);
        System.out.println(Line2D.linesIntersect(118, 517, 118, 557, 156, 577, 118, 557));
    }
    
}
