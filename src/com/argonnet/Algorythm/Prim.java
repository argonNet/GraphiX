package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Edge;
import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;
import com.argonnet.GraphRepresentation.VertexView;


import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Aoi on 13/06/2016.
 */
public class Prim implements IMinimalTree {
    /**
     * Calc a minimal tree (partial graph), from a node
     *
     * @param graph Base graph to proceed
     * @param root  Root vertex number to start the tree
     * @return Partial graph that represent the minimal tree
     */
    @Override
    public GraphMatrix CalcMinimalTree(Graph graph, int root) {
        GraphMatrix resultObj = new GraphMatrix(graph.getVertexCount());
        int cost = 0;
        //Step 1: Choose one Vertex and begin to make a tree T with only this one
        //Init unvisited Vertex list, remove the starting vertex
        PriorityQueue<Edge> edgeAvailables = new PriorityQueue<>();
        ArrayList<VertexView> vertexUnvisited =  new ArrayList<VertexView>(graph.getVertexViewList());
        VertexView currentVertex = graph.getVertexView(root);
        vertexUnvisited.remove(root);

        //Step 2: For each iteration, Add to tree T the edge with the most minimal weight
        // bound from a vertex of T to a vertex not yet in T
        while (!vertexUnvisited.isEmpty()) {
            for (int i = 0; i < graph.getVertexCount(); i++) { //!!C'est faux de faire ça !!!
                int to = graph.getMatrix().getMatrix()[currentVertex.getNumber() - 1][i];
                Edge edge = new Edge(currentVertex.getNumber() -1, i, graph.getMatrix().getEdge(currentVertex.getNumber() - 1, i));
                if (vertexUnvisited.contains(graph.getVertexView(i)) && edge.getWeight() > 0)
                edgeAvailables.add(edge);
        }
        Edge e = edgeAvailables.remove();
        cost += e.getWeight();
        resultObj.setEdge(e.getFrom(), e.getTo(), e.getWeight());

        currentVertex = graph.getVertexView(e.getTo());
        vertexUnvisited.remove(currentVertex);
        }
        //Step 3: Stop when the tree T has ALL vertex
        return resultObj;
    }
}
