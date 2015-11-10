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
        world.setAgentPosition(world.calcStartposition());
        System.out.println(world.getAvPoints());
//        world.setAgentPosition(start);
//        
//        System.out.print(world.getAvailablePoints().toString());
        
    }
    
}
