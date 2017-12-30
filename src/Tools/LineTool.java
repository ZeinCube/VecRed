package Tools;

import Figures.Figure;
import Figures.Line;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class LineTool extends Tool {
    public LineTool(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Line");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
       start = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        end = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        new Line(start,end,size, colorOfStroke);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        Controller.addFigure(new Line(start,end,size, colorOfStroke));
    }
}