package com.argonnet;

/**
 * Base class to represente a Graph as a matrix
 */
public class GraphMatrix {

    private int matrix[][];
    private int vertexCount;

    /**
     * Constructor of the Matrix
     * @param nbVertex vertex count in the Graph
     */
    public GraphMatrix(int nbVertex){
        this.vertexCount = nbVertex;
        this.setMatrix(new int[nbVertex][nbVertex]);
    }


    /**
     *
     * @param explorationStatus
     * @param vertex
     * @return return true if we find a cycle
     */
    private boolean exploreVertex(boolean explorationStatus[], int vertex){

        explorationStatus[vertex] = true;

        //We have an no directed graph then we need only the half of the matrix
        for(int i = vertex + 1; i < vertexCount; i++){

            if( getMatrix()[vertex][i] != 0){
                if(!explorationStatus[i]) {
                    exploreVertex(explorationStatus, i);
                }else {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check if the graph contain at least ont cycle, it use a DFS algorythm
     * @return True if the graph contain cycle
     */
    public boolean containCycle(){
        boolean explorationStatus[] = new boolean[vertexCount];

        //Initialize the exploration status as not explored (false)
        for(int i = 0; i < vertexCount; i++) explorationStatus[i] = false;

        return exploreVertex(explorationStatus,0);
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getEdge(int x, int y){
        return this.matrix[x][y];
    }

    public  void setEdge(int x, int y, int edgeWeight){
        this.matrix[x][y] = edgeWeight;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }
}
