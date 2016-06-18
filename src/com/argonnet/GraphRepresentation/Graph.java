package com.argonnet.GraphRepresentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Class for graph representation (visual and logic)
 */
public class Graph {

    private GraphMatrix matrix;
    private ArrayList<Edge> edges;
    private ArrayList<VertexView> vertexViews;

    private final Collection<GraphChangeListener> graphChangeListeners = new ArrayList<GraphChangeListener>();

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
     * Generation of the list of edges (no-oriented edges)
     */
    private void generateOrderedEdgeList(){

        edges = new ArrayList<Edge>();

        for(int i = 0; i < matrix.getVertexCount(); i++) {
            for (int j = 0; j <= i; j++) { //We take only the half part of the edges
                if(matrix.getEdge(i,j) != 0){
                    edges.add(new Edge(i,j,matrix.getEdge(i,j)));
                }
            }
        }
    }

    /**
     * Launch graph change event
     */
    private void fireGraphChanged(){
        for (GraphChangeListener listner :graphChangeListeners) {
            listner.graphChange();
        }
    }

    /**
     * Add a new vertex to the current graph
     */
    public void addVertex(){
        matrix.addVertex();
        this.vertexViews.add(new VertexView(matrix.getVertexCount()));
        fireGraphChanged();
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

    /**
     * Define an edge in the graph
     * @param from vertex start
     * @param to vertex end
     * @param weight weight of the edge
     */
    public void setEdge(int from, int to, int weight){
        this.matrix.setEdge(from,to,weight);

        boolean edgeAlreadyInList = false;
        //Searching the edge in the list and update it if needed
        for (Edge edge : edges) {
            if(edge.getFrom() == from && edge.getTo() == to){
                edge.setWeight(weight);
                edgeAlreadyInList = true;
                break;
            }
        }

        //If edge is not in the list we create it
        if(!edgeAlreadyInList){
            edges.add(new Edge(from,to,weight));
        }

        fireGraphChanged();
    }

    public int getEdge(int from, int to){
        return this.matrix.getEdge(from,to);
    }

    /**
     * Get the list of vertexes of the graph
     * @return List of vertexes (VertexView)
     */
    public ArrayList<VertexView> getVertexViewList(){
        return this.vertexViews;
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


    public void addGraphChangeListener(GraphChangeListener listener) {
        graphChangeListeners.add(listener);
    }

    public void removeGraphChangeListener(GraphChangeListener listener) {
        graphChangeListeners.remove(listener);
    }

    public GraphChangeListener[] getGraphChangeListeners() {
        return graphChangeListeners.toArray(new GraphChangeListener[0]);
    }



}
