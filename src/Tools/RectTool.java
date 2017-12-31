package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.Rect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

import static sample.Controller.height;
import static sample.Controller.widht;

public class RectTool extends Tool{
    public RectTool(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Rect");
        isFillingTool = true;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        start = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        end = null;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,widht,height);
        end = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        Controller.repaintCanvas();
        new Rect(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        if(end!=null) {
            Controller.addFigure(new Rect(start, end, size, colorOfStroke, Controller.colorOfFilling, Controller.isFilling));
            Controller.repaintCanvas();
        }
    }
}