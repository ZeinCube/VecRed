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

    public void initialize(){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(event -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = event.getX()-size/2;
            double y = event.getY()-size/2;
            if(eraser.isSelected()){
                graphicsContext.clearRect(x , y  , size, size);
            }else {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRoundRect(x,y,size,size,size,size);
                
            }
        });
    }
}
