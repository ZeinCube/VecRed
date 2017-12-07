package sample;

import Figures.Figure;
import Tools.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Controller {
    @FXML
    public BorderPane borderPane;

    @FXML
    public ComboBox toolBox;

    @FXML
    private GraphicsContext exampleBrush;

    @FXML
    private Canvas CanvasExample;

    @FXML
    private Label brushSizeLabel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSize;

    public static List<Figure> figures = new LinkedList<>();
    private GraphicsContext graphicsContext;

    private ObservableList<String> tools = FXCollections.observableArrayList(
            "Pen",
            "Oval",
            "Eraser",
            "Rectangle"
    );

    private Tool pen;
    private Tool oval;
    private Tool eraser;
    private Tool rectTool;
    private Tool currentTool;
    public static int size;
    public static List<Tool> toolsList = new ArrayList<>();

    public void onExit() {
        Platform.exit();
    }

    public void getSize(){
        size = (int)brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        exampleBrush.clearRect(0, 0,105, 105);
        exampleBrush.fillRoundRect(52-size/2, 52-size/2, size,size,size, size);
        for (Tool tool : toolsList){
            tool.setSize(size);
        }
    }

    public void initialize(){
        getGraphCont();
        colorPicker.setValue(Color.BLACK);
        toolBox.setItems(tools);
        toolBox.setValue("Pen");
        pen = new Pen(canvas);
        oval = new OvalTool(canvas);
        eraser = new Eraser(canvas,15);
        rectTool  = new RectTool(canvas);
        toolSelect();
        toolsList.add(pen);
        toolsList.add(oval);
        toolsList.add(eraser);
        toolsList.add(rectTool);
        toolsList.add(currentTool);
        getSize();
        setColor();
        canvas.toBack();
    }

    private void getGraphCont(){
        graphicsContext = canvas.getGraphicsContext2D();
        exampleBrush = CanvasExample.getGraphicsContext2D();
    }

    public void setColor() {
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        exampleBrush.setFill(colorPicker.getValue());
        for (Tool tool : toolsList){
            tool.setColor(colorPicker.getValue());
        }
        getSize();
    }

    public void toolSelect() {
        switch ((String) toolBox.getValue()){
            case "Pen" :
                oval.setSize(size);
                oval.setColor(colorPicker.getValue());
                currentTool = pen;
                break;
            case "Oval" :
                oval.setColor(colorPicker.getValue());
                currentTool = oval;
                break;
            case "Eraser":
                eraser.setSize(size);
                currentTool = eraser;
                break;
            case "Rectangle":
                rectTool.setColor(colorPicker.getValue());
                currentTool = rectTool;
                break;
        }
    }

    public static void repaintCanvas(){
        for(Figure figure : figures){
            figure.draw();
        }
    }

    public static void addFigure(Figure figure){
        figures.add(figure);
    }

    public void canvasOnMousePressed(MouseEvent event) {
        currentTool.getOnMousePressed(event);
    }

    public void canvasOnMouseDragged(MouseEvent event) {
        currentTool.getOnMouseDragged(event);
    }

    public void canvasOnMouseReleased(MouseEvent event) {
        currentTool.getOnMouseReleased(event);
    }
}
