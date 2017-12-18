package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.PolyLine;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pen extends Tool {
    LinkedList<Point> points;
    public Pen(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Pen");
    }

    private void newLine(LinkedList<Point> points, double size, Paint color){
        Controller.figures.add(new PolyLine(points,color,size));
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        points = new LinkedList<>();
        points.add(new Point((event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize));
        points.add(new Point((event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize));
        new PolyLine(points,color,size);
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        points.add(new Point((event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize));
        new PolyLine(points,color,size);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        newLine(points,size,color);
    }
}