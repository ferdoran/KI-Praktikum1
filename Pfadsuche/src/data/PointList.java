package data;


import java.util.HashSet;
import java.util.NoSuchElementException;
/**
 *
 * @author Roland
 */
public final class PointList {
    
    HashSet<Point> points;
    
    
    
    public PointList() {
        points = new HashSet<>();
        
        
        //Definition der Punkte
        points.add(new Point(352, 543, "TEST"));
        
        //Start und Zielpunkt
        points.add(new Point(100,534, "S"));
        points.add(new Point(388,663, "Z"));
        
        //Polygon1
        points.add(new Point(220,616, "P11"));
        points.add(new Point(220,666, "P12"));
        points.add(new Point(251,670, "P13"));
        points.add(new Point(272,647, "P14"));
        
        //Polygon2
        points.add(new Point(341,655, "P21"));
        points.add(new Point(359,667, "P22"));
        points.add(new Point(374,651, "P23"));
        points.add(new Point(366,577, "P24"));
        
        //Polygon3
        points.add(new Point(311,530, "P31"));
        points.add(new Point(311,559, "P32"));
        points.add(new Point(339,578, "P33"));
        points.add(new Point(361,560, "P34"));
        points.add(new Point(361,528, "P35"));
        points.add(new Point(336,516, "P36"));
        
        //Polygon4
        points.add(new Point(105,628, "P41"));
        points.add(new Point(151,670, "P42"));
        points.add(new Point(180,629, "P43"));
        points.add(new Point(156,577, "P44"));
        points.add(new Point(113,587, "P45"));
        
        //Polygon5
        points.add(new Point(118,517, "P51"));
        points.add(new Point(245,517, "P52"));
        points.add(new Point(245,557, "P53"));
        points.add(new Point(118,557, "P54"));
        
        //Polygon6
        points.add(new Point(280,583, "P61"));
        points.add(new Point(333,583, "P62"));
        points.add(new Point(333,665, "P63"));
        points.add(new Point(280,665, "P64"));
        
        //Polygon7
        points.add(new Point(252,594, "P71"));
        points.add(new Point(290,562, "P72"));
        points.add(new Point(264,538, "P73"));
        
        //Polygon8
        points.add(new Point(198,635, "P81"));
        points.add(new Point(217,574, "P82"));
        points.add(new Point(182,574, "P83"));
        
        
        //Definition der Nachbaren
        
        //Nachbarn des Startknotens
        getPointById("S").addNeighbour(getPointById("P51"));
        getPointById("S").addNeighbour(getPointById("P54"));
        getPointById("S").addNeighbour(getPointById("P45"));
        getPointById("S").addNeighbour(getPointById("P41"));
        
        //Nachbarn des Knoten P11
        getPointById("P11").addNeighbour(getPointById("P81"));
        getPointById("P11").addNeighbour(getPointById("P82"));
        getPointById("P11").addNeighbour(getPointById("P53"));
        getPointById("P11").addNeighbour(getPointById("P73"));
        getPointById("P11").addNeighbour(getPointById("P71"));
        getPointById("P11").addNeighbour(getPointById("P61"));
        getPointById("P11").addNeighbour(getPointById("P14"));
        getPointById("P11").addNeighbour(getPointById("P12"));
        getPointById("P11").addNeighbour(getPointById("P32"));
        
        //Nachbarn des Knoten P12
        getPointById("P12").addNeighbour(getPointById("P42"));
        getPointById("P12").addNeighbour(getPointById("P43"));
        getPointById("P12").addNeighbour(getPointById("P81"));
        getPointById("P12").addNeighbour(getPointById("P13"));
        getPointById("P12").addNeighbour(getPointById("P11"));
        
        //Nachbarn des Knoten P13
        getPointById("P13").addNeighbour(getPointById("P12"));
        getPointById("P13").addNeighbour(getPointById("P13"));
        getPointById("P13").addNeighbour(getPointById("P64"));
        getPointById("P13").addNeighbour(getPointById("P42"));
        getPointById("P13").addNeighbour(getPointById("P63"));
        getPointById("P13").addNeighbour(getPointById("P22"));
        
        //Nachbarn des Knoten P14
        getPointById("P14").addNeighbour(getPointById("P11"));
        getPointById("P14").addNeighbour(getPointById("P13"));
        getPointById("P14").addNeighbour(getPointById("P82"));
        getPointById("P14").addNeighbour(getPointById("P71"));
        getPointById("P14").addNeighbour(getPointById("P64"));
        getPointById("P14").addNeighbour(getPointById("P61"));
        
        
        
        //Nachbarn des Knoten P21
        getPointById("P21").addNeighbour(getPointById("P22"));
        getPointById("P21").addNeighbour(getPointById("P24"));
        getPointById("P21").addNeighbour(getPointById("P63"));
        getPointById("P21").addNeighbour(getPointById("P33"));
        getPointById("P21").addNeighbour(getPointById("P62"));
        
        //Nachbarn des Knoten P22
        getPointById("P22").addNeighbour(getPointById("P21"));
        getPointById("P22").addNeighbour(getPointById("P23"));
        getPointById("P22").addNeighbour(getPointById("Z"));
        getPointById("P22").addNeighbour(getPointById("P63"));
        getPointById("P22").addNeighbour(getPointById("P13"));
        getPointById("P22").addNeighbour(getPointById("P64"));
        
        //Nachbarn des Knoten P23
        getPointById("P23").addNeighbour(getPointById("P22"));
        getPointById("P23").addNeighbour(getPointById("P24"));
        getPointById("P23").addNeighbour(getPointById("PZ"));
        
        //Nachbarn des Knoten P24
        getPointById("P24").addNeighbour(getPointById("P23"));
        getPointById("P24").addNeighbour(getPointById("P21"));
        getPointById("P24").addNeighbour(getPointById("Z"));
        getPointById("P24").addNeighbour(getPointById("P63"));
        getPointById("P24").addNeighbour(getPointById("P62"));
        getPointById("P24").addNeighbour(getPointById("P33"));
        getPointById("P24").addNeighbour(getPointById("P34"));
        getPointById("P24").addNeighbour(getPointById("P35"));
        
        //Nachbarn des Knoten P31
        getPointById("P31").addNeighbour(getPointById("P32"));
        getPointById("P31").addNeighbour(getPointById("P36"));
        getPointById("P31").addNeighbour(getPointById("P52"));
        getPointById("P31").addNeighbour(getPointById("P73"));
        getPointById("P31").addNeighbour(getPointById("P72"));
        getPointById("P31").addNeighbour(getPointById("P61"));
        
        //Nachbarn des Knoten P32
        getPointById("P32").addNeighbour(getPointById("P33"));
        getPointById("P32").addNeighbour(getPointById("P31"));
        getPointById("P32").addNeighbour(getPointById("P52"));
        getPointById("P32").addNeighbour(getPointById("P73"));
        getPointById("P32").addNeighbour(getPointById("P72"));
        getPointById("P32").addNeighbour(getPointById("P61"));
        getPointById("P32").addNeighbour(getPointById("P64"));
        getPointById("P32").addNeighbour(getPointById("P71"));
        getPointById("P32").addNeighbour(getPointById("P11"));
        
        //Nachbarn des Knoten P33
        getPointById("P33").addNeighbour(getPointById("P32"));
        getPointById("P33").addNeighbour(getPointById("P34"));
        getPointById("P33").addNeighbour(getPointById("P62"));
        getPointById("P33").addNeighbour(getPointById("P61"));
        getPointById("P33").addNeighbour(getPointById("P21"));
        getPointById("P33").addNeighbour(getPointById("P24"));
        getPointById("P33").addNeighbour(getPointById("P63"));
        getPointById("P33").addNeighbour(getPointById("P72"));
        getPointById("P33").addNeighbour(getPointById("P73"));
        
        //Nachbarn des Knoten P34
        getPointById("P34").addNeighbour(getPointById("P33"));
        getPointById("P34").addNeighbour(getPointById("P35"));
        getPointById("P34").addNeighbour(getPointById("P24"));
        getPointById("P34").addNeighbour(getPointById("P21"));
        getPointById("P34").addNeighbour(getPointById("P63"));
        
        //Nachbarn des Knoten P35
        getPointById("P35").addNeighbour(getPointById("P34"));
        getPointById("P35").addNeighbour(getPointById("P36"));
        getPointById("P35").addNeighbour(getPointById("Z"));
        getPointById("P35").addNeighbour(getPointById("P24"));
        
        //Nachbarn des Knoten P36
        getPointById("P36").addNeighbour(getPointById("P35"));
        getPointById("P36").addNeighbour(getPointById("P31"));
        getPointById("P36").addNeighbour(getPointById("P73"));
        getPointById("P36").addNeighbour(getPointById("P52"));
        
        //Nachbarn des Knoten 41
        getPointById("P41").addNeighbour(getPointById("P42"));
        getPointById("P41").addNeighbour(getPointById("P45"));
        getPointById("P41").addNeighbour(getPointById("S"));
        
        //Nachbarn des Knoten 42
        getPointById("P42").addNeighbour(getPointById("P41"));
        getPointById("P42").addNeighbour(getPointById("P43"));
        getPointById("P42").addNeighbour(getPointById("P81"));
        getPointById("P42").addNeighbour(getPointById("P12"));
        getPointById("P42").addNeighbour(getPointById("P13"));
        
        //Nachbarn des Knoten 43
        getPointById("P43").addNeighbour(getPointById("P42"));
        getPointById("P43").addNeighbour(getPointById("P44"));
        getPointById("P43").addNeighbour(getPointById("P81"));
        getPointById("P43").addNeighbour(getPointById("P83"));
        getPointById("P43").addNeighbour(getPointById("P12"));
        
        //Nachbarn des Knoten 44
        getPointById("P44").addNeighbour(getPointById("P43"));
        getPointById("P44").addNeighbour(getPointById("P45"));
        getPointById("P44").addNeighbour(getPointById("P81"));
        getPointById("P44").addNeighbour(getPointById("P83"));
        getPointById("P44").addNeighbour(getPointById("P53"));
        getPointById("P44").addNeighbour(getPointById("P54"));
        
        //Nachbarn des Knoten 45
        getPointById("P45").addNeighbour(getPointById("P41"));
        getPointById("P45").addNeighbour(getPointById("P44"));
        getPointById("P45").addNeighbour(getPointById("S"));
        getPointById("P45").addNeighbour(getPointById("P51"));
        getPointById("P45").addNeighbour(getPointById("P54"));
        
        //Nachbarn des Knoten 51
        getPointById("P51").addNeighbour(getPointById("P54"));
        getPointById("P51").addNeighbour(getPointById("P52"));
        getPointById("P51").addNeighbour(getPointById("S"));
        getPointById("P51").addNeighbour(getPointById("P45"));
        
        //Nachbarn des Knoten 52
        getPointById("P52").addNeighbour(getPointById("P51"));
        getPointById("P52").addNeighbour(getPointById("P53"));
        getPointById("P52").addNeighbour(getPointById("P71"));
        getPointById("P52").addNeighbour(getPointById("P72"));
        getPointById("P52").addNeighbour(getPointById("P73"));
        getPointById("P52").addNeighbour(getPointById("P62"));
        getPointById("P52").addNeighbour(getPointById("P31"));
        getPointById("P52").addNeighbour(getPointById("P32"));
        getPointById("P52").addNeighbour(getPointById("P36"));
        
        //Nachbarn des Knoten 53
        getPointById("P53").addNeighbour(getPointById("P52"));
        getPointById("P53").addNeighbour(getPointById("P54"));
        getPointById("P53").addNeighbour(getPointById("P73"));
        getPointById("P53").addNeighbour(getPointById("P71"));
        getPointById("P53").addNeighbour(getPointById("P11"));
        getPointById("P53").addNeighbour(getPointById("P81"));
        getPointById("P53").addNeighbour(getPointById("P82"));
        getPointById("P53").addNeighbour(getPointById("P83"));
        getPointById("P53").addNeighbour(getPointById("P44"));
        getPointById("P53").addNeighbour(getPointById("P45"));
        
        //Nachbarn des Knoten 54
        getPointById("P54").addNeighbour(getPointById("P53"));
        getPointById("P54").addNeighbour(getPointById("P51"));
        getPointById("P54").addNeighbour(getPointById("S"));
        getPointById("P54").addNeighbour(getPointById("P44"));
        getPointById("P54").addNeighbour(getPointById("P45"));
        getPointById("P54").addNeighbour(getPointById("P83"));
        getPointById("P54").addNeighbour(getPointById("P82"));
        
        //Nachbarn des Knoten 61
        getPointById("P61").addNeighbour(getPointById("P62"));
        getPointById("P61").addNeighbour(getPointById("P64"));
        getPointById("P61").addNeighbour(getPointById("P31"));
        getPointById("P61").addNeighbour(getPointById("P32"));
        getPointById("P61").addNeighbour(getPointById("P33"));
        getPointById("P61").addNeighbour(getPointById("P71"));
        getPointById("P61").addNeighbour(getPointById("P72"));
        getPointById("P61").addNeighbour(getPointById("P11"));
        getPointById("P61").addNeighbour(getPointById("P14"));
        
        //Nachbarn des Knoten 62
        getPointById("P62").addNeighbour(getPointById("P61"));
        getPointById("P62").addNeighbour(getPointById("P63"));
        getPointById("P62").addNeighbour(getPointById("P31"));
        getPointById("P62").addNeighbour(getPointById("P32"));
        getPointById("P62").addNeighbour(getPointById("P21"));
        getPointById("P62").addNeighbour(getPointById("P24"));
        getPointById("P62").addNeighbour(getPointById("P72"));
        getPointById("P62").addNeighbour(getPointById("P73"));
        getPointById("P62").addNeighbour(getPointById("P52"));
        
        //Nachbarn des Knoten 63
        getPointById("P63").addNeighbour(getPointById("P64"));
        getPointById("P63").addNeighbour(getPointById("P62"));
        getPointById("P63").addNeighbour(getPointById("P21"));
        getPointById("P63").addNeighbour(getPointById("P22"));
        getPointById("P63").addNeighbour(getPointById("P24"));
        getPointById("P63").addNeighbour(getPointById("P33"));
        getPointById("P63").addNeighbour(getPointById("P34"));
        getPointById("P63").addNeighbour(getPointById("P13"));
        
        //Nachbarn des Knoten 64
        getPointById("P64").addNeighbour(getPointById("P61"));
        getPointById("P64").addNeighbour(getPointById("P63"));
        getPointById("P64").addNeighbour(getPointById("P13"));
        getPointById("P64").addNeighbour(getPointById("P14"));
        getPointById("P64").addNeighbour(getPointById("P21"));
        getPointById("P64").addNeighbour(getPointById("P22"));
        
        //Nachbarn des Knoten 71
        getPointById("P71").addNeighbour(getPointById("P72"));
        getPointById("P71").addNeighbour(getPointById("P73"));
        getPointById("P71").addNeighbour(getPointById("P52"));
        getPointById("P71").addNeighbour(getPointById("P53"));
        getPointById("P71").addNeighbour(getPointById("P82"));
        getPointById("P71").addNeighbour(getPointById("P11"));
        getPointById("P71").addNeighbour(getPointById("P12"));
        getPointById("P71").addNeighbour(getPointById("P61"));
        getPointById("P71").addNeighbour(getPointById("P32"));
        
        //Nachbarn des Knoten 72
        getPointById("P72").addNeighbour(getPointById("P71"));
        getPointById("P72").addNeighbour(getPointById("P73"));
        getPointById("P72").addNeighbour(getPointById("P31"));
        getPointById("P72").addNeighbour(getPointById("P32"));
        getPointById("P72").addNeighbour(getPointById("P33"));
        getPointById("P72").addNeighbour(getPointById("P61"));
        getPointById("P72").addNeighbour(getPointById("P62"));
        getPointById("P72").addNeighbour(getPointById("P52"));
        
        //Nachbarn des Knoten 73
        getPointById("P73").addNeighbour(getPointById("P71"));
        getPointById("P73").addNeighbour(getPointById("P72"));
        getPointById("P73").addNeighbour(getPointById("P31"));
        getPointById("P73").addNeighbour(getPointById("P32"));
        getPointById("P73").addNeighbour(getPointById("P33"));
        getPointById("P73").addNeighbour(getPointById("P36"));
        getPointById("P73").addNeighbour(getPointById("P62"));
        getPointById("P73").addNeighbour(getPointById("P52"));
        getPointById("P73").addNeighbour(getPointById("P53"));
        getPointById("P73").addNeighbour(getPointById("P11"));
        getPointById("P73").addNeighbour(getPointById("P81"));
        
        //Nachbarn des Knoten 81
        getPointById("P81").addNeighbour(getPointById("P82"));
        getPointById("P81").addNeighbour(getPointById("P83"));
        getPointById("P81").addNeighbour(getPointById("P11"));
        getPointById("P81").addNeighbour(getPointById("P12"));
        getPointById("P81").addNeighbour(getPointById("P42"));
        getPointById("P81").addNeighbour(getPointById("P43"));
        getPointById("P81").addNeighbour(getPointById("P44"));
        getPointById("P81").addNeighbour(getPointById("P53"));
        getPointById("P81").addNeighbour(getPointById("P73"));
        
        //Nachbarn des Knoten 82
        getPointById("P82").addNeighbour(getPointById("P81"));
        getPointById("P82").addNeighbour(getPointById("P83"));
        getPointById("P82").addNeighbour(getPointById("P53"));
        getPointById("P82").addNeighbour(getPointById("P54"));
        getPointById("P82").addNeighbour(getPointById("P11"));
        getPointById("P82").addNeighbour(getPointById("P12"));
        getPointById("P82").addNeighbour(getPointById("P14"));
        getPointById("P82").addNeighbour(getPointById("P71"));
        
        //Nachbarn des Knoten 83
        getPointById("P83").addNeighbour(getPointById("P81"));
        getPointById("P83").addNeighbour(getPointById("P82"));
        getPointById("P83").addNeighbour(getPointById("P53"));
        getPointById("P83").addNeighbour(getPointById("P54"));
        getPointById("P83").addNeighbour(getPointById("P43"));
        getPointById("P83").addNeighbour(getPointById("P44"));
    }
    
    public boolean addPoint(Point p) {
        if(!exists(p)) {
           points.add(p);
           return true;
        }
        else {
            return false;
        }
            
    }
    
    public Point getPoint(double x, double y) {
        
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        
        return null;
        
    }
    
    public Point getPointById(String id) {
        for(Point p: points)  {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean exists(Point p) {
        try {
            this.getPoint(p.getX(), p.getY());
        }
        catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
    public boolean exists(double x, double y) {
        try {
            getPoint(x, y);
        }
        catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
    public HashSet<Point> getAllPoints() {
        return (HashSet<Point>) points.clone();
    }
    
    
    public boolean removePoint(Point p) {
        return points.remove(p);
    }
    
    public boolean removePoint(double x, double y) {
        for(Point p : points) {
            if(p.getX() == x && p.getY() == y) {
                return points.remove(p);
            }
        }
        return false;
    }

    public HashSet<Point> getPolygonPoints(String polygonName) {
        HashSet<Point> result = new HashSet<>();
        for(Point p: points) {
            if(p.getId().contains(polygonName)) {
                result.add(p);
            }
        }
        return result;
    }
    
    
}
