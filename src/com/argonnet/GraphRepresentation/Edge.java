package com.argonnet.GraphRepresentation;

/**
 * Representation on an edge
 * Added the Comparable interface so that's the Edge object can be compared
 */
public class Edge implements Comparable   {

    private int from;
    private int to;
    private int weight;

    public Edge(int from, int to, int weight){
        setFrom(from);
        setTo(to);
        setWeight(weight);
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Compare the weight between two edges
     * @param o the Edge to compare
     * @return -1 if the current edge's weight is less than the other, if  greater = 1, if equals = 0
     */
    @Override
    public int compareTo(Object o) {
        Edge otherEdge = (Edge)o;
        if (this.weight < otherEdge.weight)
            return -1;
        if (this.weight > otherEdge.weight)
            return 1;
        return 0;
    }
}
