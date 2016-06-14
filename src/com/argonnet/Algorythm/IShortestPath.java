package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;

/**
 * Interface that all shortest path algorithm have to implement
 */
public interface IShortestPath {

    /**
     * Calc a shortest path (partial graph), from a node to another one
     * @param graph Base graph to proceed
     * @param from The vertex where the algorithm should start
	 * @param to The vertex where that we want reach
     * @return Partial graph that represent the shortest path
     */
    GraphMatrix CalcShortestPath(Graph graph, int from, int to);
}
