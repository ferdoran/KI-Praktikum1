package test;
import algorithms.agent.World;
import data.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class WorldTest {
    public static void main(String[] args){
        World world = new World();
        ArrayList<Point2D.Double> liste = new ArrayList<>();
        int used = 0;
        for(int i=0;i<10000;i++) {
            Point2D.Double p = world.calcStartposition();
            if(!liste.contains(p)) {
                liste.add(p);
                System.out.println(p);
            }
            else {
                used++;
            }
            
        }
        System.out.println("Anzahl an bereits verwendeten Punkten: " + used);
//        world.setAgentPosition(start);
//        
//        System.out.print(world.getAvailablePoints().toString());
        
    }
    
}
