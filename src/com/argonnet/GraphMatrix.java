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
