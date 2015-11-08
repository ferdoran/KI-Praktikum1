package data;

import java.util.ArrayList;

/**
 *
 * @author Roland
 */
public class LineList {
    PointList points;
    ArrayList<Line> lines;
    
    public LineList(data.PointList p) {
        lines = new ArrayList<>();
        points = p;
        
        //Polygon-Linien
        
        //Polygon 1
        lines.add(new Line(p.getPointById("P11"), p.getPointById("P14")));
        lines.add(new Line(p.getPointById("P11"), p.getPointById("P12")));
        lines.add(new Line(p.getPointById("P12"), p.getPointById("P13")));
        lines.add(new Line(p.getPointById("P13"), p.getPointById("P14")));
        
        //Polygon 2
        lines.add(new Line(p.getPointById("P21"), p.getPointById("P24")));
        lines.add(new Line(p.getPointById("P21"), p.getPointById("P22")));
        lines.add(new Line(p.getPointById("P22"), p.getPointById("P23")));
        lines.add(new Line(p.getPointById("P23"), p.getPointById("P24")));
        
        //Polygon 3
        lines.add(new Line(p.getPointById("P31"), p.getPointById("P36")));
        lines.add(new Line(p.getPointById("P31"), p.getPointById("P32")));
        lines.add(new Line(p.getPointById("P32"), p.getPointById("P33")));
        lines.add(new Line(p.getPointById("P33"), p.getPointById("P34")));
        lines.add(new Line(p.getPointById("P34"), p.getPointById("P35")));
        lines.add(new Line(p.getPointById("P35"), p.getPointById("P36")));
        
        //Polygon 4
        lines.add(new Line(p.getPointById("P41"), p.getPointById("P45")));
        lines.add(new Line(p.getPointById("P41"), p.getPointById("P42")));
        lines.add(new Line(p.getPointById("P42"), p.getPointById("P43")));
        lines.add(new Line(p.getPointById("P43"), p.getPointById("P44")));
        lines.add(new Line(p.getPointById("P44"), p.getPointById("P45")));
        
        //Polygon 5
        lines.add(new Line(p.getPointById("P51"), p.getPointById("P54")));
        lines.add(new Line(p.getPointById("P51"), p.getPointById("P52")));
        lines.add(new Line(p.getPointById("P52"), p.getPointById("P53")));
        lines.add(new Line(p.getPointById("P53"), p.getPointById("P54")));
        
        //Polygon 6
        lines.add(new Line(p.getPointById("P61"), p.getPointById("P64")));
        lines.add(new Line(p.getPointById("P61"), p.getPointById("P62")));
        lines.add(new Line(p.getPointById("P62"), p.getPointById("P63")));
        lines.add(new Line(p.getPointById("P63"), p.getPointById("P64")));
        
        //Polygon 7
        lines.add(new Line(p.getPointById("P71"), p.getPointById("P73")));
        lines.add(new Line(p.getPointById("P71"), p.getPointById("P72")));
        lines.add(new Line(p.getPointById("P72"), p.getPointById("P73")));
        
        //Polygon 8
        lines.add(new Line(p.getPointById("P81"), p.getPointById("P83")));
        lines.add(new Line(p.getPointById("P81"), p.getPointById("P82")));
        lines.add(new Line(p.getPointById("P82"), p.getPointById("P83")));
        
    }
    
    
    public Line getLine(Point from, Point to) {
        
        for(Line line : lines) {
            if((line.getPointFrom().equals(from) && line.getPointTo().equals(to)) || (line.getPointFrom().equals(to) && line.getPointTo().equals(from))) {
                return line;
            }
        }
        return null;
    }
    
    public Line getLine(String idFrom, String idTo) {
        
        for(Line line : lines) {
            if((line.getPointFrom().getId().equals(idFrom) && line.getPointTo().getId().equals(idTo)) || (line.getPointFrom().getId().equals(idTo) && line.getPointTo().getId().equals(idFrom))) {
                return line;
            }
        }

        return null;
    }
    
    public ArrayList<Line> getList() {
        return lines;
    }
}
