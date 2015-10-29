package algorithms;

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

   public static List<NavNode> search(NavGraph graph, NavNode source, NavNode target) {
        Map<String, AStarNode> openSet = new HashMap<>();
        PriorityQueue<AStarNode> pQueue = new PriorityQueue(40, new AStarNodeComparator());
        Map<String, AStarNode> closeSet = new HashMap<>();
        AStarNode start = new AStarNode(source, 0, graph.calcDistance(source, target));
        openSet.put(source.getId(), start);
        pQueue.add(start);

        AStarNode goal = null;
        while(openSet.size() > 0){
            AStarNode x = pQueue.poll();
            openSet.remove(x.getId());
            if(x.getId().equals(target.getId())){
                //found
                goal = x;
                break;
            }else{                
                closeSet.put(x.getId(), x);
                Set<NavNode> neighbors = graph.getAdjacentNodes(x.getId());
                neighbors.stream().forEach((neighbor) -> {
                    AStarNode visited = closeSet.get(neighbor.getId());
                    if (visited == null) {
                        double g = x.getG() + graph.calcDistance(x.getNode(), neighbor);
                        AStarNode n = openSet.get(neighbor.getId());

                        if (n == null) {
                            //not in the open set
                            n = new AStarNode(neighbor, g, graph.calcDistance(neighbor, target));
                            n.setCameFrom(x);
                            openSet.put(neighbor.getId(), n);
                            pQueue.add(n);
                        } else if (g < n.getG()) {
                            //Have a better route to the current node, change its parent
                            n.setCameFrom(x);
                            n.setG(g);
                            n.setH(graph.calcDistance(neighbor, target));
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
            return list;
        }
        
        return null;  
    }

}
