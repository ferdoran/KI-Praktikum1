/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
package test;

import data.Vector2D;

/**
 *
 * @author Roland
 */
public class VectorTest {
    
    public static void main(String[] args) {
        
        Vector2D a = new Vector2D(100, 100);
        Vector2D b = new Vector2D(50,50);
        
        System.out.println("Vektor a:" +a);
        System.out.println("Vektor b:" +b);
        System.out.println("Gegenvektor von a: " +a.opposite());
        System.out.println("Gegenvektor von b: " + b.opposite());
        a.add(b);
        System.out.println("a + b = " + a);
        a.subtract(b);
        a.subtract(b);
        System.out.println("a - b = " + a);
    }
    
}
