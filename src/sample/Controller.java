package sample;

import Figures.Figure;
import Tools.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Controller {
    @FXML
    public BorderPane borderPane;
    public javafx.scene.control.MenuBar MenuBar;
    public Label scaleSizeLable;
    public Slider SliderScale;
    public ColorPicker colorOfFillingPicker;

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

    public static Double scaleSize = 1.0;
    public static List<Figure> figures = new LinkedList<>();
    private GraphicsContext graphicsContext;
    public static List<Tool> toolList = new ArrayList<>();

    private Tool currentTool;
    public static int size;
    public static boolean isFilling;
    public static Paint colorOfFilling = Color.BLACK;

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
        colorOfFillingPicker.setValue(Color.BLACK);
        setFillingColor();
        colorOfFillingPicker.setVisible(false);
        currentTool = new Pen(canvas);
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        new OvalTool(canvas);
        new RectTool(canvas);
        new LineTool(canvas);
        new Hand(canvas);
        new RoundRectTool(canvas);
        new Selection(canvas);
        drawToolBut();
        getSize();
        setColor();
        canvas.toBack();
    }

    public void onScrollScale() {
        scaleSize = SliderScale.getValue();
        graphicsContext.clearRect(0,0,1920,1080);
        repaintCanvas();
        scaleSizeLable.setText(String.valueOf(Math.round(SliderScale.getValue())));
    }
    private void drawToolBut(){
        for (Tool tool : toolList){
            tool.button.setOnMouseClicked((MouseEvent event) -> {
                currentTool = tool;
                currentTool.setSize(size);
                currentTool.setColorOfStroke(colorPicker.getValue());
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
        currentTool.setColorOfStroke(colorPicker.getValue());
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

    public void saveFile(ActionEvent actionEvent) {
        File directory = new File("C:/");
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(directory);
        directoryChooser.setTitle("Select Folder");
        directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector image (*.vi)","*.vi"));
        directory = directoryChooser.showSaveDialog(Main.getStage());
        ObjectOutputStream outputStream = null;
        if(directory!=null) {
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Figure figure : figures) {
                try {
                    outputStream.writeObject(figure);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFile(ActionEvent actionEvent) {
        File directory = new File("C:/");
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(directory);
        directoryChooser.setTitle("Select Folder");
        directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector image (*.vi)","*.vi"));
        directory = directoryChooser.showOpenDialog(Main.getStage());
        ObjectInputStream inputStream = null;
        if(directory!=null) {
            try {
                inputStream = new ObjectInputStream(new FileInputStream(directory));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            try {
                figures.clear();
                while(true) {
                    figures.add((Figure) inputStream.readObject());
                }
            } catch (EOFException ignored) {
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            graphicsContext.clearRect(0, 0, 1920, 1080);
            repaintCanvas();
    }
    }

    public void setFigureFilling() {
        if (isFilling){
            isFilling = false;
            colorOfFillingPicker.setVisible(false);
        }else{
            isFilling = true;
            colorOfFillingPicker.setVisible(true);
        }
    }

    public void setFillingColor() {
        colorOfFilling = colorOfFillingPicker.getValue();
    }

}