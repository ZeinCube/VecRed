package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.PolyLine;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.util.LinkedList;

public class Pen extends Tool {
    PolyLine curruntLine;
    public Pen(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Pen");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        curruntLine = new PolyLine(new LinkedList<>(), colorOfStroke,size);
        curruntLine.addPoint(Figure.canvasToScreen(new Point(event.getX(),event.getY())));
        curruntLine.addPoint(Figure.canvasToScreen(new Point(event.getX(),event.getY())));
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        curruntLine.addPoint(Figure.canvasToScreen(new Point(event.getX(),event.getY())));
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.figures.add(curruntLine);
        curruntLine = null;
    }
}