package test;
import data.Point;
/**
 *
 * @author Mahdi
 * Diese Klasse dient dem Test des AStar-Algorithmus welcher im Package
 * 'algorithms' verwaltet wird.
 */
public class AStarTest {
    
    public static void main(String[] args){
        
        //Anlegen der Nodes aus der Aufgabe als NavNodes
        Point s = new Point(100,534,"Start");
        Point z = new Point(388,663,"Ziel");
        
        //Polygon 1
        Point p1_1 = new Point(220,616,"P1_1");
        Point p1_2 = new Point(220,666,"P1_2");
        Point p1_3 = new Point(251,670,"P1_3");
        Point p1_4 = new Point(272,647,"P1_4");
        
        //Polygon 2
        Point p2_1 = new Point(341,655,"P2_1");
        Point p2_2 = new Point(359,667,"P2_2");
        Point p2_3 = new Point(374,651,"P2_3");
        Point p2_4 = new Point(366,577,"P2_4");
        
        //Polygon 3
        Point p3_1 = new Point(311,530,"P3_1");
        Point p3_2 = new Point(311,559,"P3_2");
        Point p3_3 = new Point(339,578,"P3_3");
        Point p3_4 = new Point(361,560,"P3_4");
        Point p3_5 = new Point(361,528,"P3_5");
        Point p3_6 = new Point(336,516,"P3_6");
        
        //Polygon 4
        Point p4_1 = new Point(105,628,"P4_1");
        Point p4_2 = new Point(151,670,"P4_2");
        Point p4_3 = new Point(180,629,"P4_3");
        Point p4_4 = new Point(156,577,"P4_4");
        Point p4_5 = new Point(113,587,"P4_5");
        
        //Polygon 5
        Point p5_1 = new Point(118,517,"P5_1");
        Point p5_2 = new Point(245,517,"P5_2");
        Point p5_3 = new Point(245,557,"P5_3");
        Point p5_4 = new Point(118,557,"P5_4");
        
        //Polygon 6
        Point p6_1 = new Point(280,583,"P6_1");
        Point p6_2 = new Point(333,583,"P6_2");
        Point p6_3 = new Point(333,665,"P6_3");
        Point p6_4 = new Point(280,665,"P6_4");
        
        //Polygon 7
        Point p7_1 = new Point(252,594,"P7_1");
        Point p7_2 = new Point(290,562,"P7_2");
        Point p7_3 = new Point(264,538,"P7_3");
        
        //Polygon 8
        Point p8_1 = new Point(198,635,"P8_1");
        Point p8_2 = new Point(217,574,"P8_2");
        Point p8_3 = new Point(182,574,"P8_3");
    
    
    /*  Festlegen der 'Nachbarn' für die Aufgabe. 'Nachbarn' sind in diesem
     *  Fall alle Knoten welche auf direktem Wege vom jeweiligen Knoten aus
     *  erreicht werden können, ohne durch die Hindernisse zu gehen. Vorerst noch
     *  'von Hand'.
     */
        //Nachbarn des Startknoten s
        s.addNeighbour(p5_1);
        s.addNeighbour(p5_4);
        s.addNeighbour(p4_5);
        s.addNeighbour(p4_1);
        
        //Nachbarn des Knoten p1_1
        p1_1.addNeighbour(p8_1);
        p1_1.addNeighbour(p8_2);
        p1_1.addNeighbour(p5_3);
        p1_1.addNeighbour(p7_3);
        p1_1.addNeighbour(p7_1);
        p1_1.addNeighbour(p6_1);
        p1_1.addNeighbour(p1_4);
        p1_1.addNeighbour(p1_2);
        p1_1.addNeighbour(p3_2);
        
        //Nachbarn des Knoten p1_2
        p1_2.addNeighbour(p4_2);
        p1_2.addNeighbour(p4_3);
        p1_2.addNeighbour(p8_1);
        p1_2.addNeighbour(p1_3);
        p1_2.addNeighbour(p1_1);
        
        //Nachbarn des Knoten p1_3
        p1_3.addNeighbour(p1_2);
        p1_3.addNeighbour(p1_3);
        p1_3.addNeighbour(p6_4);
        p1_3.addNeighbour(p4_2);
        p1_3.addNeighbour(p6_3);
        p1_3.addNeighbour(p2_2);
        
        //Nachbarn des Knoten p1_4
        p1_4.addNeighbour(p1_1);
        p1_4.addNeighbour(p1_3);
        p1_4.addNeighbour(p8_2);
        p1_4.addNeighbour(p7_1);
        p1_4.addNeighbour(p6_4);
        p1_4.addNeighbour(p6_1);

        //Nachbarn des Knoten p2_1
        p2_1.addNeighbour(p2_2);
        p2_1.addNeighbour(p2_4);
        p2_1.addNeighbour(p6_2);
        p2_1.addNeighbour(p6_3);
        p2_1.addNeighbour(p3_3);
        
        //Nachbarn des Knoten p2_2
        p2_2.addNeighbour(p2_1);
        p2_2.addNeighbour(p2_3);
        p2_2.addNeighbour(z);
        p2_2.addNeighbour(p6_3);
        p2_2.addNeighbour(p1_3);
        p2_2.addNeighbour(p6_4);
        
        //Nachbarn des Knoten p2_3
        p2_3.addNeighbour(p2_2);
        p2_3.addNeighbour(p2_4);
        p2_3.addNeighbour(z);

        //Nachbarn des Knoten p2_4
        p2_4.addNeighbour(p2_3);
        p2_4.addNeighbour(p2_1);
        p2_4.addNeighbour(z);
        p2_4.addNeighbour(p6_3);
        p2_4.addNeighbour(p6_2);
        p2_4.addNeighbour(p3_3);
        p2_4.addNeighbour(p3_4);
        p2_4.addNeighbour(p3_5);
        
        //Nachbarn des Knoten p3_1
        p3_1.addNeighbour(p3_2);
        p3_1.addNeighbour(p3_6);
        p3_1.addNeighbour(p5_2);
        p3_1.addNeighbour(p7_3);
        p3_1.addNeighbour(p7_2);
        p3_1.addNeighbour(p6_1);
        
        //Nachbarn des Knoten p3_2
        p3_2.addNeighbour(p3_3);
        p3_2.addNeighbour(p3_1);
        p3_2.addNeighbour(p5_2);
        p3_2.addNeighbour(p7_3);
        p3_2.addNeighbour(p7_2);
        p3_2.addNeighbour(p6_1);
        p3_2.addNeighbour(p6_4);
        p3_2.addNeighbour(p7_1);
        p3_2.addNeighbour(p1_1);
        
        //Nachbarn des Knoten p3_3
        p3_3.addNeighbour(p3_2);
        p3_3.addNeighbour(p3_4);
        p3_3.addNeighbour(p6_2);
        p3_3.addNeighbour(p6_1);
        p3_3.addNeighbour(p2_1);
        p3_3.addNeighbour(p2_4);
        p3_3.addNeighbour(p6_3);
        p3_3.addNeighbour(p7_2);
        p3_3.addNeighbour(p7_3);
        
        
        //Nachbarn des Knoten p3_4
        p3_4.addNeighbour(p3_3);
        p3_4.addNeighbour(p3_5);
        p3_4.addNeighbour(p2_4);
        p3_4.addNeighbour(p2_1);
        p3_4.addNeighbour(p6_3);
        
        //Nachbarn des Knoten p3_5
        p3_5.addNeighbour(p3_4);
        p3_5.addNeighbour(p3_6);
        p3_5.addNeighbour(z);
        p3_5.addNeighbour(p2_4);
        
        //Nachbarn des Knoten p3_6
        p3_6.addNeighbour(p3_5);
        p3_6.addNeighbour(p3_1);
        p3_6.addNeighbour(p7_3);
        p3_6.addNeighbour(p5_2);
        
        
        //Nachbarn des Knoten p4_1
        p4_1.addNeighbour(p4_2);
        p4_1.addNeighbour(p4_5);
        p4_1.addNeighbour(s);
        
        //Nachbarn des Knoten p4_2
        p4_2.addNeighbour(p4_1);
        p4_2.addNeighbour(p4_3);
        p4_2.addNeighbour(p8_1);
        p4_2.addNeighbour(p1_2);
        p4_2.addNeighbour(p1_3);
        
        
        //Nachbarn des Knoten p4_3
        p4_3.addNeighbour(p4_2);
        p4_3.addNeighbour(p4_4);
        p4_3.addNeighbour(p8_1);
        p4_3.addNeighbour(p8_3);
        p4_3.addNeighbour(p1_2);
        
        //Nachbarn des Knoten p4_4
        p4_4.addNeighbour(p4_3);
        p4_4.addNeighbour(p4_5);
        p4_4.addNeighbour(p8_1);
        p4_4.addNeighbour(p8_3);
        p4_4.addNeighbour(p5_4);
        p4_4.addNeighbour(p5_3);
        
        //Nachbarn des Knoten p4_5
        p4_5.addNeighbour(p4_1);
        p4_5.addNeighbour(p4_4);
        p4_5.addNeighbour(s);
        p4_5.addNeighbour(p5_4);
        p4_5.addNeighbour(p5_1);
        
        
        //Nachbarn des Knoten p5_1
        p5_1.addNeighbour(p5_4);
        p5_1.addNeighbour(p5_2);
        p5_1.addNeighbour(s);
        p5_1.addNeighbour(p4_5);
        
        //Nachbarn des Knoten p5_2
        p5_2.addNeighbour(p5_1);
        p5_2.addNeighbour(p5_3);
        p5_2.addNeighbour(p7_3);
        p5_2.addNeighbour(p7_2);
        p5_2.addNeighbour(p7_1);
        p5_2.addNeighbour(p6_2);
        p5_2.addNeighbour(p3_1);
        p5_2.addNeighbour(p3_2);
        p5_2.addNeighbour(p3_6);
        
        //Nachbarn des Knoten p5_3
        p5_3.addNeighbour(p5_2);
        p5_3.addNeighbour(p5_4);
        p5_3.addNeighbour(p7_3);
        p5_3.addNeighbour(p7_1);
        p5_3.addNeighbour(p1_1);
        p5_3.addNeighbour(p8_1);
        p5_3.addNeighbour(p8_2);
        p5_3.addNeighbour(p8_3);
        p5_3.addNeighbour(p4_4);
        p5_3.addNeighbour(p4_5);
        
        //Nachbarn des Knoten p5_4
        p5_4.addNeighbour(p5_3);
        p5_4.addNeighbour(p5_1);
        p5_4.addNeighbour(s);
        p5_4.addNeighbour(p4_4);
        p5_4.addNeighbour(p4_5);
        p5_4.addNeighbour(p8_3);
        p5_4.addNeighbour(p8_2);
        
        //Nachbarn des Knoten p6_1
        p6_1.addNeighbour(p6_2);
        p6_1.addNeighbour(p6_4);
        p6_1.addNeighbour(p3_1);
        p6_1.addNeighbour(p3_2);
        p6_1.addNeighbour(p3_3);
        p6_1.addNeighbour(p7_1);
        p6_1.addNeighbour(p7_2);
        p6_1.addNeighbour(p1_1);
        p6_1.addNeighbour(p1_4);
        
        //Nachbarn des Knoten p6_2
        p6_2.addNeighbour(p6_1);
        p6_2.addNeighbour(p6_3);
        p6_2.addNeighbour(p3_1);
        p6_2.addNeighbour(p3_2);
        p6_2.addNeighbour(p2_4);
        p6_2.addNeighbour(p2_1);
        p6_2.addNeighbour(p7_2);
        p6_2.addNeighbour(p7_3);
        p6_2.addNeighbour(p5_2);
        
        //Nachbarn des Knoten p6_3
        p6_3.addNeighbour(p6_4);
        p6_3.addNeighbour(p6_2);
        p6_3.addNeighbour(p2_1);
        p6_3.addNeighbour(p2_2);
        p6_3.addNeighbour(p2_4);
        p6_3.addNeighbour(p3_3);
        p6_3.addNeighbour(p3_4);
        p6_3.addNeighbour(p1_3);
        
        //Nachbarn des Knoten p6_4
        p6_4.addNeighbour(p6_1);
        p6_4.addNeighbour(p6_3);
        p6_4.addNeighbour(p1_3);
        p6_4.addNeighbour(p1_4);
        p6_4.addNeighbour(p2_1);
        
        //Nachbarn des Knoten p7_1
        p7_1.addNeighbour(p7_2);
        p7_1.addNeighbour(p7_3);
        p7_1.addNeighbour(p5_2);
        p7_1.addNeighbour(p5_3);
        p7_1.addNeighbour(p8_2);
        p7_1.addNeighbour(p1_1);
        p7_1.addNeighbour(p1_2);
        p7_1.addNeighbour(p6_1);
        p7_1.addNeighbour(p3_2);
        
        //Nachbarn des Knoten p7_2
        p7_2.addNeighbour(p7_1);
        p7_2.addNeighbour(p7_3);
        p7_2.addNeighbour(p3_1);
        p7_2.addNeighbour(p3_2);
        p7_2.addNeighbour(p3_3);
        p7_2.addNeighbour(p6_1);
        p7_2.addNeighbour(p6_2);
        p7_2.addNeighbour(p5_2);
        
        //Nachbarn des Knoten p7_3
        p7_3.addNeighbour(p7_1);
        p7_3.addNeighbour(p7_2);
        p7_3.addNeighbour(p3_1);
        p7_3.addNeighbour(p3_2);
        p7_3.addNeighbour(p3_3);
        p7_3.addNeighbour(p3_6);
        p7_3.addNeighbour(p6_2);
        p7_3.addNeighbour(p5_2);
        p7_3.addNeighbour(p5_3);
        p7_3.addNeighbour(p1_1);
        p7_3.addNeighbour(p8_1);
        
        
        //Nachbarn des Knoten p8_1
        p8_1.addNeighbour(p8_2);
        p8_1.addNeighbour(p8_3);
        p8_1.addNeighbour(p1_1);
        p8_1.addNeighbour(p1_2);
        p8_1.addNeighbour(p4_2);
        p8_1.addNeighbour(p4_3);
        p8_1.addNeighbour(p4_4);
        p8_1.addNeighbour(p5_3);
        p8_1.addNeighbour(p7_3);
        
        //Nachbarn des Knoten p8_2
        p8_2.addNeighbour(p8_1);
        p8_2.addNeighbour(p8_3);
        p8_2.addNeighbour(p5_3);
        p8_2.addNeighbour(p5_4);
        p8_2.addNeighbour(p1_1);
        p8_2.addNeighbour(p1_2);
        p8_2.addNeighbour(p1_4);
        p8_2.addNeighbour(p7_1);
        
        //Nachbarn des Knoten p8_3
        p8_3.addNeighbour(p8_1);
        p8_3.addNeighbour(p8_2);
        p8_3.addNeighbour(p5_3);
        p8_3.addNeighbour(p5_4);
        p8_3.addNeighbour(p4_3);
        p8_3.addNeighbour(p4_4);
        
        //System.out.printf(AStarSearch.search(s, z).toString());
    }
}
