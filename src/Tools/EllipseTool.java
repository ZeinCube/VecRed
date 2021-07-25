package Tools;

import Figures.Ellipse;
import Figures.Figure;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

import static sample.Controller.height;
import static sample.Controller.widht;

public class EllipseTool extends Tool {
    public EllipseTool(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Oval");
        isFillingTool = true;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        start = Figure.canvasToScreen(new Point(event.getX(), event.getY()));
        end = null;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0, 0, widht, height);
        Controller.repaintCanvas();
        end = Figure.canvasToScreen(new Point(event.getX(), event.getY()));
        new Ellipse(start, end, size, colorOfStroke, Controller.colorOfFilling, Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        if (end != null) {
            Controller.addFigure(new Ellipse(start, end, size, colorOfStroke, Controller.colorOfFilling, Controller.isFilling));
            Controller.repaintCanvas();
        }
    }
}