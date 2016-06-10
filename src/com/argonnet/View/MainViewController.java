package com.argonnet.View;

import com.argonnet.Algorythm.Kruskal;
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

        //Mettre à jour la liste des algorythme disponible en fonction du problème sélectionné
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
                    default :
                        throw new UnknownHowException();
                }


                break;
            default :
                throw new UnknownWhatException();
        }

        reDraw();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Init the spinner control
        SpinnerValueFactory spinneValueFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 6);
        fieldVertexNumber.setValueFactory(spinneValueFact);

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
