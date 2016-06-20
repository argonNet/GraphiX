package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Edge;
import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;

import java.util.ArrayList;

/**
 * Kruskal algorithm implementation
 */
public class Kruskal implements IMinimalTree {

    private int cost = 0;

    /**
     * Allow to get the cost of the calculated minimal tree
     * @return
     */
    public int getCost(){
        return cost;
    }

    /**
     * Kruskal method to get a minimal tree, root is not consider.
     * @param graph Base graph to proceed
     * @param Root this parameter is not used. Kruskal does not allow to choose the starting point of the tree.
     * @return A Partial graph that is a minimal Tree
     */
    public GraphMatrix CalcMinimalTree(Graph graph, int Root) {
        GraphMatrix resultObj = new GraphMatrix(graph.getVertexCount());
        int addedEdges = 0;
        cost = 0;

        //Step 1 : Order edges by weight
        ArrayList<Edge> edges = graph.getOrderedEdgeMinToMax();

        //Step 2 : Trying to add each edge, and removing it if we found a cycle
        //We step when the number of edge added is equal to vertexCount - 1
        for(int i = 0; i < edges.size() && addedEdges < graph.getVertexCount() - 1; i++){
            resultObj.setEdge(edges.get(i).getFrom(),edges.get(i).getTo(),edges.get(i).getWeight());
            //resultObj.setEdge(edges.get(i).getTo(),edges.get(i).getFrom(),edges.get(i).getWeight());
            addedEdges++;
            cost += edges.get(i).getWeight();

            //If we found a cycle we have to remove the edge
            if(resultObj.containCycle()){
                resultObj.setEdge(edges.get(i).getFrom(),edges.get(i).getTo(),0);

                //resultObj.setEdge(edges.get(i).getTo(),edges.get(i).getFrom(),0);
                addedEdges--;
                cost -= edges.get(i).getWeight();
            }
        }

        return resultObj;
    }

}
