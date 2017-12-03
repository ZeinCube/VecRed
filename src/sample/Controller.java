package sample;

import Tools.Line;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


public class Controller {

    private GraphicsContext exampleBrush;

    @FXML
    private Canvas CanvasExample;

    @FXML
    private Label brushSizeLabel;

    private int size;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSize;

    @FXML
    private CheckBox eraser;

    private double x, y, x0, y0;

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void getSize(){
        size = (int)brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        exampleBrush.clearRect(0, 0,105, 105);
        exampleBrush.fillRoundRect(52-size/2, 52-size/2, size,size,size, size);
    }

    private void draw() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        exampleBrush = CanvasExample.getGraphicsContext2D();

        canvas.setOnMousePressed(event -> {
            double x = event.getX()-size/2;
            double y = event.getY()-size/2;
            x0 = x;
            y0 = y;
            if(eraser.isSelected()){
                graphicsContext.clearRect(x, y ,size, size);
            }else {
                graphicsContext.fillRoundRect(x, y, size, size, size, size);
            }
        });

        canvas.setOnMouseDragged(event -> {
            x = (int) (event.getX()-size/2);
            y = event.getY()-size/2;

            if(eraser.isSelected()){
                graphicsContext.clearRect(x, y ,size, size);
            }else {
                new Line(size,x, y, x0, y0,graphicsContext);
                x0 = x;
                y0 = y;
            }
        });
    }

    public void initialize(){
        colorPicker.setValue(Color.BLACK);
        draw();
        getSize();
    }

    public void setColor(ActionEvent actionEvent) {
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        exampleBrush.setFill(colorPicker.getValue());
        getSize();
    }
}
