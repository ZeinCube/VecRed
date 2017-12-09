package Tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public abstract class Tool {
    double x, y, x0, y0 , size;
    Canvas canvas;
    GraphicsContext graphicsContext;
    Paint color;

    public Tool(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract void getOnMousePressed(MouseEvent event);
    public abstract void getOnMouseDragged(MouseEvent event);
    public abstract void getOnMouseReleased(MouseEvent event);


    public void setSize(double size){
        this.size = size;
    }
    public void setColor(Paint color){this.color = color;}
}
