package com.argonnet.View;

import com.argonnet.Algorythm.Kruskal;
import com.argonnet.Algorythm.Prim;
import com.argonnet.Draw.GraphDrawer;
import com.argonnet.Draw.GraphShapeFactory;
import com.argonnet.Exception.UnknownHowException;
import com.argonnet.Exception.UnknownWhatException;
import com.argonnet.GraphRepresentation.Graph;
import com.argonnet.GraphRepresentation.GraphMatrix;
import com.argonnet.Utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller used to manage the main view of the application.
 */
public class MainViewController implements Initializable{

    @FXML private Pane canvasPane;
    @FXML private Canvas drawZone;
    @FXML private AnchorPane rightPane;
    @FXML private Button buttonGenerateGraph;

    @FXML private Spinner<Integer> fieldVertexNumber;
    @FXML private Spinner<Integer> fieldVertexFrom;
    @FXML private Spinner<Integer> fieldVertexTo;
    @FXML private Spinner<Integer> fieldVertexWeight;
    @FXML private ComboBox<Constants.PROBLEM_LIST>  whatComboBox;
    @FXML private ComboBox<Constants.ALGORITHM>  howComboBox;

    private GraphMatrix highlightGraph;
    private Graph currentGraph;
    private GraphDrawer currentGraphDrawer;

    /**
     * Initialize the list of problem that could be solved
     */
    private void setWhatList(){

        whatComboBox.setCellFactory(new Callback<ListView<Constants.PROBLEM_LIST>, ListCell<Constants.PROBLEM_LIST>>() {
            @Override
            public ListCell<Constants.PROBLEM_LIST> call(ListView<Constants.PROBLEM_LIST> param) {
                return new ListCell<Constants.PROBLEM_LIST>(){
                    @Override
                    protected void updateItem(Constants.PROBLEM_LIST item, boolean empty) {
                        super.updateItem(item,empty);

                        setText(Constants.problemListDef.get(item));
                    }
                };
            }
        });

        //Update algorithm list in function of the problem selected
        whatComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            howComboBox.getItems().clear();
            howComboBox.getItems().addAll(Constants.problemAndSoluctionList.get(whatComboBox.getSelectionModel().getSelectedItem()));
        });

        whatComboBox.getItems().addAll(Constants.problemListDef.keySet());
    }

    /**
     * Initialize the list of the algorithm to find the solution to a problem
     */
    private void setHowList(){

        howComboBox.setCellFactory(new Callback<ListView<Constants.ALGORITHM>, ListCell<Constants.ALGORITHM>>() {
            @Override
            public ListCell<Constants.ALGORITHM> call(ListView<Constants.ALGORITHM> param) {
                return new ListCell<Constants.ALGORITHM>(){
                    @Override
                    protected void updateItem(Constants.ALGORITHM item, boolean empty) {
                        super.updateItem(item,empty);

                        setText(Constants.algoListDef.get(item));
                    }
                };
            }
        });
    }

    /**
     * Initialization of the edge creation
     */
    private void setEdgeFromToSpinnerVal(){
        if(currentGraph != null){
            fieldVertexFrom.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, currentGraph.getVertexCount()));
            fieldVertexTo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, currentGraph.getVertexCount()));
        }
    }

    /**
     * reDraw the current graph list
     */
    private void reDraw(){
        if(currentGraph != null){
            currentGraphDrawer.draw();
        }
    }

    /**
     * Generating a new random graph
     */
    @FXML private void generateGraphOnClick(){
        highlightGraph = null;
        currentGraph = new Graph(fieldVertexNumber.getValue());
        currentGraph.generateRandomGraph(10);
        GraphShapeFactory.setVertexPosition(currentGraph);

        currentGraphDrawer.setCurrentGraph(currentGraph);
        currentGraphDrawer.setHighlightedGraph(highlightGraph);
        reDraw();

        setEdgeFromToSpinnerVal();
    }

    /**
     * Calculating a solution to a problem
     */
    @FXML private void calcOnClick() throws Exception{

        switch (whatComboBox.getSelectionModel().getSelectedItem()){
            case MinimalTree:

                switch(howComboBox.getSelectionModel().getSelectedItem()){
                    case Kruskal:
                        highlightGraph = (new Kruskal()).CalcMinimalTree(currentGraph,0);
                        break;
                    case Prim:
                        highlightGraph = (new Prim()).CalcMinimalTree(currentGraph,0);
                        break;
                    default :
                        throw new UnknownHowException();
                }


                break;
            default :
                throw new UnknownWhatException();
        }

        currentGraphDrawer.setHighlightedGraph(highlightGraph);
        reDraw();
    }

    /**
     * Add a vertex to the current graph
     */
    @FXML private void addVertexOnClick(){
        if(currentGraph != null){
            highlightGraph = null;
            currentGraphDrawer.setHighlightedGraph(highlightGraph);
            currentGraph.addVertex();
            currentGraphDrawer.draw();

            setEdgeFromToSpinnerVal();
        }
    }

    /**
     * Add an edge to the graph
     */
    @FXML private void addEdgeOnClick(){
        if(currentGraph != null){
            currentGraph.setEdge(fieldVertexFrom.getValue()  - 1,fieldVertexTo.getValue() - 1,fieldVertexWeight.getValue());
            currentGraphDrawer.draw();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Init the spinner control
        fieldVertexNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 6));
        fieldVertexWeight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));

        //Init list of problem and solution
        setWhatList();
        setHowList();

        //Setting canvas size to his container
        drawZone.widthProperty().bind(canvasPane.widthProperty());
        drawZone.heightProperty().bind(canvasPane.heightProperty());

        currentGraphDrawer = new GraphDrawer(drawZone);

        drawZone.widthProperty().addListener(observable -> reDraw());
        drawZone.heightProperty().addListener(observable -> reDraw());
    }

}
