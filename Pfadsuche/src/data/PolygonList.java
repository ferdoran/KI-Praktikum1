package data;

import java.awt.Polygon;
import java.util.HashSet;

/**
 *
 * @author Roland
 */
public class PolygonList {
    HashSet<Polygon> polygons;
    
    public PolygonList() {
        polygons = new HashSet<>();
            


      Polygon p1 = new Polygon();
      Polygon p2 = new Polygon();
      Polygon p3 = new Polygon();
      Polygon p4 = new Polygon();
      Polygon p5 = new Polygon();
      Polygon p6 = new Polygon();
      Polygon p7 = new Polygon();
      Polygon p8 = new Polygon();

      p1.addPoint(220, 616);
      p1.addPoint(220, 666);
      p1.addPoint(251, 670);
      p1.addPoint(272, 647);

      p2.addPoint(341, 655);
      p2.addPoint(359, 667);
      p2.addPoint(374, 651);
      p2.addPoint(366, 577);

      p3.addPoint(311,530);
      p3.addPoint(311,559);
      p3.addPoint(339,578);
      p3.addPoint(361,560);
      p3.addPoint(361,528);
      p3.addPoint(336,516);

      p4.addPoint(105,628);
      p4.addPoint(151,670);
      p4.addPoint(180,629);
      p4.addPoint(156,577);
      p4.addPoint(113,587);

      p5.addPoint(118,517);
      p5.addPoint(245,517);
      p5.addPoint(245,557);
      p5.addPoint(118,557);

      p6.addPoint(280,583);
      p6.addPoint(333,583);
      p6.addPoint(333,665);
      p6.addPoint(280,665);

      p7.addPoint(252,594);
      p7.addPoint(290,562);
      p7.addPoint(264,538);

      p8.addPoint(198,635);
      p8.addPoint(217,574);
      p8.addPoint(182,574);          

      polygons.add(p1);
      polygons.add(p2);
      polygons.add(p3);
      polygons.add(p4);
      polygons.add(p5);
      polygons.add(p6);
      polygons.add(p7);
      polygons.add(p8);
        
    }
    
    
    public HashSet<Polygon> getPolygons() {
        return polygons;
    }
    
    public boolean inPolygon(Point p) {
        for(Polygon pol : polygons) {
            if(pol.contains(p)) {
                return true;
            }
        }
        
        return false;
    }
}
