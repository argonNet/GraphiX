package com.argonnet;

import java.util.ArrayList;

/**
 * Kruskal algorithm implementation
 */
public class Kruskal implements IMinimalTree {

    //private int[] ;

    /**
     * Kruskal method to get a minimal tree, root is not consider.
     * @param graph Base graph to proceed
     * @param Root
     * @return
     */
    @Override
    public GraphMatrix CalcMinimalTree(Graph graph, int Root) {
        GraphMatrix resultObj = new GraphMatrix(graph.getVertexCount());
        int addedEdges = 0;

        //Step 1 : Order edges by weight
        ArrayList<Edge> edges = graph.getOrderedEdgeMinToMax();

        for(int i = 0; i < edges.size() && addedEdges < graph.getVertexCount() - 1; i++){
            resultObj.setEdge(edges.get(i).getFrom(),edges.get(i).getTo(),edges.get(i).getWeight());
            resultObj.setEdge(edges.get(i).getTo(),edges.get(i).getFrom(),edges.get(i).getWeight());
            addedEdges++;

            if(resultObj.containCycle()){
                resultObj.setEdge(edges.get(i).getFrom(),edges.get(i).getTo(),0);
                resultObj.setEdge(edges.get(i).getTo(),edges.get(i).getFrom(),0);
                addedEdges--;
            }
        }

        return resultObj;
    }

}
