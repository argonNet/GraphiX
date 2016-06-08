package com.argonnet;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Put the Vertex on the plan and draw the Graph
 */
public class GraphDrawer {

    private final static int VERTEX_WIDTH = 50;
    private final static int VERTEX_HEIGHT = 50;

    public final static int VERTEX_RADIUS = VERTEX_WIDTH / 2;

    private final static int LABEL_WIDTH = 30;
    private final static int LABEL_HEIGHT = 30;


    private GraphicsContext gc;

    /**
     * Constructor of the class
     * @param gc Graphicscontext where the class must draw the graph
     */
    public GraphDrawer(GraphicsContext gc){
        this.gc = gc;

        //Define text default params for this graphics contexte
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }


    private double getRatioX(){
        return gc.getCanvas().getWidth() / Constants.GRAPH_REF_WIDTH;
    }

    private double getRatioY(){
        return gc.getCanvas().getHeight() / Constants.GRAPH_REF_HEIGHT;
    }

    private boolean isVertexSelected(GraphMatrix highlightPart, int vertexNumber){
        for(int i = 0; i < highlightPart.getVertexCount(); i++){
            if(highlightPart.getEdge(vertexNumber - 1,i) != 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Draw all the vertexes of the graph
     * @param graph graph to draw
     * @param highlightPart Partial graph to highlight
     */
    private void drawEdges(Graph graph, GraphMatrix highlightPart){

        for(int i = 0; i < graph.getVertexCount(); i++){
            for(int j = 0; j <= i; j++){

                if(graph.getEdge(i,j) > 0){

                    if(highlightPart != null && highlightPart.getEdge(i,j) != 0) {
                        gc.setStroke(Color.rgb(255,0,0));
                    }else{
                        gc.setStroke(Color.rgb(0,0,0));
                    }
                    
                    //Draw the line
                    gc.strokeLine(
                            graph.getVertexView(i).getX() * getRatioX(),
                            graph.getVertexView(i).getY() * getRatioY(),
                            graph.getVertexView(j).getX() * getRatioX(),
                            graph.getVertexView(j).getY() * getRatioY());

                    //Draw the weight of the edge
                    double vectX = graph.getVertexView(j).getX() - graph.getVertexView(i).getX();
                    double vectY = graph.getVertexView(j).getY() - graph.getVertexView(i).getY();

                    //Position of the label
                    double edgeLabelPosX = (graph.getVertexView(i).getX() + (vectX / 3)) * getRatioX();
                    double edgeLabelPosY = (graph.getVertexView(i).getY() + (vectY / 3)) * getRatioY();

                    // To get the middle of the edges
                    //(graph.getVertexView(j).getX() + graph.getVertexView(i).getX()) * getRatioX() / 2;
                    //(graph.getVertexView(j).getY() + graph.getVertexView(i).getY()) * getRatioY() / 2;

                    //Background ...
                    gc.setFill(Color.rgb(255,255,255,1));
                    gc.fillOval(
                            edgeLabelPosX - (LABEL_WIDTH / 2),
                            edgeLabelPosY - (LABEL_HEIGHT / 2),
                            LABEL_WIDTH,
                            LABEL_HEIGHT);

                    //Text...
                    gc.setFill(Color.rgb(0,0,0,1));
                    gc.fillText(
                            String.valueOf(graph.getEdge(i,j)),
                            edgeLabelPosX,
                            edgeLabelPosY) ;
                }

            }
        }
    }

    /**
     * Draw all the vertexes of the graph
     * @param graph graph to draw
     */
    private void drawVertexes(Graph graph, GraphMatrix highlightPart){

        gc.setFont(new Font(gc.getFont().getFamily(), 20));

        for (int i = 0; i < graph.getVertexCount(); i++){

            //Vertex draw...

            //Select color if the vertex if selected
            if(highlightPart != null && isVertexSelected(highlightPart,graph.getVertexView(i).getNumber())) {
                gc.setFill(Color.rgb(255, 0, 0, 1));
            }else{
                gc.setFill(Color.rgb(0, 0, 255, 1));
            }

            gc.fillOval(
                    graph.getVertexView(i).getX() * getRatioX() - (VERTEX_WIDTH / 2),
                    graph.getVertexView(i).getY() * getRatioY() - (VERTEX_HEIGHT / 2),
                    VERTEX_WIDTH,
                    VERTEX_HEIGHT);

            //Text number draw...
            gc.setFill(Color.rgb(255,255,255,1));
            gc.fillText(
                    String.valueOf(graph.getVertexView(i).getNumber()),
                    graph.getVertexView(i).getX() * getRatioX(),
                    graph.getVertexView(i).getY() * getRatioY());
        }

    }

    /**
     * Draw the graph in the GraphicsContext set to constructor
     * @param graph Graph to draw
     */
    public void drawGraph(Graph graph){
        drawGraph(graph,null);
    }

    /**
     * Draw the graph in the GraphicsContext set to constructor
     * @param graph Graph to draw
     * @param highlightPart Partial graph to highlight
     */
    public void drawGraph(Graph graph, GraphMatrix highlightPart){
        gc.clearRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());

        //Drawing the Edges first to avoid the black line in the circle
        drawEdges(graph,highlightPart);
        drawVertexes(graph,highlightPart);
    }

}
