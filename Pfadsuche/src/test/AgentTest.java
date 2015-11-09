package test;

import algorithms.agent.Agent;
import algorithms.agent.World;

/**
 *
 * @author Roland
 */
public class AgentTest {
    
    public static void main(String[] args) {
        World w = new World();
        Agent a = new Agent(w);
        int[] cost = a.search();
        double sum = 0;
        for(int i = 0;i < 100; i++) {
            sum += cost[i];
            System.out.println("Kosten für die " + i +". Suche: " + cost[i]);
        }
        System.out.println("Die durchschnittlichen Kosten betragen: " + sum/100);
    }
    
    
}
