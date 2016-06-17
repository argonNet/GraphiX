package com.argonnet.Edit;

import com.argonnet.Draw.GraphDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * Created by Argon on 15.06.16.
 */
public class GraphEdgeViusalEditionManager {

    private Canvas canvas;
    private GraphDrawer drawer;
    private GraphMotionManager motionManager;

    private boolean isEnable;
    private int newEdgeFrom;
    private int newEdgeTo;



    /**
     * Class constructor
     * @param drawer Drawer that will manager the redraw of the graph
     */
    public GraphEdgeViusalEditionManager(GraphDrawer drawer, GraphMotionManager motionManager){
        this.drawer = drawer;
        this.canvas = drawer.getCanvas();
        this.motionManager = motionManager;

        this.isEnable = false;
        this.newEdgeFrom = -1;
        this.newEdgeTo = -1;


        canvas.addEventHandler(MouseEvent.DRAG_DETECTED, e -> {
            if(isEnable){
                this.newEdgeFrom = motionManager.findCurrentDraggedVertex(e.getX(),e.getY());
            }
        } );

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if(isEnable && this.newEdgeFrom != -1){
                this.drawer.drawWithLine(this.newEdgeFrom,e.getX(),e.getY());
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if(isEnable) {
                this.newEdgeTo = motionManager.findCurrentDraggedVertex(e.getX(), e.getY());
                if (this.newEdgeTo != -1 && this.newEdgeFrom != -1) {
                    int weight = askEdgeWeight(this.newEdgeFrom, this.newEdgeTo);
                    drawer.getCurrentGraph().setEdge(this.newEdgeFrom, this.newEdgeTo, weight == -1 ? drawer.getCurrentGraph().getEdge(this.newEdgeFrom, this.newEdgeTo) : weight);

                }
                drawer.draw();

                this.newEdgeFrom = -1;
                this.newEdgeTo = -1;
            }
        });

    }

    /**
     * Ask the new edge weight
     * @param from Edge departure
     * @param to Edge destination
     * @return Edge weight selection
     */
    private int askEdgeWeight(int from, int to){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);

        int selectedVal = -1;

        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Weight chooser");
        dialog.setHeaderText("Edge from : " + (from + 1)  + " to : " + (to + 1));
        dialog.setContentText("Weight :");

        Optional<String> result = dialog.showAndWait();

        try{
            if (result.isPresent()){
                selectedVal = Integer.valueOf(result.get());
                if(selectedVal < 0){

                    alert.setContentText("The value \":" + result.get() + "\" is not a valid integer value !");
                    alert.showAndWait();
                }
            }
        }catch(NumberFormatException e){
            alert.setContentText("The value must be >= 0 !");
            alert.showAndWait();
        }

        return selectedVal;
    }

    public void EnableEdgeCreation(){
        this.isEnable = true;
        this.motionManager.setDraggedAllowed(false);
    }

    public void DisableEdgeCreation(){
        this.isEnable = false;
        this.motionManager.setDraggedAllowed(true);
    }
}
