package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

import java.util.List;

public class Line extends Figure {
    public Line(Point start,Point end, double size, Paint colorOfStroke) {
        super(start,end, size,colorOfStroke);
    }

    @Override
    public boolean isContainPoint(Point p) {
        return false;
    }

    @Override
    public void drawSelection() {

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.strokeLine(canvSt.x,canvSt.y,canvEnd.x,canvEnd.y);
    }
}