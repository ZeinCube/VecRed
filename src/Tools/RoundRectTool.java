package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.RoundRect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

import static sample.Controller.height;
import static sample.Controller.widht;

public class RoundRectTool extends Tool {
    public RoundRectTool(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(110);
        button.setText("RoundedRect");
        isFillingTool = true;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        start = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        end = null;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        end = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        graphicsContext.clearRect(0,0,widht,height);
        Controller.repaintCanvas();
        new RoundRect(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        if(end!=null) {
            Controller.addFigure(new RoundRect(start, end, size, colorOfStroke, Controller.colorOfFilling, Controller.isFilling));
            Controller.repaintCanvas();
        }
    }
}