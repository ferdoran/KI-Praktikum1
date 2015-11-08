package test;
import algorithms.agent.World;
import java.awt.geom.Point2D;

/**
 *
 * @author Mahdi
 */
public class WorldTest {
    public static void main(String[] args){
        World world = new World();
        Point2D.Double start = new Point2D.Double(100,534);
        world.setAgentPosition(start);
        
        System.out.print(world.getAvailablePoints().toString());
        
    }
    
}
