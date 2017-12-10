package Tools;

import Figures.Rect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class RectTool extends Tool{

    public RectTool(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Rect");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x0 = event.getX();
        y0 = event.getY();
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        new Rect(x0,y0, event.getX(), event.getY(), size,color);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.repaintCanvas();
        Controller.addFigure(new Rect(x0,y0, event.getX(), event.getY(), size,color));
    }
}
