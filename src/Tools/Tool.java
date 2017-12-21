package Tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import sample.Controller;

public abstract class Tool {
    double x0, y0 , size;
    Canvas canvas;
    GraphicsContext graphicsContext;
    Paint colorOfStroke;
    public Button button;

    public Tool(Canvas canvas) {
        this.canvas = canvas;
        Controller.toolList.add(this);
    }

    public abstract void getOnMousePressed(MouseEvent event);
    public abstract void getOnMouseDragged(MouseEvent event);
    public abstract void getOnMouseReleased(MouseEvent event);


    public void setSize(double size){
        this.size = size;
    }
    public void setColorOfStroke(Paint colorOfStroke){this.colorOfStroke = colorOfStroke;}
}