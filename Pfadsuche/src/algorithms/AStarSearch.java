package algorithms;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Mahdi
 */
public class AStarSearch {

   public static List<String> search(NavNode source, NavNode target) {
        Map<String, AStarNode> offeneKnoten = new HashMap<>();
        PriorityQueue<AStarNode> grenzListe = new PriorityQueue(40, new AStarNodeComparator());
        Map<String, AStarNode> geschlosseneKnoten = new HashMap<>();
        AStarNode start = new AStarNode(source, 0, calcDistance(source, target));
        offeneKnoten.put(source.getId(), start);
        grenzListe.add(start);

        AStarNode goal = null;
        while(offeneKnoten.size() > 0){
            AStarNode x = grenzListe.poll();
            offeneKnoten.remove(x.getId());
            if(x.getId().equals(target.getId())){
                //found
                goal = x;
                break;
            }else{                
                geschlosseneKnoten.put(x.getId(), x);
                Set<NavNode> neighbors = x.getNode().getNeighbours();
                neighbors.stream().forEach((neighbor) -> {
                    AStarNode visited = geschlosseneKnoten.get(neighbor.getId());
                    if (visited == null) {
                        double g = x.getG() + calcDistance(x.getNode(), neighbor);
                        AStarNode n = offeneKnoten.get(neighbor.getId());

                        if (n == null) {
                            //not in the open set
                            n = new AStarNode(neighbor, g, calcDistance(neighbor, target));
                            n.setCameFrom(x);
                            offeneKnoten.put(neighbor.getId(), n);
                            grenzListe.add(n);
                        } else if (g < n.getG()) {
                            //Have a better route to the current node, change its parent
                            n.setCameFrom(x);
                            n.setG(g);
                            n.setH(calcDistance(neighbor, target));
                        }
                    }
                });
            }
        }

        //after found the target, start to construct the path 
        if(goal != null){
            Stack<NavNode> stack = new Stack<>();
            List<NavNode> list = new ArrayList<>();
            stack.push(goal.getNode());
            AStarNode parent = goal.getCameFrom();
            while(parent != null){
                stack.push(parent.getNode());
                parent = parent.getCameFrom();
            }
            while(stack.size() > 0){
                list.add(stack.pop());
            }
            
            List<String> idlist = new ArrayList<>();
            for (NavNode list1 : list) {
                idlist.add(list1.getId());
            }
            return idlist;
        }
        
        return null;  
    }

    private static double calcDistance(NavNode source, NavNode target) {
        return sqrt(power(((source.getX()) - (target.getX())),2) + (power(((target.getY()) - (source.getY())),2)));
    }

}
