package sample;

import Figures.Figure;
import Figures.RoundRect;
import Tools.*;
import javafx.application.Platform;
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
    private ToolBar toolBar;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSize;

    public static List<Figure> figures = new LinkedList<>();
    private GraphicsContext graphicsContext;
    public static List<Tool> toolList = new ArrayList<>();

    private Tool currentTool;
    public static int size;

    public void onExit() {
        Platform.exit();
    }

    public void getSize(){
        size = (int)brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        exampleBrush.clearRect(0, 0,105, 105);
        exampleBrush.fillRoundRect(52-size/2, 52-size/2, size,size,size, size);
        currentTool.setSize(size);
    }

    public void initialize(){
        getGraphCont();
        colorPicker.setValue(Color.BLACK);
        currentTool = new Pen(canvas);
        new OvalTool(canvas);
        new RectTool(canvas);
        new LineTool(canvas);
        new Hand(canvas);
        new RoundRectTool(canvas);
        drawToolBut();
        getSize();
        setColor();
        canvas.toBack();
    }

    private void drawToolBut(){
        for (Tool tool : toolList){
            tool.button.setOnMouseClicked((MouseEvent event) -> {
                currentTool = tool;
                currentTool.setSize(size);
                currentTool.setColor(colorPicker.getValue());
            });
            toolBar.getItems().add(tool.button);
        }
    }

    private void getGraphCont(){
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext = canvas.getGraphicsContext2D();
        exampleBrush = CanvasExample.getGraphicsContext2D();
    }

    public void setColor() {
        currentTool.setColor(colorPicker.getValue());
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        exampleBrush.setFill(colorPicker.getValue());
        getSize();
    }

    public static void repaintCanvas(){
        for(Figure figure : figures){
            figure.draw(Figure.graphicsContext);
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