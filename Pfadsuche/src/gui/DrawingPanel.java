/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;

/**
 *
 * @author Roland
 */
public class DrawingPanel extends javax.swing.JPanel {
    private HashSet<Point> p;

    /**
     * Creates new form DrawingPanel
     */
    public DrawingPanel() {
        initComponents();        
    }
    
    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        for(Point point : p) {
            g.drawOval((int)point.getX(), (int)point.getY(), 3, 3);
        }
    }
    
    public void setPoints(HashSet<Point> po) {
        p = po;
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
