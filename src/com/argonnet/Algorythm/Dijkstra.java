package com.argonnet.Algorythm;

import com.argonnet.GraphRepresentation.Edge;
import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;

import java.util.ArrayList;

/**
 * Djikstra algorithm implementation
 */
public class Dijkstra implements IShortestPath {
	
	//Base graph for the current calc
	private Graph currentGraph;
	
	//Start and End point of the graph.
	private int from;
	private int to;
	
	//Tab to get the shortestDist for each vertex
	private int[] shortestDist;
	//Tab to get the vertex from which the shortest distance come from for each vertex
	private int[] predecessor; 
	//Manage the parsed status of the vertex, if it had been already marked (added in solution) will be to true
	private boolean[] hasVertexMarked;
	
	/**
	 * Return the vertex with the min distance from start in the shortestDist tab
	 */
	private int getVertexWithMinDist(){
		int minDist = Integer.MAX_VALUE;
		int vertex = -1;
		for(int i = 0; i < currentGraph.getVertexCount();i++){
			if(shortestDist[i] < minDist && !hasVertexMarked[i]){
				minDist = shortestDist[i];
				vertex = i;
			}
		}

		hasVertexMarked[vertex] = true;
		return vertex;
	}
	
	/**
	 * Creation of the result graph with predecessor tab
	 * It only inclure de main path between From and To
	 */
	private GraphMatrix resultGraph(){
		//Create the Partial graph result
		//It should be bette to use an under graph but in our archicture it's easier to use a partial graph
		GraphMatrix resultGraph = new GraphMatrix(currentGraph.getVertexCount());
		
		int vertex = this.to;
		while(vertex != this.from){
			resultGraph.setEdge(vertex,predecessor[vertex], currentGraph.getEdge(vertex,predecessor[vertex]));
			vertex = predecessor[vertex];
		}
		
		return resultGraph;
	}

    /**
     * Calc a shortest path (partial graph), from a node to another one
     * @param graph Base graph to proceed
     * @param from The vertex where the algorithm should start (this number is the one known by the end user)
	 * @param to The vertex where that we want reach
     * @return Partial graph that represent the shortest path
     */
    @Override
    public GraphMatrix CalcShortestPath(Graph graph, int from, int to){
		this.from = from - 1 ;
		this.to = to - 1;
		
		currentGraph = graph;
		shortestDist = new int[currentGraph.getVertexCount()];
		predecessor = new int[currentGraph.getVertexCount()]; 
		hasVertexMarked = new boolean[currentGraph.getVertexCount()];
		
		//Parsed vertex count
		int parsedVertexCount = 0;
		
		//Step 1 : Init of the tab with infinite values
		for(int i = 0; i < currentGraph.getVertexCount();i++) {
			shortestDist[i] = Integer.MAX_VALUE;
			hasVertexMarked[i] = false;
		}
		shortestDist[this.from] = 0; //Departure vertex init to 0
		
		//Step 2 : Dijkstra main algorithm part
		int currentVertex;
		while(parsedVertexCount < currentGraph.getVertexCount()){
			parsedVertexCount++;
			currentVertex = getVertexWithMinDist();
			
			for(int i = 0; i < currentGraph.getVertexCount();i++){
				if(currentGraph.getEdge(currentVertex, i) != 0 && !hasVertexMarked[i]){
					if(shortestDist[i] > shortestDist[currentVertex] + currentGraph.getEdge(currentVertex, i)){
						shortestDist[i] = shortestDist[currentVertex] + currentGraph.getEdge(currentVertex, i);
						predecessor[i] = currentVertex; 
					}
				}
			}	
		}
		
        return resultGraph();
    }

}
