package gui;

import data.Line;
import data.LineList;
import data.Point;
import data.PointList;
import data.PolygonList;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roland
 */
public class DrawingPanel extends javax.swing.JPanel {
    
    final int WIDTH_NEW = 900;
    final int HEIGHT_NEW = 456;
    LineList lines;
    PointList points;
    PolygonList polygons;
    Point selected;
    final double scalingFactorX = WIDTH_NEW / (388 - 100);
    final double scalingFactorY = HEIGHT_NEW / (670 - 516);
    Point lastVisited;
    Point lastPosition;
    /**
     * Creates new form DrawingPanel
     */
    public DrawingPanel() {
        initComponents();
        this.setBackground(Color.white);
    }
    
    public boolean setPolygons(PolygonList p) {
        if(p == null) {
            return false;
        }
        polygons = p;
        return true;
    }
    
    public boolean setPoints(PointList p) {
        if(p == null) {
            return false;
        }
        points = p;
        return true;
    }
    
    public boolean setLines(LineList l) {
        if(l == null) {
            return false;
        }
        lines = l;
        return true;
    }
    
    public void drawActualPosition(Point p) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.blue);
        int x,y;
        if(lastPosition == null) {
            
            x = (int) p.getX();
            y = (int) p.getY();
            lastPosition = p;
        }
        else {
            x = (int) lastPosition.getX();
            y = (int) lastPosition.getY();
            g.setColor(this.getBackground());
            g.drawRect((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+10, 6, 6);
            g.fillRect((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+10, 6, 6);
            lastPosition = p;
            x = (int) p.getX();
            y = (int) p.getY();
        }
        
        g.setColor(Color.blue);
        g.drawRect((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+10, 6, 6);
        g.fillRect((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+10, 6, 6);
    }
    
    
    public void drawAllPoints() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.lightGray);
        for(Point p: points.getAllPoints()) {
                int x = (int) p.getX();
                int y = (int) p.getY();
                String id = p.getId();
                
                g.drawOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
                g.fillOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
                if(id.equals("S") || id.equals("Z")) {
                    g.drawString(id,(int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)+10+25-2);
                }
        }
        if(selected != null) {
            
            int x = (int) selected.getX();
            int y = (int) selected.getY();
            String name = selected.getId();
            g.setColor(Color.RED);
            g.drawOval(x, y, 5, 5);
            g.fillOval(x, y, 5, 5);
            g.drawString(name, x, 15+y);
            
        }
        
    }
    
    public void markPoint(Point p, boolean aufg3) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.black);
        int x = (int) p.getX();
        int y = (int) p.getY();
        if(!aufg3) {
            if(lastVisited == null) {
                lastVisited = p;
            }
            else {
                drawPath(lastVisited, p, aufg3);
                lastVisited = p;
            }
        }
    
        g.drawOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
        g.fillOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
        
    }
    
    public void trackPath(Point p, boolean aufgabe3) {
        if(lastVisited == null) {
            lastVisited = p;
        }
        else if (lastVisited.isNeighbourOf(p)) {
            drawPath(lastVisited, p, aufgabe3);
            lastVisited = p;
        }
    }
    
    public void drawPath(Point from, Point to, boolean aufgabe3) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.red);
        if(aufgabe3) {
            int xFrom = (int) from.getX();
            int yFrom = (int) from.getY();
            int xTo = (int) to.getX();
            int yTo = (int) to.getY();
            g.drawLine((int) scalingFactorX * xFrom - xFrom,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yFrom)*scalingFactorY) +20,(int) scalingFactorX * xTo - xTo,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yTo)*scalingFactorY)+20);
        }
        else {
            if(from.isNeighbourOf(to)) {
                int xFrom = (int) from.getX();
                int yFrom = (int) from.getY();
                int xTo = (int) to.getX();
                int yTo = (int) to.getY();
                g.drawLine((int) scalingFactorX * xFrom - xFrom,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yFrom)*scalingFactorY) +20,(int) scalingFactorX * xTo - xTo,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yTo)*scalingFactorY)+20);
        
            }
        }
    }
    
    public void drawAllLines() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.lightGray);
        for(Line l: lines.getList()) {
            int xFrom = (int) l.getP1().getX();
            int yFrom = (int) l.getP1().getY();
            int xTo = (int) l.getP2().getX();
            int yTo = (int) l.getP2().getY();
            g.drawLine((int) scalingFactorX * xFrom - xFrom,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yFrom)*scalingFactorY) +20,(int) scalingFactorX * xTo - xTo,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yTo)*scalingFactorY)+20);
        }
        
    }
    
    public void clear() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(this.getBackground());
        super.paintComponent(g);
        lastVisited = null;
        //g.drawRect(0, 0, WIDTH_NEW+20, HEIGHT_NEW+20);
    }
    
    public void drawAllPolygons() {
        Graphics g =  this.getGraphics();
        g.setColor(Color.lightGray);
        clear();
        
        for(Polygon p : polygons.getPolygons()) {
            int[] x = transferPolygonXPoints(p.xpoints);
            int[] y = transferPolygonYPoints(p.ypoints);
            g.drawPolygon(x, y, p.npoints);
        }
        
        int x = (int) points.getPointById("Z").getX();
        int y = (int) points.getPointById("Z").getY();
        int x1 = (int) points.getPointById("S").getX();
        int y1 = (int) points.getPointById("S").getY();
        
        g.drawOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
        g.fillOval((int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)-2+20, 4, 4);
        g.drawString("Z",(int) scalingFactorX * x - x-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y)*scalingFactorY)+10+25-2);
        
        g.drawOval((int) scalingFactorX * x1 - x1-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y1)*scalingFactorY)-2+20, 4, 4);
        g.fillOval((int) scalingFactorX * x1 - x1-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y1)*scalingFactorY)-2+20, 4, 4);
        g.drawString("S",(int) scalingFactorX * x1 - x1-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - y1)*scalingFactorY)+10+25-2);
        
        
    }
    
    public void drawFinalPath(List<String> idList) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.green);
//        super.paintComponent(g);
//        drawAllPoints();
//        drawAllLines();
        Point last = null;
        
        for(String id : idList) {
            if(last == null) {
                last = points.getPointById(id);
                continue;
            }
            int lastX = (int) last.getX();
            int lastY = (int) last.getY();
            Point current = points.getPointById(id);
            int thisX = (int) current.getX();
            int thisY = (int) current.getY();
            g.drawOval((int) scalingFactorX * lastX - lastX-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - lastY)*scalingFactorY)-2+20, 4, 4);
            g.fillOval((int) scalingFactorX * lastX - lastX-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - lastY)*scalingFactorY)-2+20, 4, 4);
            
            g.drawOval((int) scalingFactorX * thisX - thisX-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - thisY)*scalingFactorY)-2+20, 4, 4);
            g.fillOval((int) scalingFactorX * thisX - thisX-2,HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - thisY)*scalingFactorY)-2+20, 4, 4);
            g.drawLine((int) scalingFactorX * lastX - lastX,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - lastY)*scalingFactorY) +20,(int) scalingFactorX * thisX - thisX,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - thisY)*scalingFactorY)+20);
            last = current;
        }
    }
    
    public void clearLastVisited() {
        lastVisited = null;
    }
    
    public void selectPoint(Point p) {
        selected = p;
        Graphics2D g = (Graphics2D) this.getGraphics();
        super.paintComponent(g);
        int x = (int) p.getX();
        int y = (int) p.getY();
        
        
        drawAllPoints();
        g.setColor(Color.RED);
        g.drawOval(x, y, 5, 5);
        g.fillOval(x, y, 5, 5);
        g.drawString(p.getId() + "("+(x)+","+ y + ")", x, 15+y);
    }
    
    public void drawPoint(Point p) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        super.paintComponent(g);
        int x = (int) p.getX();
        int y = (int) p.getY();
        g.drawOval(x, y, 5, 5);
        g.fillOval(x, y, 5, 5);
        g.drawString(p.getId() + "("+(x)+","+ y + ")", x, 15+y);
    }

    
    
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private int[] transferPolygonXPoints(int[] xpoints) {
        int[] result = new int[xpoints.length];
        for(int i=0;i < xpoints.length; i++) {
            result[i] = (int) scalingFactorX * xpoints[i] - xpoints[i];
        }
        return result;
    }
    
    private int[] transferPolygonYPoints(int[] xpoints) {
        int[] result = new int[xpoints.length];
        for(int i=0;i < xpoints.length; i++) {
            result[i] = HEIGHT_NEW - (int)(Math.abs(HEIGHT_NEW - xpoints[i])*scalingFactorY)+20;
        }
        return result;
    }
    
    public void drawLines(ArrayList<Line> lines) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.red);
        
        for(Line l : lines) {
            Point p1 = l.getP1();
            Point p2 = l.getP2();
            int xFrom = (int) p1.getX();
            int yFrom = (int) p1.getY();
            int xTo = (int) p2.getX();
            int yTo = (int) p2.getY();
            if(points.contains(p1)) {
                if(p1.isNeighbourOf(p2)) {
                    g.drawLine((int) scalingFactorX * xFrom - xFrom,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yFrom)*scalingFactorY) +20,(int) scalingFactorX * xTo - xTo,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yTo)*scalingFactorY)+20);
                }
            }
            else {
                g.drawLine((int) scalingFactorX * xFrom - xFrom,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yFrom)*scalingFactorY) +20,(int) scalingFactorX * xTo - xTo,HEIGHT_NEW -  (int) (Math.abs(HEIGHT_NEW - yTo)*scalingFactorY)+20);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
