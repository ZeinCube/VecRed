package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.RoundRect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class RoundRectTool extends Tool {
    public RoundRectTool(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(110);
        button.setText("RoundedRect");
        isFillingTool = true;
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
        new RoundRect(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.addFigure(new RoundRect(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling));
        Controller.repaintCanvas();
    }
}