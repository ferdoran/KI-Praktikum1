package algorithms.astar;

import java.util.Comparator;

/**
 *
 * @author Mahdi
 * 
 * Dieser Comparator dient dazu die PriorityQueue entsprechend des geschätzten 
 * kürzesten Weges zu sortieren.
 */
public class AStarNodeComparator implements Comparator<AStarNode> {

    @Override
    public int compare(AStarNode first, AStarNode second) {
        if(first.getF() < second.getF()){
            return -1;
        }else if(first.getF() > second.getF()){
            return 1;
        }else{
            return 0;
        }
    }
}
