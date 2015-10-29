package test;
import algorithms.*;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
/**
 *
 * @author Mahdi
 * Diese Klasse dient dem Test des AStar-Algorithmus welcher im Package
 * 'algorithms'.
 */
public class AStarTest {
    
    public static void main(){
        
        //Anlegen der Nodes aus der Aufgabe als NavNodes
        NavNode s = new NavNode(100,534,"Start");
        NavNode z = new NavNode(388,663,"Ziel");
        
        //Polygon 1
        NavNode p1_1 = new NavNode(220,616,"P1_1");
        NavNode p1_2 = new NavNode(220,666,"P1_2");
        NavNode p1_3 = new NavNode(251,670,"P1_3");
        NavNode p1_4 = new NavNode(272,647,"P1_4");
        
        //Polygon 2
        NavNode p2_1 = new NavNode(341,655,"P2_1");
        NavNode p2_2 = new NavNode(359,667,"P2_2");
        NavNode p2_3 = new NavNode(374,651,"P2_3");
        NavNode p2_4 = new NavNode(366,577,"P2_4");
        
        //Polygon 3
        NavNode p3_1 = new NavNode(311,530,"P3_1");
        NavNode p3_2 = new NavNode(311,559,"P3_2");
        NavNode p3_3 = new NavNode(339,578,"P3_3");
        NavNode p3_4 = new NavNode(361,560,"P3_4");
        NavNode p3_5 = new NavNode(361,528,"P3_5");
        NavNode p3_6 = new NavNode(336,516,"P3_6");
        
        //Polygon 4
        NavNode p4_1 = new NavNode(105,628,"P4_1");
        NavNode p4_2 = new NavNode(151,670,"P4_2");
        NavNode p4_3 = new NavNode(180,629,"P4_3");
        NavNode p4_4 = new NavNode(156,577,"P4_4");
        NavNode p4_5 = new NavNode(113,587,"P4_5");
        
        //Polygon 5
        NavNode p5_1 = new NavNode(118,517,"P5_1");
        NavNode p5_2 = new NavNode(245,517,"P5_2");
        NavNode p5_3 = new NavNode(245,557,"P5_3");
        NavNode p5_4 = new NavNode(118,557,"P5_4");
        
        //Polygon 6
        NavNode p6_1 = new NavNode(280,583,"P6_1");
        NavNode p6_2 = new NavNode(333,583,"P6_2");
        NavNode p6_3 = new NavNode(333,665,"P6_3");
        NavNode p6_4 = new NavNode(280,665,"P6_4");
        
        //Polygon 7
        NavNode p7_1 = new NavNode(252,594,"P7_1");
        NavNode p7_2 = new NavNode(290,562,"P7_2");
        NavNode p7_3 = new NavNode(264,538,"P7_3");
        
        //Polygon 8
        NavNode p8_1 = new NavNode(198,635,"P8_1");
        NavNode p8_2 = new NavNode(217,574,"P8_2");
        NavNode p8_3 = new NavNode(182,574,"P8_3");
    
    
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
        //p1_1.addNeighbour(z);
        sqrt(power(p5_4.getX() - p5_3.getX(),2) + (power(p5_4.getY() - p5_3.getY(),2)));
    
    }
}
