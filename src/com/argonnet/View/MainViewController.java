package com.argonnet.View;

import com.argonnet.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    @FXML private ComboBox<Constants.ALGORYTHM>  howComboBox;

    private GraphMatrix highlightGraph;
    private Graph currentGraph;
    private GraphDrawer currentGraphDrawer;

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

    private void setHowList(){

        howComboBox.setCellFactory(new Callback<ListView<Constants.ALGORYTHM>, ListCell<Constants.ALGORYTHM>>() {
            @Override
            public ListCell<Constants.ALGORYTHM> call(ListView<Constants.ALGORYTHM> param) {
                return new ListCell<Constants.ALGORYTHM>(){
                    @Override
                    protected void updateItem(Constants.ALGORYTHM item, boolean empty) {
                        super.updateItem(item,empty);

                        setText(Constants.algoListDef.get(item));
                    }
                };
            }
        });

    }

    private void reDraw(){
        if(currentGraph != null){
            currentGraphDrawer.drawGraph(currentGraph,highlightGraph);
        }
    }

    @FXML private void generateGraphOnClick(){
        currentGraph = new Graph(fieldVertexNumber.getValue());
        currentGraph.generateRandomGraph(10);
        GraphShapeFactory.setVertexPosition(currentGraph);
        reDraw();
    }

    @FXML private void calcOnClick(){

        highlightGraph = (new Kruskal()).CalcMinimalTree(currentGraph,1);
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

        drawZone.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(int i = 0; i < currentGraph.getVertexCount();i++){
                   // currentGraph.getVertexView()
                }
            }
        });
        currentGraphDrawer = new GraphDrawer(drawZone.getGraphicsContext2D());

        drawZone.widthProperty().addListener(observable -> reDraw());
        drawZone.heightProperty().addListener(observable -> reDraw());

    }

}
