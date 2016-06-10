package com.argonnet.GraphRepresentation;

/**
 * Represent the View of the Vertex
 */
public class VertexView {

    //Vertex number in the matrix
    private int number;
    //Position X
    private double x;
    //Position Y
    private double y;

    /**
     * Main constructor
     * @param number number of the vertex in the graph.
     */
    public VertexView(int number){
        this.setNumber(number);
        this.setX(50);
        this.setY(50);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}
