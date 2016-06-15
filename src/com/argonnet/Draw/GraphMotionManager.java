package com.argonnet.Draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 * Class that manage the motion of a graph in a canvas
 */
public class GraphMotionManager {

    private Canvas canvas;
    private GraphDrawer drawer;

    private int currentDraggedVertex;

    private boolean isDraggedAllowed;

    /**
     * Class constructor
     * @param drawer Drawer that will manager the redraw of the graph
     */
    public GraphMotionManager(GraphDrawer drawer){
        this.drawer = drawer;
        this.canvas = drawer.getCanvas();

        this.currentDraggedVertex = Integer.MIN_VALUE;

        canvas.addEventHandler(MouseEvent.DRAG_DETECTED, e -> {
            findCurrentDraggedVertex(e.getX(),e.getY());
        } );

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if(this.isDraggedAllowed()){
                this.moveVertexWithMouse(e.getX(),e.getY());
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.currentDraggedVertex = Integer.MIN_VALUE;
        } );

    }


    /**
     * Find the vertex that will be moved by the user
     * @param mouseXPos Position X of the mouse, in the canvas
     * @param mouseYPos Position Y of the mouse, in the canvas
     */
    public int findCurrentDraggedVertex(double mouseXPos, double mouseYPos){
        int vertex = -1;

        for(int i = 0; i< drawer.getCurrentGraph().getVertexCount();i++){

            double distanceWithCenter = Math.sqrt(
                    Math.pow(drawer.getCurrentGraph().getVertexView(i).getX() * drawer.getRatioX() - mouseXPos,2) +
                    Math.pow(drawer.getCurrentGraph().getVertexView(i).getY() * drawer.getRatioY() - mouseYPos,2)
            );

            if(distanceWithCenter <= GraphDrawer.VERTEX_RADIUS)  {
                this.currentDraggedVertex = i;
                vertex = i;
                break; //Optimization of the treatment when we found a vertex we move leave
            }
        }

        return vertex;
    }


    /**
     * Move the current dragged vertex to X and Y coordinate in the canvas
     * @param xPos Position on X axe in the canvas
     * @param yPos Position on Y axe in the canvas
     */
    private void moveVertexWithMouse(double xPos, double yPos){

        if(this.currentDraggedVertex != Integer.MIN_VALUE) {

            drawer.getCurrentGraph().getVertexView(currentDraggedVertex).setX(xPos / drawer.getRatioX());
            drawer.getCurrentGraph().getVertexView(currentDraggedVertex).setY(yPos / drawer.getRatioY());

            drawer.draw();
        }
    }


    public boolean isDraggedAllowed() {
        return isDraggedAllowed;
    }

    public void setDraggedAllowed(boolean draggedAllowed) {
        isDraggedAllowed = draggedAllowed;
    }
}
