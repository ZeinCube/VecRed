package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public float getSize(){
        float size;
        if(brushSize.getText().equals("")) {
            size = 5;
            brushSize.setText("5");
        }else {
            size = Float.parseFloat(brushSize.getText());
        }
        return size;
    }

    public void draw(){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(event -> {
            graphicsContext.setLineWidth(getSize());
            double x = event.getX();
            double y = event.getY();
            graphicsContext.setStroke(colorPicker.getValue());
            graphicsContext.beginPath();
            graphicsContext.lineTo(x , y);
            graphicsContext.stroke();
        });

        canvas.setOnMouseDragged(event -> {
            graphicsContext.setLineWidth(getSize());
            double x = event.getX();
            double y = event.getY();
            graphicsContext.lineTo(x , y);
            graphicsContext.stroke();
        });
    }

    public void initialize(){
        draw();
    }
}
