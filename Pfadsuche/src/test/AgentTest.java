/*
 * TH Koeln Informatik BA
 * 5. Semester KI Praktikum
 * Praktikum 1, Aufgabe 1
 * Roland Mueller & Stephan Schneider
 */
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
        double cost = a.search();
        System.out.println("Die Kosten betragen: " + cost);
    }
    
    
}
