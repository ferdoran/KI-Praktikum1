package test;
import algorithms.agent.World;
import data.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Mahdi
 */
public class WorldTest {
    public static void main(String[] args){
        World world = new World();
        Point start = new Point(100,534, "S");
        world.setAgentPosition(start);
        
        System.out.print(world.getAvailablePoints().toString());
        
    }
    
}
