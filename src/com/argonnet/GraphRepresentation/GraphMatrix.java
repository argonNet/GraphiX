package com.argonnet.GraphRepresentation;

import java.util.Arrays;

/**
 * Base class to represente a Graph as a matrix
 */
public class GraphMatrix {

    private int matrix[][];

    /**
     * Constructor of the Matrix
     * @param nbVertex vertex count in the Graph
     */
    public GraphMatrix(int nbVertex){
        this.setMatrix(new int[nbVertex][nbVertex]);
    }

    /**
     * Method that use a DFS path to check if there is an existing cycle
     * @param explorationStatus table with the exploration status of alle the vertexes
     * @param vertex Vertex to explore
     * @return Return true if we find a cycle
     */
    private boolean dfsPathForCycleDetection(boolean explorationStatus[], int vertex){
        boolean result = false;
        explorationStatus[vertex] = true;

        //We have an no directed graph then we need only the half of the matrix
        for(int i = vertex + 1; i < this.getVertexCount() && !result; i++){

            if( getMatrix()[vertex][i] != 0){ //Check the existence of an edge
                if(!explorationStatus[i]) {
                    result = dfsPathForCycleDetection(explorationStatus, i);
                }else {
                    return true; //Cycle found we stop to search
                }
            }
        }

        //If we get there there isn't any cycle
        return result;
    }

    /**
     * Check if the graph contain at least ont cycle, it use a DFS algorythm
     * @return True if the graph contain cycle
     */
    public boolean containCycle(){
        boolean explorationStatus[] = new boolean[this.getVertexCount()];

        //Initialize the exploration status as not explored (false)
        for(int i = 0; i < this.getVertexCount(); i++) explorationStatus[i] = false;

        return dfsPathForCycleDetection(explorationStatus,0);
    }

    /**
     * Adding a vertex in the graph.
     */
    public void addVertex(){

        //First we add a cell to each existing line...
        for(int i = 0; i < this.getVertexCount();i++) {
            matrix[i] = Arrays.copyOf(matrix[i], this.getVertexCount() + 1);
            matrix[i][this.getVertexCount()] = 0;
        }

        //Then we add a new line (our matrix should always be a square
        matrix = Arrays.copyOf(matrix, this.getVertexCount() + 1);
        matrix[this.getVertexCount() - 1] = new int[this.getVertexCount()];
        for(int i = 0; i < this.getVertexCount();i++) {
            matrix[this.getVertexCount() - 1][i] = 0;
        }

    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getEdge(int from, int to){
        return this.matrix[from][to];
    }

    public  void setEdge(int from, int to, int edgeWeight){
        this.matrix[from][to] = edgeWeight;
        this.matrix[to][from] = edgeWeight;
    }

	/**
	 * Return the number of vertex in the current matrix
	 */
    public int getVertexCount() {
		//As we always have a square matrix, we only have to check the first dim.
        return matrix.length;
    }

}
