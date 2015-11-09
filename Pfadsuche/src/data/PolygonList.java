/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package data;

import java.awt.Polygon;
import java.util.HashSet;

/**
 *
 * @author Roland
 */
public class PolygonList {
    HashSet<Polygon> polygons;
    
    public PolygonList(PointList p) {
        polygons = new HashSet<>();
        
        
        
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P1")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P2")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P3")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P4")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P5")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P6")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P7")));
        polygons.add(createPolygonFromPointList(p.getPolygonPoints("P8")));
        
    }
    
    private Polygon createPolygonFromPointList(HashSet<Point> points) {
        int[] xpoints = new int[points.size()];
        int[] ypoints = new int[points.size()];
        int i = 0;
        for(Point p : points) {
            xpoints[i] = (int) p.getX();
            ypoints[i] = (int) p.getY();
            i++;
        }
        return new Polygon(xpoints, ypoints, points.size());
    }
    
    public HashSet<Polygon> getPolygons() {
        return polygons;
    }
}
