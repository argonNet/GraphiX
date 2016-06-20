package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;

/**
 * Interface to implement for all minimal tree algorythm
 */
public interface IMinimalTree {

    /**
     * Allow to get the cost of the calculated minimal tree
     * @return
     */
    int getCost();

    /**
     * Calc a minimal tree (partial graph), from a node
     * @param graph Base graph to proceed
     * @param root Root vertex number to start the tree
     * @return Partial graph that represent the minimal tree
     */
    GraphMatrix CalcMinimalTree(Graph graph, int root);
}
