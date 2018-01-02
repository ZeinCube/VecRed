package Tools;

import Figures.Figure;
import Figures.Line;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

import static sample.Controller.height;
import static sample.Controller.widht;

public class LineTool extends Tool {
    public LineTool(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Line");
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
        new Line(start,end,size, colorOfStroke);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        graphicsContext.clearRect(0,0,widht,height);
        Controller.repaintCanvas();
        if(end!=null)
        Controller.addFigure(new Line(start,end,size, colorOfStroke));
    }
}