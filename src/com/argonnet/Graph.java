package com.argonnet;

import javafx.collections.FXCollections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Class for graph representation (visual and logic)
 */
public class Graph {

    private GraphMatrix matrix;
    private ArrayList<Edge> edges;
    private ArrayList<VertexView> vertexViews;

    /**
     * Generation of the list of edges
     */
    private void generateOrderedEdgeList(){

        edges = new ArrayList<Edge>();

        for(int i = 0; i < matrix.getVertexCount(); i++) {
            for (int j = 0; j <= i; j++) {
                if(matrix.getEdge(i,j) != 0){
                    edges.add(new Edge(i,j,matrix.getEdge(i,j)));
                }

            }
        }
    }

    /**
     * Constructor with the number of vertexes in the graph.
     * @param nbVertex Total count of Vertexes in the graph.
     */
    public Graph(int nbVertex){

        this.matrix = new GraphMatrix(nbVertex);
        this.vertexViews = new ArrayList<VertexView>();

        //Creation of all the VertexView
        for(int i = 0; i< nbVertex;i++){
            this.vertexViews.add(new VertexView(i + 1));
        }
    }

    /**
     * Automatic and random fill of the matrix.
     * @param maxDistance Max distance between two vertexs (minimum = 0)
     */
    public void generateRandomGraph(int maxDistance){
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < matrix.getVertexCount(); i++){
            for(int j = 0; j <= i; j++){
                if(i != j){
                    int val = random.nextInt(maxDistance);
                    matrix.setEdge(i,j,val);
                    matrix.setEdge(j,i,val);
                }else{
                    matrix.setEdge(i,j,0);
                }
            }
        }

        generateOrderedEdgeList();
    }



    public int getEdge(int x, int y){
        return this.matrix.getEdge(x,y);
    }

    public int getVertexCount() {
        return this.matrix.getVertexCount();
    }

    public VertexView getVertexView(int vertexNumber){
        return this.vertexViews.get(vertexNumber);
    }

    public GraphMatrix getMatrix(){
        return this.matrix;
    }

    /**
     * Retrun the order list of edges (form min to max)
     * @return Arraylist of sorted edges (min to max)
     */
    public ArrayList<Edge> getOrderedEdgeMinToMax(){
        Collections.sort(edges, (e1, e2) -> e1.getWeight() - e2.getWeight());
        return edges;
    }

}
