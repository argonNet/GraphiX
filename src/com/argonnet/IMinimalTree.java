package com.argonnet;

/**
 * Interface to implement for all minimal tree algorythm
 */
public interface IMinimalTree {

    /**
     * Calc a minimal tree (partial graph), from a node
     * @param graph Base graph to proceed
     * @param root Root vertex number to start the tree
     * @return Partial graph that represent the minimal tree
     */
    GraphMatrix CalcMinimalTree(Graph graph, int root);
}
