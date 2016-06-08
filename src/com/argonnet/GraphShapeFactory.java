
package com.argonnet;

/**
 * Classe that provide the shape for the graphe (vertexes position)
 */
public class GraphShapeFactory {

    //Shortcut to simplify the point definition tab
    private static final int W = Constants.GRAPH_REF_WIDTH;
    private static final int H = Constants.GRAPH_REF_HEIGHT;

    //Constants for the X and Y val in the tab
    private static final int X = 0;
    private static final int Y = 1;

    /**
     * Define the position of each point of the vertexes in function of their count
     * First coord  => number of vertexes
     * Second coord => number of THE vertex
     * Third coord  => 0 = X pos, 1 = Y pos
     */
    private static final double[][][] VERTEXES_POSITION_DEF = {
		/* 000 */ {{}},
		/* 001 */ {{W / 2, H / 2}},
		/* 002 */ {{W / 3, H / 2},{2* W / 3, H / 2}},
		/* 003 */ {{W / 3, H / 3},{2* W / 3, H / 3},{W / 2,2* H / 3}},
		/* 004 */ {{W / 3, H / 3},{2* W / 3, H / 3},{W / 3,2* H / 3},{2* W / 3,2 * H / 3}},
		/* 005 */ {{W / 4, H / 2},{2* W / 4, H / 3},{3* W / 4, H / 3},{2* W / 4 ,2* H / 3},{3* W / 4,2 * H / 3}},
		/* 006 */ {{W / 5, H / 2},{2* W / 5, H / 3},{3* W / 5, H / 3},{4* W / 5 ,H / 2},{2* W / 5,2 * H / 3},{3* W / 5,2 * H / 3}}
    };

    /**
     * Set the position of each vetext of the graph
     * @param graph Graph to define vetexes position
     */
    public static void setVertexPosition(Graph graph){

        for(int i = 0; i < graph.getVertexCount(); i++){
            graph.getVertexView(i).setX(VERTEXES_POSITION_DEF[graph.getVertexCount()][i][X]);
            graph.getVertexView(i).setY(VERTEXES_POSITION_DEF[graph.getVertexCount()][i][Y]);
        }

    }


}
