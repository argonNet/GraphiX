package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Edge;
import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;
import com.argonnet.GraphRepresentation.VertexView;


import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Prim algorithm implementation
 * Created by Aoi on 13/06/2016.
 */
public class Prim implements IMinimalTree {


    private int cost = 0;

    /**
     * Allow to get the cost of the calculated minimal tree
     * @return
     */
    public int getCost(){
        return cost;
    }

    /**
     * Prim method to get a minimal tree
     * NOTE: The graph must be UNDIRECTED in order to work
     * TODO: Eventually return the cost of the minimal tree
     *
     * @param graph Base graph to proceed
     * @param root  Root vertex number to start the tree
     * @return Partial graph that represent the minimal tree
     */
    public GraphMatrix CalcMinimalTree(Graph graph, int root) {
        GraphMatrix resultObj = new GraphMatrix(graph.getVertexCount());
        cost = 0;
        //Step 1: Choose one Vertex and begin to make a tree T with only this one
        //Init unvisited Vertex list, remove the starting vertex
        PriorityQueue<Edge> edgeAvailables = new PriorityQueue<>();
        ArrayList<VertexView> vertexUnvisited = new ArrayList<VertexView>(graph.getVertexViewList());
        VertexView currentVertex = graph.getVertexView(root);
        vertexUnvisited.remove(root);

        //Step 2: For each iteration, Add to tree T the edge with the most minimal weight
        while (!vertexUnvisited.isEmpty()) {
            for (int i = 0; i < graph.getVertexCount(); i++) {
                Edge edge = new Edge(currentVertex.getNumber() - 1, i, graph.getMatrix().getEdge(currentVertex.getNumber() - 1, i));
                //Add the edge only if the To vertex is not visited and the weight is not 0 (= no edge between the vertexes)
                if (vertexUnvisited.contains(graph.getVertexView(edge.getTo())) && edge.getWeight() > 0) {
                    edgeAvailables.add(edge);
                }
            }
            //Get the edge with the least weight (thanks for the PriorityQueue to order automatically)
            Edge e = edgeAvailables.remove();
            //Make a last check, if the To vertex of the current edge is already visited, we do not need this edge anymore.
            // Take the next one
            while (!vertexUnvisited.contains(graph.getVertexView(e.getTo()))) {
                if (!edgeAvailables.isEmpty()) {
                    e = edgeAvailables.remove();
                } else {
                    break; //It should normally not happen, prevent the application to crash
                }
            }
            cost += e.getWeight();
            //Set the edge to the result
            resultObj.setEdge(e.getFrom(), e.getTo(), e.getWeight());

            //Prepare for next loop
            currentVertex = graph.getVertexView(e.getTo());
            vertexUnvisited.remove(currentVertex);
        }
        //Step 3: Stop when the tree T has ALL vertex
        return resultObj;
    }
}
