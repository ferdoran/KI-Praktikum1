package test;
import algorithms.agent.World;
import data.*;

/**
 *
 * @author Mahdi
 */
public class WorldTest {
    public static void main(String[] args){
        World world = new World();
        world.setAgentPosition(new Point(100,534, "S"));
        
        System.out.print(world.getAvailablePoints().toString());
        
    }
    
}
