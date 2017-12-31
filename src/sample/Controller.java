package sample;

import Figures.Figure;
import Tools.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.awt.*;
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
    public CheckBox checkBox;

    public Label PropertySize;
    public ColorPicker paramColorOfStroke;
    public CheckBox PropertyFilled;
    public VBox BoxColorOfFiling;
    public Slider sizeOfPropertyBrush;
    public ColorPicker colorOfFillingParamPicker;
    public VBox Parameters;
    public static Label staticPropertySize;
    public static ColorPicker staticParamColorOfStroke;
    public static CheckBox staticPropertyFilled;
    public static VBox staticBoxColorOfFiling;
    public static ColorPicker staticColorOfFillingParam;
    public static Slider staticSizeOfPropertyBrush;
    public static VBox staticParameters;



    @FXML
    private Label brushSizeLabel;

    @FXML
    private Canvas canvas;

    @FXML
    private ToolBar toolBar;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSize;

    public static Double scaleSize = 1.0;
    public static List<Figure> figures = new ArrayList<>();
    private GraphicsContext graphicsContext;
    public static List<Tool> toolList = new ArrayList<>();

    private Tool currentTool;
    public static int size;
    public static boolean isFilling = false;
    public static boolean isFillingParam;
    public static Paint colorOfFilling = Color.BLACK;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int widht = Toolkit.getDefaultToolkit().getScreenSize().width;


    public void onExit() {
        Platform.exit();
    }

    public void getSize(){
        size = (int)brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        currentTool.setSize(size);
    }

    public void initialize(){
        getGraphCont();
        colorPicker.setValue(Color.BLACK);
        colorOfFillingPicker.setValue(Color.BLACK);
        setFillingColor();
        colorOfFillingPicker.setVisible(false);
        checkBox.setVisible(false);
        currentTool = new Pen(canvas);
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        new EllipseTool(canvas);
        new RectTool(canvas);
        new LineTool(canvas);
        new Hand(canvas);
        new RoundRectTool(canvas);
        new Selection(canvas);
        drawToolBut();
        getSize();
        setColor();
        canvas.toBack();
        initparams();
        History.init();
    }

    public void initparams(){
        staticColorOfFillingParam = colorOfFillingParamPicker;
        staticBoxColorOfFiling = BoxColorOfFiling;
        staticParamColorOfStroke = paramColorOfStroke;
        staticPropertySize = PropertySize;
        staticPropertyFilled = PropertyFilled;
        staticPropertySize = PropertySize;
        staticSizeOfPropertyBrush = sizeOfPropertyBrush;
        staticParameters = Parameters;
    }

    public void onScrollScale() {
        scaleSize = SliderScale.getValue();
        graphicsContext.clearRect(0,0,widht,height);
        repaintCanvas();
        scaleSizeLable.setText(String.valueOf(Math.round(SliderScale.getValue())));
    }
    private void drawToolBut(){
        for (Tool tool : toolList){
            tool.button.setOnMouseClicked((MouseEvent event) -> {
                currentTool = tool;
                currentTool.setSize(size);
                currentTool.setColorOfStroke(colorPicker.getValue());
                checkBox.setVisible(tool.isFillingTool);
                colorOfFillingPicker.setVisible(tool.isFillingTool&isFilling);
            });
            toolBar.getItems().add(tool.button);
        }
    }

    private void getGraphCont(){
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void setColor() {
        currentTool.setColorOfStroke(colorPicker.getValue());
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
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

    public void canvasOnMousePressed(MouseEvent event) { currentTool.getOnMousePressed(event);}

    public void canvasOnMouseDragged(MouseEvent event) {
        currentTool.getOnMouseDragged(event);
    }

    public void canvasOnMouseReleased(MouseEvent event) {
        currentTool.getOnMouseReleased(event);
        if(!currentTool.button.getText().equals("Selection"))
        History.rememberCondition();
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
            graphicsContext.clearRect(0,0,widht,height);
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
        History.rememberCondition();
    }

    public void setFillingColor() {
        colorOfFilling = colorOfFillingPicker.getValue();
    }

    public void copy(ActionEvent actionEvent) {
        for(Figure figure : figures){
            if(figure.isSelected){

            }
        }
    }

    public void paste(ActionEvent actionEvent) {

        History.rememberCondition();

    }

    public void cut(ActionEvent actionEvent) {

        History.rememberCondition();
    }

    public void deleteSelectedShape(ActionEvent actionEvent) {
        for(int i = 0;i<=figures.size()-1;i++){
            if(figures.get(i).isSelected) {
                figures.remove(i);
                i--;
            }
            graphicsContext.clearRect(0,0,widht,height);
            repaintCanvas();
        }
        hideParams();
        History.rememberCondition();
    }

    public void setColorOfShape() {
        for(Figure figure : figures){
            if(figure.isSelected)
                figure.colorOfStroke = paramColorOfStroke.getValue();
        }
        graphicsContext.clearRect(0,0,widht,height);
        repaintCanvas();
        History.rememberCondition();
    }

    public void setSizeOfBrush() {
        PropertySize.setText(String.valueOf(sizeOfPropertyBrush.getValue()));
        for(Figure figure : figures){
            if(figure.isSelected)
                figure.size = sizeOfPropertyBrush.getValue();
            graphicsContext.clearRect(0,0,widht,height);
            repaintCanvas();
        }
        History.rememberCondition();
    }

    public void setParamFilling() {
        isFillingParam = !isFillingParam;
        BoxColorOfFiling.setVisible(isFillingParam);
        for (Figure figure : figures) {
            if (figure.isSelected & figure.isFillingFigure) {
                figure.isFilled = isFillingParam;
                figure.colorOfFilling = colorOfFillingParamPicker.getValue();
            }
        }
        graphicsContext.clearRect(0,0,widht,height);
        repaintCanvas();
        History.rememberCondition();
    }

    public void setColorOfFillingParam() {
        for (Figure figure : figures){
            if(figure.isSelected&figure.isFillingFigure){
                figure.colorOfFilling = colorOfFillingParamPicker.getValue();
            }
            graphicsContext.clearRect(0,0,widht,height);
            repaintCanvas();
        }
        History.rememberCondition();
    }

    public static void showParams(){
        isFillingParam = Selection.isFilled;
        staticParamColorOfStroke.setValue(Selection.colorOfShape);
        if(Selection.isSameSize) {
            staticPropertySize.setText(String.valueOf(Selection.sizeOfBrush));
            staticSizeOfPropertyBrush.setValue(Selection.sizeOfBrush);
        }
        staticBoxColorOfFiling.setVisible(Selection.isFilled);
        staticPropertyFilled.setSelected(Selection.isFilled);
        staticColorOfFillingParam.setValue(Selection.colorOfFilling);
        staticPropertyFilled.setVisible(Selection.isFillingFigure);
        staticParameters.setVisible(true);
    }

    public static void hideParams(){
        staticParameters.setVisible(false);
    }

    public void undo() {
        History.undo();
    }

    public void redo() {
        History.redo();
    }

    public void getSizeFinal(MouseEvent event) {
        History.rememberCondition();
    }

    public void onScrollScaleFinal(MouseEvent event) {
        History.rememberCondition();
    }
}