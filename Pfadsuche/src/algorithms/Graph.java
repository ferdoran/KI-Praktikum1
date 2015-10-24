/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class Graph<N extends Node, E extends Edge> {

    protected List<N> nodeList;
    
    protected List<E> edgeList;
    
    //Index for fast access
    private Map<String, Adjacency<N>> adjacency;

    //directed graph or not
    protected boolean diGraph;
}
