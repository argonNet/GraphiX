package com.argonnet.Draw;

import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;
import com.argonnet.Utils.Constants;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Put the Vertex on the plan and draw the Graph
 */
public class GraphDrawer {

    private final static int VERTEX_WIDTH = 50;
    private final static int VERTEX_HEIGHT = 50;

    public final static int VERTEX_RADIUS = VERTEX_WIDTH / 2;

    private final static int LABEL_WIDTH = 30;
    private final static int LABEL_HEIGHT = 30;



    private Canvas canvas;
    private GraphicsContext gc;

    private GraphMotionManager motionManager;

    private Graph currentGraph;
    private GraphMatrix highlightedGraph;

    /**
     * Constructor of the class
     * @param canvas Canvas that will be mange by the graph drawer
     */
    public GraphDrawer(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        motionManager = new GraphMotionManager(this);

        //Define text default params for this graphics context
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }


    public double getRatioX(){
        return gc.getCanvas().getWidth() / Constants.GRAPH_REF_WIDTH;
    }

    public double getRatioY(){
        return gc.getCanvas().getHeight() / Constants.GRAPH_REF_HEIGHT;
    }

    private boolean isVertexHighlighted(GraphMatrix highlightPart, int vertexNumber){
        for(int i = 0; i < highlightPart.getVertexCount(); i++){
            if(highlightPart.getEdge(vertexNumber - 1,i) != 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Draw all the vertexes of the graph
     */
    private void drawEdges(){

        for(int i = 0; i < currentGraph.getVertexCount(); i++){
            for(int j = 0; j <= i; j++){

                if(currentGraph.getEdge(i,j) > 0){

                    if(highlightedGraph != null && highlightedGraph.getEdge(i,j) != 0) {
                        gc.setStroke(Color.rgb(255,0,0));
                    }else{
                        gc.setStroke(Color.rgb(0,0,0));
                    }
                    
                    //Draw the line
                    gc.strokeLine(
                            currentGraph.getVertexView(i).getX() * getRatioX(),
                            currentGraph.getVertexView(i).getY() * getRatioY(),
                            currentGraph.getVertexView(j).getX() * getRatioX(),
                            currentGraph.getVertexView(j).getY() * getRatioY());

                    //Draw the weight of the edge
                    double vectX = currentGraph.getVertexView(j).getX() - currentGraph.getVertexView(i).getX();
                    double vectY = currentGraph.getVertexView(j).getY() - currentGraph.getVertexView(i).getY();

                    //Position of the label
                    double edgeLabelPosX = (currentGraph.getVertexView(i).getX() + (vectX / 3)) * getRatioX();
                    double edgeLabelPosY = (currentGraph.getVertexView(i).getY() + (vectY / 3)) * getRatioY();

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
                            String.valueOf(currentGraph.getEdge(i,j)),
                            edgeLabelPosX,
                            edgeLabelPosY) ;
                }

            }
        }
    }

    /**
     * Draw all the vertexes of the graph
     */
    private void drawVertexes(){

        gc.setFont(new Font(gc.getFont().getFamily(), 20));

        for (int i = 0; i < currentGraph.getVertexCount(); i++){

            //Vertex draw...

            //Select color if the vertex if selected
            if(highlightedGraph != null && isVertexHighlighted(highlightedGraph,currentGraph.getVertexView(i).getNumber())) {
                gc.setFill(Color.rgb(255, 0, 0, 1));
            }else{
                gc.setFill(Color.rgb(0, 0, 255, 1));
            }

            gc.fillOval(
                    currentGraph.getVertexView(i).getX() * getRatioX() - (VERTEX_WIDTH / 2),
                    currentGraph.getVertexView(i).getY() * getRatioY() - (VERTEX_HEIGHT / 2),
                    VERTEX_WIDTH,
                    VERTEX_HEIGHT);

            //Text number draw...
            gc.setFill(Color.rgb(255,255,255,1));
            gc.fillText(
                    String.valueOf(currentGraph.getVertexView(i).getNumber()),
                    currentGraph.getVertexView(i).getX() * getRatioX(),
                    currentGraph.getVertexView(i).getY() * getRatioY());
        }

    }

    /**
     * Draw the graph in the GraphicsContext set to constructor
     */
    public void draw(){
        gc.clearRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());

        //Drawing the Edges first to avoid the black line in the circle
        drawEdges();
        drawVertexes();
    }



    public Graph getCurrentGraph() {
        return currentGraph;
    }

    public void setHighlightedGraph(GraphMatrix highlightedGraph){
        this.highlightedGraph = highlightedGraph;
    }

    public GraphMatrix getCurrentHighlightedGraph(){
        return highlightedGraph;
    }

    public void setCurrentGraph(Graph currentGraph) {
        this.currentGraph = currentGraph;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
