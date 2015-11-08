package data;

import java.awt.geom.Line2D;

/**
 *
 * @author Roland
 */
public class Line extends Line2D.Double {

    public Line(Point pFrom, Point pTo) {
        super(pFrom,pTo);

    }
    
    @Override
    public Point getP1() {
        return (Point) super.getP1();
    }
    
    @Override
    public Point getP2() {
        return (Point) super.getP2();
    }
}
